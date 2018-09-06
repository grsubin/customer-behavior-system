package com.texas.CustomerBehaviorSystem.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
//@Configuration
//public class SwaggerConfig {
//
//
//    @Bean
//    public Docket productApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.softtech.localLevel"))
//                .paths(regex("/rest.*"))
//                .build()
//                .apiInfo(metaInfo());
//    }
//
//    private ApiInfo metaInfo() {
//
//        ApiInfo apiInfo = new ApiInfo(
//                " LocalLevel Of Nepal",
//                "",
//                "",
//                "",
//                new Contact("", "",
//                        "karkideependra58@gmail.com, yubarajoli77@gmail.com"),
//                "",
//                ""
//        );
//
//        return apiInfo;
//    }
//}

@Profile("enable-swagger")
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SwaggerConfig.class);

	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {

		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

	}

	@Bean	
	public Docket api() {
		LOG.info("swagger implementation");
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.texas.CustomerBehaviorSystem.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaInfo());
				//.securitySchemes(Lists.newArrayList(apiKey()));
	}

	private ApiInfo metaInfo() {
		
		//System.out.println("---------------------------------------------");

		ApiInfo apiInfo = new ApiInfo(" CustomerBehaviorSystem", "", "", "",
				new Contact("", "", ""), "", "");

		return apiInfo;
	}
	
	@Bean
	SecurityConfiguration security() {
		return new SecurityConfiguration(
				"test-app-client-id",
				"test-app-client-secret",
				"test-app-realm",
				"test-app",
				"",
				ApiKeyVehicle.HEADER,
				"Authorization",
				"," /*scope separator*/);
	}
	
	@Bean
	SecurityScheme apiKey() {
		return new ApiKey("AUTHORIZATION", "api_key", "header");
	}


	// @Profile({ "prod", "staging" })
	@Bean
	public FilterRegistrationBean urlFilter() {
		return new FilterRegistrationBean(new SwaggerUrlFilter());

	}
	
	
	
}