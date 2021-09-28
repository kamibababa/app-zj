package com.cxy.zjbg;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xing-yu-chen
 * @Project: zj-bg
 * @Description: mybatis-plus 代码生成器
 * @Data:Created in 2021/7/12 9:26 下午
 */
public class CodeGenerator {
    public static void main(String[] args) {
        //创建generator对象
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        //数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        // 配置数据库 url 地址
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/zj_app?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        // 配置数据库连接用户名
        dataSourceConfig.setUsername("root");
        // 配置数据库连接密码
        dataSourceConfig.setPassword("123456");
        // 配置数据库驱动
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        autoGenerator.setDataSource(dataSourceConfig);

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 代码生成的目录
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
        // 配置是否打开目录，false 为不打开
        globalConfig.setOpen(false);
        //作者信息
        globalConfig.setAuthor("cxy");
        //实体属性 Swagger2 注解
        globalConfig.setSwagger2(true);
        // 默认生成的 service 会有 I 前缀
        globalConfig.setServiceName("%sService");
        //设置时间格式为常规Date
        globalConfig.setDateType(DateType.ONLY_DATE);
        autoGenerator.setGlobalConfig(globalConfig);

        //包信息
        PackageConfig packageConfig = new PackageConfig();
        // 配置父包名
        packageConfig.setParent("com.cxy.zjbg");
        // 配置模块名
        //packageConfig.setModuleName("");
        // 配置 controller 包名
        packageConfig.setController("controller");
        // 配置 service 包名
        packageConfig.setService("service");
        // 配置 service.impl 包名
        packageConfig.setServiceImpl("service.impl");
        // 配置 mapper 包名
        packageConfig.setMapper("mapper");
        // 配置 entity 包名
        packageConfig.setEntity("entity");
        //设置时间
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 velocity
         String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return System.getProperty("user.dir") + "/src/main/resources/mappers/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        autoGenerator.setCfg(cfg);

        //配置策略（数据库表配置）
        StrategyConfig strategyConfig = new StrategyConfig();
        // 配置 lombok 模式
        strategyConfig.setEntityLombokModel(true);
        // 配置数据表与实体类名之间映射的策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 配置数据表的字段与实体类的属性名之间映射的策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 配置 rest 风格的控制器（@RestController）
        strategyConfig.setRestControllerStyle(true);
        // 配置驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 此处的表名为 t_users,去除前缀后剩下为 users。
        strategyConfig.setTablePrefix("t_");
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
