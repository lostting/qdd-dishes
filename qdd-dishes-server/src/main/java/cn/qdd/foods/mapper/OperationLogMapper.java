package cn.qdd.foods.mapper;

import cn.qdd.foods.domain.base.SysOperationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OperationLogMapper extends BaseMapper<SysOperationLog> {

    @Delete("delete from sys_operation_log WHERE operation_time < #{day} ")
    void delLog(@Param("day") String day);
}
