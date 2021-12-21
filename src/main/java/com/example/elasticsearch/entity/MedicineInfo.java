package com.example.elasticsearch.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * <p>
 * 药物信息
 * </p>
 *
 * @author ChenYu
 * @since 2021-12-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document(indexName = "medicine_info_index")
public class MedicineInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Integer id;

    /**
     * 剂型
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String drugForm;

    /**
     * 药品类别
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String medicineType;

    /**
     * 药品通用名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String gname;

    /**
     * 拼音
     */
    @Field
    private String code;

    /**
     * 本位码
     */
    @Field
    private String medicineStandardCode;

    /**
     * 药品条形码
     */
    @Field
    private String medicineBarcode;

    /**
     * 成分
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String medicineComponent;

    /**
     * 剂量
     */
    @Field
    private String drugAmount;

    /**
     * 适应症
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String medicineIndication;

    /**
     * 用药禁忌
     */
    @Field
    private String medicineAvoid;

    /**
     * 不良反应
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String medicineResponse;

    /**
     * 药理毒理
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String medicineToxicology;

    /**
     * 包装单位
     */
    @Field
    private String units;

    /**
     * 计量单位
     */
    @Field
    private String agentiaUnits;

    /**
     * 最小单位
     */
    @Field
    private String medicineUnitMini;

    /**
     * 用法用量
     */
    @Field
    private String medicineUsageRemark;

    /**
     * 注意事项
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String medicineNotes;

    /**
     * 哺乳用药
     */
    @Field
    private String medicinePreg;

    /**
     * 儿童用药
     */
    @Field
    private String medicineChild;

    /**
     * 老人用药
     */
    @Field
    private String medicineOldman;

    /**
     * 药物相互作用
     */
    @Field
    private String medicineInteraction;

    /**
     * 药物过量
     */
    @Field
    private String medicineExcessive;

    /**
     * 是否处方药0否
     */
    @Field
    private String medicineOtc;

    /**
     * 是否基药0否
     */
    @Field
    private String medicineBase;

    /**
     * 国内外0国内1国外
     */
    @Field
    private String medicinePlace;

    /**
     * 规格转化率
     */
    @Field
    private String medicineRates;

    /**
     * 注册规格
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String drugSpec;

    /**
     * 批准文号
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String drugPzwh;

    /**
     * 生产企业
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String drugFrom;

    /**
     * 说明书
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String medicineInstruction;

}
