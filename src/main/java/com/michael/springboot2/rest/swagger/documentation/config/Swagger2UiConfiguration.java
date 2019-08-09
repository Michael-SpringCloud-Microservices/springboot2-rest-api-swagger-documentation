/**
 * 
 */
package com.michael.springboot2.rest.swagger.documentation.config;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author Michael Philomin Raj 
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2UiConfiguration{
	

	private static final Set<String> DEFAULT_PRODUCES = new HashSet<>(Arrays.asList("application/json"));
	private static final Set<String> DEFAULT_CONSUMES = new HashSet<>(Arrays.asList("application/json"));
	
	@Bean
	@ConditionalOnMissingBean(SwaggerAppParameters.class)
	public SwaggerAppParameters swaggerAppParameters() {
		return new DefaultSwaggerAppParameters();
	}
	
	// Bean - Docket
	@Bean 
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.michael.springboot2.rest.swagger.documentation.controller"))
		//.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
		.paths(PathSelectors.any())
		//.paths(regex("/student.*"))
		.build().apiInfo(metaData())
		.consumes(DEFAULT_CONSUMES)
		.produces(DEFAULT_PRODUCES)
		.globalOperationParameters(swaggerAppParameters().globalOperationParameters());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
		.title("Spring Boot RESTful API")
		.description("\"RESTful API for students details management\"")
		.version("version 1.0")
		.contact(new Contact("Michael Philomin Raj", "", 
				"michaelraj.p@gmail.com")).build();
	}
}
