package cn.chenzecheng.alan.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * @author JacksonChen
 * @date 2022/7/17 21:59
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        // 更换配置，进行代码生成
        String ipPort = "42.194.206.95:3306";
        String dataBaseName = "alan_bank";
        String username = "alan_bank";
        String password = "5HDdzsH2fikXzbP7";

        String moduleName = "bank";
        String[] tables = {"t_bank_account"};

        String url = "jdbc:mysql://" + ipPort + "/" + dataBaseName + "?useUnicode=true&characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=Asia/Shanghai";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author("JacksonChen")
                            // 开启 swagger 模式
//                            .enableSwagger()
                            // 覆盖已生成文件
                            .fileOverride()
                            // 指定输出目录
                            .outputDir("D://codeGen");
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent("cn.chenzecheng.alan")
                            // 设置父包模块名
                            .moduleName(moduleName)
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://codeGen/mapper"));
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tables)
                            // 设置过滤表前缀
                            .addTablePrefix("t_");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
