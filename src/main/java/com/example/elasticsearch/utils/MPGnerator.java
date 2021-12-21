package com.example.elasticsearch.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MPGnerator {
    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setAuthor("ChenYu") //作者
                .setOutputDir("D:\\ideaDemo\\elasticsearch-demo\\src\\main\\java")  //生成路径
//                .setOutputDir("C:\\Users\\Joshua\\OneDrive\\workspace\\untra\\Java\\czs\\czs-wechat\\src\\main\\java")//生成路径
                .setFileOverride(true)//是否文件覆盖，如果多次
                .setIdType(IdType.AUTO) //主键策略
                .setServiceName("%sService") //设置生成的service接口名首字母不用I开头
                .setBaseResultMap(true)//映射文件中生成默认的baseMap
                .setBaseColumnList(true)//映射文件中生成默认的基础列名sql
                .setSwagger2(false);

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://139.9.132.42:53306/transpopo_idc?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("transpopo_idc")
                .setPassword("6fd76TehTmi2XfJc");
        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel).setEntityLombokModel(true) //下划线转驼峰
                .setTablePrefix("d_", "w_","c_","sys_")//去除表名前缀
                .setInclude("c_oil_gas");//表名

        //4.包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.example.elasticsearch.test")//父包名
                .setModuleName("wechat")
                .setController(null)
                .setEntity("entity")
                .setService("service")
                .setMapper("mapper")
                .setXml("xml_mapper");
        //5.整合配置
        AutoGenerator ag = new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);
        ag.execute();
    }
}
