package com.example.elasticsearch.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 分页参数
 * </p>
 *
 * @author ChenYu
 * @since 2021/4/9 15:06
 */
@ApiModel(description = "分页参数")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestPage implements Serializable {

    /**
     * 当前页码 默认为1
     */
    @ApiModelProperty("当前页码 默认为1")
    private Integer current;

    /**
     * 每页显示条数 默认为10
     */
    @ApiModelProperty("每页显示条数 默认为10")
    private Integer size;

    public Integer getCurrent() {
        if(current == null) return 1;
        return current;
    }

    public Integer getSize() {
        if(size == null) return 10;
        return size;
    }
}
