package cn.chenzecheng.alan.common.bean;

import lombok.Data;

/**
 * @author JacksonChen
 * @date 2022/7/19 23:00
 */
@Data
public class BasePageReq {

    private int pageNo = 1;
    private int size = 10;
}
