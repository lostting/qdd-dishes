package cn.qdd.foods.filter;

import cn.qdd.foods.constant.Constants;
import cn.qdd.foods.constant.HttpStatus;
import cn.qdd.foods.domain.SysUser;
import cn.qdd.foods.domain.base.ResultUtil;
import cn.qdd.foods.utils.ServletUtils;
import cn.qdd.foods.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * token过滤器 验证token有效性
 *
 * @author 东方曜
 */
//@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${token.header}")
    private String header;

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expireTime}")
    private int expireTime;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            if (!request.getRequestURI().contains("swagger-") && !request.getRequestURI().contains("api-docs") && !request.getRequestURI().contains("upload/")
                    && !request.getRequestURI().contains("favicon") && ! request.getRequestURI().contains("/login")  && !request.getRequestURI().contains("callback/video")
                    && !request.getRequestURI().contains("qiNiu/callback")) {
                SysUser sysUserJson = this.sign(request);
                if (Objects.isNull(sysUserJson)) {
                    int code = HttpStatus.UNAUTHORIZED;
                    String msg = StringUtils.format("无效token：{}，认证失败，无法访问系统资源", request.getRequestURI());
                    ServletUtils.renderString(response, JSON.toJSONString(ResultUtil.fail(code, msg)));
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            int code = HttpStatus.UNAUTHORIZED;
            String msg = StringUtils.format("token已失效：{}，认证失败，无法访问系统资源", request.getRequestURI());
            ServletUtils.renderString(response, JSON.toJSONString(ResultUtil.fail(code, msg)));
        }
    }

    public SysUser sign(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            // 解析对应的权限以及用户信息

            String o = (String) claims.get(Constants.LOGIN_USER_KEY);
            return JSONObject.parseObject(o, SysUser.class);
        }
        return null;

    }
}
