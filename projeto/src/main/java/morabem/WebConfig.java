package morabem;

import morabem.converters.base64ToFotoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.function.Function;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new base64ToFotoConverter());
    }

    @Bean
    public Function<String, String> currentUrlWithoutParam() {
        return param -> ServletUriComponentsBuilder.fromCurrentRequest().replaceQueryParam(param).toUriString();
    }
}
