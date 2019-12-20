package com.godxvincent.springweb.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.godxvincent.springweb.component.RequestTimeInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer   {

	// Inyectar el componente exampleComponent - Indica a spring que se va a inyectar un componente cargado en memoria.
		@Autowired
		// usa el calificador o nombre que le pusimos en @Component("exampleComponent")
		@Qualifier("requestTimeInterceptor")
		private RequestTimeInterceptor requestTimeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(requestTimeInterceptor);
	}
	
	
}
