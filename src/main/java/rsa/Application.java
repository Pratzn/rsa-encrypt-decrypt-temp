package rsa;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import rsa.interceptor.Interceptor;
import rsa.service.AsynService;

@SpringBootApplication
@ComponentScan({"rsa"})
@ServletComponentScan({"rsa","rsa.filter"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
    @EnableWebMvc
    static class Config implements WebMvcConfigurer{

    	@Bean
    	public AntPathMatcher antPathMatcher() {
    		return new AntPathMatcher();
    	}
    	
    	@Override
        public void addInterceptors(final InterceptorRegistry registry) {
            registry.addInterceptor(new Interceptor()).addPathPatterns("/*");
        }
    	
    	@Bean
    	RestTemplate restTemplate() {
    		return new RestTemplateBuilder().build();
    	}
    	
    }
    
    @Autowired
    private AsynService service;
    
    
    @Bean
    public CommandLineRunner commandLineRunner() {
    	return (args)->{
    		for(int i = 0 ; i < 5 ; i++) {
    			System.out.println(Thread.currentThread().getName()+"asyn:"+i);
    			service.asynMethod();
    		}
    		
    		
    		
    		
    	};
    	
    	
    }
}
