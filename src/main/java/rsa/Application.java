package rsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import rsa.filter.TokenAuthenticationFilter;

@SpringBootApplication
@ComponentScan(basePackages= {"rsa"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
    @EnableWebSecurity
    static class Config extends WebSecurityConfigurerAdapter{
    	
    	@Bean
        public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
            return new TokenAuthenticationFilter();
        }

        
        @Override
        protected void configure(HttpSecurity http) throws Exception{

            http.addFilterBefore(tokenAuthenticationFilter(), BasicAuthenticationFilter.class);
        }
    	
    }
}
