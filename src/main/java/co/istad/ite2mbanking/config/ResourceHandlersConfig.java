package co.istad.ite2mbanking.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlersConfig implements WebMvcConfigurer {
    @Value("${media.server-path}")
    private  String serverPath;
    @Value("${media.client-path}")
    private  String clientPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry
                .addResourceHandler(clientPath)
                .addResourceLocations("file:"+serverPath);
    }
}
