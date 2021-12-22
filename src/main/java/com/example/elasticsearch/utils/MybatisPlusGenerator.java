package com.example.elasticsearch.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author Administrator
 */
public class MybatisPlusGenerator {
    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config
                //作者
                .setAuthor("ChenYu")
                //生成路径
                .setOutputDir("D:\\ideaDemo\\elasticsearch-demo\\src\\main\\java")
                //是否文件覆盖，如果多次
                .setFileOverride(true)
                //主键策略
                .setIdType(IdType.AUTO)
                //设置生成的service接口名首字母不用I开头
                .setServiceName("%sService")
                //映射文件中生成默认的baseMap
                .setBaseResultMap(false)
                //swagger注解
                .setSwagger2(false)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(false)
                // XML columList
                .setBaseColumnList(false)
                //生成后打开文件夹
                .setOpen(false)
                // 自定义文件命名，注意 %s 会自动填充表实体属性
                .setControllerName("%sController")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                //映射文件中生成默认的基础列名sql
                .setBaseColumnList(false)
        ;

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)
                .setUrl("jdbc:mysql://localhost:3306/es_demo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true")
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUsername("root")
                .setPassword("root");
        //3.策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) // 全局大写命名
                .setNaming(NamingStrategy.underline_to_camel).setEntityLombokModel(true) //下划线转驼峰
                .setTablePrefix("d_", "w_","c_","sys_")//去除表名前缀
                .setInclude("medicine_info");//表名

        //4.包名策略
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.example.elasticsearch")//父包名
                .setModuleName("test")
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
