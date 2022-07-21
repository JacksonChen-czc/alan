package cn.chenzecheng.alan.common.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: mqdemo
 * @description: 分页实体
 * @author: shenning
 * @create: 2020-10-09 11:18
 */
@Data
@NoArgsConstructor
public class MyPageInfo {
    /**
     * 当前页码
     */
    private Long pageNum = 1L;

    /**
     * 每页数量
     */
    private Long pageSize = 10L;

    /**
     * 页码总数
     */
    private Long pages;

    /**
     * 记录总数
     */
    private Long total;

    public MyPageInfo(Long pageNum, Long pageSize, Long pages, Long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
    }

    public boolean hasNext() {
        return this.pageNum < pages;
    }
}