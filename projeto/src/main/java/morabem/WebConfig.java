package morabem;

import morabem.converters.base64ToFotoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new base64ToFotoConverter());
    }
}
