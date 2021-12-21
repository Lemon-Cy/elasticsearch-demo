/*
 Navicat Premium Data Transfer

 Source Server         : 120.26.1.75
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 120.26.1.75:3306
 Source Schema         : es_demo

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/12/2021 20:26:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_oil_gas
-- ----------------------------
DROP TABLE IF EXISTS `c_oil_gas`;
CREATE TABLE `c_oil_gas`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '油站信息表',
  `gas_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加油站ID标识',
  `gas_type` int NULL DEFAULT 1 COMMENT '油站类型 1：汽车油站 2：船舶油站',
  `gas_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加油站名称',
  `gas_status` int NULL DEFAULT NULL COMMENT '加油站状态 0 暂停服务，1 正常服务\r\n（默认只有 1 的油站）',
  `gas_logo_big` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '油站图片 url 原图',
  `gas_logo_small` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '油站图片 url 缩略图',
  `gas_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '油站地址',
  `gas_address_longitude` double NULL DEFAULT NULL COMMENT '油站经度',
  `gas_address_latitude` double NULL DEFAULT NULL COMMENT '油站维度',
  `gas_feature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '油站标签 多标签时使用“，”隔开',
  `province_code` int NULL DEFAULT NULL COMMENT '省份编码 国际码',
  `province_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '油站所在省份',
  `city_code` int NULL DEFAULT NULL COMMENT '城市编码 国际码',
  `city_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '油站所在市',
  `county_code` int NULL DEFAULT NULL COMMENT '区县码 国际码',
  `county_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '油站所在区/县',
  `gas_source_id` int NULL DEFAULT NULL COMMENT '油站来源0-能链；10-上汽联名会员',
  `platform_id` int NULL DEFAULT NULL COMMENT '所属平台ID',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_timestamp` bigint NULL DEFAULT NULL COMMENT '更新时间戳',
  `add_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6918 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for medicine_info
-- ----------------------------
DROP TABLE IF EXISTS `medicine_info`;
CREATE TABLE `medicine_info`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `drug_form` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '剂型',
  `medicine_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '药品类别',
  `gname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '药品通用名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼音',
  `medicine_standard_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '本位码',
  `medicine_barcode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '药品条形码',
  `medicine_component` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成分',
  `drug_amount` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '剂量',
  `medicine_indication` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '适应症',
  `medicine_avoid` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用药禁忌',
  `medicine_response` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '不良反应',
  `medicine_toxicology` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '药理毒理',
  `units` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '包装单位',
  `agentia_units` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计量单位',
  `medicine_unit_mini` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最小单位',
  `medicine_usage_remark` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用法用量',
  `medicine_notes` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注意事项',
  `medicine_preg` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '哺乳用药',
  `medicine_child` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '儿童用药',
  `medicine_oldman` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '老人用药',
  `medicine_interaction` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '药物相互作用',
  `medicine_excessive` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '药物过量',
  `medicine_otc` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否处方药0否',
  `medicine_base` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否基药0否',
  `medicine_place` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国内外0国内1国外',
  `medicine_rates` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格转化率',
  `drug_spec` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注册规格',
  `drug_pzwh` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '批准文号',
  `drug_from` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生产企业',
  `medicine_instruction` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '说明书',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gname`(`gname`) USING BTREE,
  INDEX `code`(`code`) USING BTREE,
  INDEX `medicine_barcode`(`medicine_barcode`) USING BTREE,
  INDEX `medicine_standard_code`(`medicine_standard_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 224833 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
