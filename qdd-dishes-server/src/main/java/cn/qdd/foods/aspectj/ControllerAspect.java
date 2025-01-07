package cn.qdd.foods.aspectj;

import cn.qdd.foods.annotation.ControllerLog;
import cn.qdd.foods.domain.base.SysOperationLog;
import cn.qdd.foods.enums.BusinessStatus;
import cn.qdd.foods.mapper.OperationLogMapper;
import cn.qdd.foods.utils.ServletUtils;
import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 操作日志记录处理
 *
 * @author 东方曜
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerAspect {

//    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final OperationLogMapper sysOperationLogMapper;

    // 配置织入点
    @Pointcut("@annotation(cn.qdd.foods.annotation.ControllerLog)")
    public void controllerLogPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "controllerLogPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "controllerLogPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {

            // 获得注解
            ControllerLog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }

            // *========数据库日志=========*//
            SysOperationLog operationLog = new SysOperationLog();
            operationLog.setStatus(BusinessStatus.SUCCESS.getValue());
            // 请求的地址
//            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
//            operationLog.setOperationIp(ip);
            // 返回参数

            // 返回参数
            operationLog.setJsonResult(JSON.toJSONString(jsonResult));

            operationLog.setOperationUrl(ServletUtils.getRequest().getRequestURI());
        /*    String redisUser = jwtAuthenticationTokenFilter.getRedisUser(ServletUtils.getRequest());
            if (StringUtils.isNotEmpty(redisUser)) {

                operationLog.setOperationName(JSONObject.parseObject(redisUser).getString("realName"));
            }*/

            if (e != null) {
                operationLog.setStatus(BusinessStatus.FAIL.getValue());
                operationLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operationLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operationLog.setRequestMethod(ServletUtils.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operationLog);
            // 保存数据库
            log.info(operationLog.toString());
            this.insertOperationLog(operationLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==后置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    @Async
    void insertOperationLog(SysOperationLog operationLog) {
        operationLog.setOperationTime(new Date());
        // 远程查询操作地点
//        operationLog.setOperationLocation(AddressUtils.getRealAddressByIP(operationLog.getOperationIp()));
        sysOperationLogMapper.insert(operationLog);
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log          日志
     * @param operationLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, ControllerLog log, SysOperationLog operationLog) throws Exception {
        // 设置action动作
        operationLog.setBusinessType(log.businessType().ordinal());
        // 设置标题
        operationLog.setTitle(log.title());
        // 获取参数的信息，传入到数据库中。
        operationLog.setOperationParam(StringUtils.substring(JSON.toJSONString(Arrays.stream(joinPoint.getArgs()).collect(Collectors.toList())), 0, 2000));
    }


    /**
     * 是否存在注解，如果存在就获取
     */
    private ControllerLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(ControllerLog.class);
        }
        return null;
    }

}
