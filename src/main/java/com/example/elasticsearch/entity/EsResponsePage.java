package com.example.elasticsearch.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

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
public class EsResponsePage<T> implements Serializable {

    /**
     * 总条数
     */
    @ApiModelProperty("总条数")
    private Long total;

    /**
     * 每页长度
     */
    @ApiModelProperty("每页长度")
    private Integer size;

    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private Integer pages;

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    private Integer current;

    /**
     * 每页数据
     */
    private List records;

    public EsResponsePage(RequestPage requestPage,Page resultPage) {
        this.current = requestPage.getCurrent();
        this.size = requestPage.getSize();
        this.total = resultPage.getTotalElements();
        this.pages = resultPage.getTotalPages();
        this.records = resultPage.getContent();
    }
}
