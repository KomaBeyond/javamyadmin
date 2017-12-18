package org.koma.javamyadmin.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 在 servlet3.0 的规范中,容器会查找所有实现了 ServletContainerInitializer 接口的类
 * 如果存在则会使用该实现类来配置 servlet 容器
 *
 * 在 spring 中存在两种上下文,
 * 一是 DispatcherServlet 的上下文, 加载包含Web组件的bean,如控制器、视图解析器以及处理器映射
 * 二是 ContextLoaderListener 的上下文, 加载应用中的其他bean, 这些bean通常是驱动应用后端的中间层和数据层组件
 * 这样,实际上在 spring 应用中存在两种性质的 bean, 他们负责不同类型的事情
 *
 * @author koma <komazhang@foxmail.com>
 */
public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        /**
         * 告诉 spring ContextLoaderListener 上下文加载指定配置类文件中的 bean, 可以有多个
         */
        return new Class<?>[] {RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        /**
         * 告诉 spring DispatcherServlet 上下文加载指定配置类文件中的 bean, 可以有多个
         */
        return new Class<?>[] {AppConfig.class};
    }

    protected String[] getServletMappings() {
        /**
         * 将路径 "/" 映射到 DispatcherServlet 上
         * 这样 DispatcherServlet 能够处理所有的的请求
         */
        return new String[] {"/"};
    }
}
