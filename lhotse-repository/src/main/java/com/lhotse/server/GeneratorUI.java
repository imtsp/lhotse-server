package com.lhotse.server;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.github.davidfantasy.mybatisplus.generatorui.GeneratorConfig;
import com.github.davidfantasy.mybatisplus.generatorui.MybatisPlusToolsApplication;
import com.github.davidfantasy.mybatisplus.generatorui.mbp.NameConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "com.github.davidfantasy.mybatisplus.generatorui", exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {"com.github.davidfantasy.mybatisplus.generatorui"}, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MybatisPlusToolsApplication.class}))
public class GeneratorUI {

    @Value("${spring.datasource.druid.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.druid.username}")
    private String username;

    @Value("${spring.datasource.druid.password}")
    private String password;

    @Value("${spring.datasource.druid.driver-class-name}")
    private String driverClassName;

    @Bean
    public GeneratorConfig generatorConfig() {
        return GeneratorConfig.builder()
                .jdbcUrl(jdbcUrl)
                .userName(username)
                .password(password)
                .nameConverter(new NameConverter() {
                    @Override
                    public String entityNameConvert(String tableName) {
                        if (StringUtils.isEmpty(tableName)) {
                            return StringUtils.EMPTY;
                        } else {
                            return StrUtil.upperFirst(StrUtil.toCamelCase(tableName.toLowerCase()));
                        }
                    }

                    @Override
                    public String serviceNameConvert(String tableName) {
                        return NameConverter.super.serviceNameConvert(tableName);
                    }

                    @Override
                    public String propertyNameConvert(String fieldName) {
                        return NameConverter.super.propertyNameConvert(fieldName);
                    }

                    @Override
                    public String mapperNameConvert(String tableName) {
                        return NameConverter.super.mapperNameConvert(tableName);
                    }

                    @Override
                    public String serviceImplNameConvert(String tableName) {
                        return NameConverter.super.serviceImplNameConvert(tableName);
                    }

                    @Override
                    public String mapperXmlNameConvert(String tableName) {
                        return NameConverter.super.mapperXmlNameConvert(tableName);
                    }

                    @Override
                    public String outputFileNameConvert(String fileType, String tableName) {
                        return NameConverter.super.outputFileNameConvert(fileType, tableName);
                    }
                })
                .typeConvert(new MySqlTypeConvert())
                .driverClassName(driverClassName)
                .port(8089)
                .basePackage("com.lhotse.server.repository")
                .build();
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> containerConfig(GeneratorConfig generatorConfig) {
        return factory -> {
            if (generatorConfig.getPort() != null) {
                factory.setPort(generatorConfig.getPort());
            } else {
                factory.setPort(8080);
            }
        };
    }

    public static void main(String[] args) {
        Map<String, Object> props = new HashMap<>();
        props.put("spring.resources.static-locations", "classpath:/generator-ui/");
        new SpringApplicationBuilder()
                .properties(props)
                .sources(GeneratorUI.class)
                .run(args);
    }
}
