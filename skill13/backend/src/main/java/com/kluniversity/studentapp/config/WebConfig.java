package com.kluniversity.studentapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Serves the React production build from Spring Boot's static resources.
 * Place the React `build/` output into src/main/resources/static/
 * Then all non-API routes will fall through to index.html (SPA support).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Serve static files from classpath:/static/
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward unknown routes to React's index.html for SPA routing
        registry.addViewController("/{path:[^\\.]*}").setViewName("forward:/index.html");
    }
}
