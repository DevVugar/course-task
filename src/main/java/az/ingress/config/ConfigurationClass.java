package az.ingress.config;


import az.ingress.model.entity.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration

public class ConfigurationClass {

    @Bean
    @Scope("prototype")
    public Category getBrand(){
        return new Category();
    }
}
