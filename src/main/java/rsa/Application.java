package rsa;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan({"rsa"})
@ServletComponentScan({"rsa","rsa.filter"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Configuration
    @EnableWebMvc
    static class Config {

		
    	
    }
    
    @Autowired
    private ApplicationContext context;
    
    @Bean
    public CommandLineRunner commandLineRunner() {
    	return (args)->{
    		
//    		String[] names = context.getBeanDefinitionNames();
//    		
//    		Stream<String> stream = Arrays.stream(names);
//    		
//    		stream.parallel().forEach(System.out::println);
//    		
//    		for(String name:names) {
//    			
//    			System.out.println(name);
//    			
//    		}
    		
    	};
    	
    	
    }
}
