package org.koma.javamyadmin.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.koma.javamyadmin.common.JavaMyAdminInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * 作为 spring DispatcherServlet 上下文中 bean 的配置类文件
 *
 * @author koma <komazhang@foxmail.com>
 */
@Configuration
//启动　AspectJ AOP
@EnableAspectJAutoProxy
//启用 SpringMVC 组件
@EnableWebMvc
//启用组件扫描
@ComponentScan(basePackages = {"org.koma.javamyadmin"})
public class AppConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    @Autowired
    private JavaMyAdminInterceptor javaMyAdminInterceptor;

    private ApplicationContext applicationContext;

    public AppConfig() {
        super();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 重写该方法以便于使用自定义的 JSON 转换器
     * 这里 json 转换器采用 jackson
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        //设置默认字符集
        jacksonConverter.setDefaultCharset(Charset.forName("UTF-8"));
        //定制序列化,反序列化过程
        jacksonConverter.setObjectMapper(objectMapper());
        //设置支持的媒体类型
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jacksonConverter.setSupportedMediaTypes(mediaTypes);
        converters.add(jacksonConverter);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        ///////////////////////////////自定义序列化特性
        //日期以时间戳格式返回
        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        ///////////////////////////////自定义反序列化特性
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(String.class, new JsonDeserializer<String>() {
            @Override
            public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
                String val = jsonParser.getValueAsString();
                return StringUtils.trimWhitespace(val);
            }
        });
        objectMapper.registerModule(simpleModule);
        //////////////////////////////其它设置
        //将json里的key的小驼峰形式转成小写蛇形(单词间以下划线分割)
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        //将值为null的key丢掉
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    /**
     * 注入拦截器 Bean,可以有多个
     * 实现全局输入,输出数据过滤
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(javaMyAdminInterceptor);
    }

    /**
     * 设置静态资源处理器及路径
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/assets/images/**").addResourceLocations("/WEB-INF/assets/images/");
        registry.addResourceHandler("/assets/css/**").addResourceLocations("/WEB-INF/assets/css/");
        registry.addResourceHandler("/assets/js/**").addResourceLocations("/WEB-INF/assets/js/");
        registry.addResourceHandler("/assets/fonts/**").addResourceLocations("/WEB-INF/assets/fonts/");
    }

    /////////////////////////////////// 配置 thymeleaf 视图解析器
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
        // speed up execution in most scenarios, but might be incompatible
        // with specific cases when expressions in one template are reused
        // across different data types, so this flag is "false" by default
        // for safer backwards compatibility.
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    /////////////////////////////////// 配置 thymeleaf 视图解析器
}
