package cn.qdd.foods.domain.base;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 东方曜
 * @date 17:52
 */
@Data
public class BaseEntity {

    private Integer page;

    private Integer size;


    private Map<String, Object> paramMap;

    public Map<String, Object> getParamMap() {
        if (CollectionUtils.isEmpty(paramMap)) {
            return new HashMap<>();
        }
        return paramMap;
    }


    public Integer getPage() {
        return page == null || page < 1 ? 1 : this.page;
    }

    public Integer getSize() {
        return size == null || size < 1 ? 20 : this.size;
    }


}
