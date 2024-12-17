package it.spring.ticket.platform.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // Configura Jackson per serializzazione/deserializzazione JSON
        ObjectMapper objectMapper = new ObjectMapper(); // Crea un ObjectMapper
        // Personalizza l'ObjectMapper se necessario, ad esempio con il formato delle date
        converters.add(new MappingJackson2HttpMessageConverter(objectMapper)); 
    }
}
