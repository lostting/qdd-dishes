package cn.qdd.foods.domain.vo;

import lombok.Data;

@Data
public class TblRankVo {
    private String name;
    private int rankOrModuleId;
    private int state;
    private int page;
    private int size;
}
