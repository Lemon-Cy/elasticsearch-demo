package com.example.elasticsearch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 油站信息类
 * </p>
 *
 * @author ChenYu
 * @since 2021/12/17 10:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "oil_gas_index", replicas = 0)
@TableName("c_oil_gas")
public class OilGas implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 油站信息表
     */
    @Id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 加油站ID标识
     */
    @Field
    private String gasId;

    /**
     * 油站类型 1：汽车油站 2：船舶油站
     */
    @Field
    private Integer gasType;

    /**
     * 加油站名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String gasName;

    /**
     * 加油站状态 0 暂停服务，1 正常服务	（默认只有 1 的油站）
     */
    @Field
    private Integer gasStatus;

    /**
     * 油站图片 url 原图
     */
    @Field
    private String gasLogoBig;

    /**
     * 油站图片 url 缩略图
     */
    @Field
    private String gasLogoSmall;

    /**
     * 油站地址
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String gasAddress;

    /**
     * 油站经度
     */
    @Field
    private Double gasAddressLongitude;

    /**
     * 油站维度
     */
    @Field
    private Double gasAddressLatitude;

    @TableField(exist = false)
    @GeoPointField
    private String location;

    /**
     * 油站标签 多标签时使用“，”隔开
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String gasFeature;

    /**
     * 省份编码 国际码
     */
    @Field
    private Integer provinceCode;

    /**
     * 油站所在省份
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String provinceName;

    /**
     * 城市编码 国际码
     */
    @Field
    private Integer cityCode;

    /**
     * 油站所在市
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String cityName;

    /**
     * 区县码 国际码
     */
    @Field
    private Integer countyCode;

    /**
     * 油站所在区/县
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String countyName;

    /**
     * 油站来源0-能链；10-上汽联名会员
     */
    @Field
    private Integer gasSourceId;

    /**
     * 所属平台ID
     */
    @Field
    private Integer platformId;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 更新时间戳
     */
    @Field
    private Long updateTimestamp;

    /**
     * 添加时间
     */
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;


}
