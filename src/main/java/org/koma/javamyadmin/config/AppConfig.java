package org.koma.javamyadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 作为 spring DispatcherServlet 上下文中 bean 的配置类文件
 *
 * @author koma <komazhang@foxmail.com>
 */
@Configuration
//启用 SpringMVC 组件
@EnableWebMvc
//启用组件扫描
@ComponentScan(basePackages = {"org.koma.javamyadmin"})
public class AppConfig {
    /**
     * 配置视图解析器
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
}
