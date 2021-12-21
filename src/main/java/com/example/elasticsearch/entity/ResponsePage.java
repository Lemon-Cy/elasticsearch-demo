package com.example.elasticsearch.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分页数据
 * </p>
 *
 * @author ChenYu
 * @since 2021/9/13 15:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页数据")
public class ResponsePage<T> implements Serializable {

    /**
     * 总条数
     */
    @ApiModelProperty("总条数")
    private Long total;

    /**
     * 每页长度
     */
    @ApiModelProperty("每页长度")
    private Long size;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private Long pages;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private Long current;

    /**
     * 每页数据
     */
    private List<T> records;

    public ResponsePage(Page page, List<T> list) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.records = list;
    }
}
