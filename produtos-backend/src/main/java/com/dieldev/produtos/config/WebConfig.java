package com.dieldev.produtos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// Permitir requisições de qualquer origem (ajuste conforme necessário)
		registry.addMapping("/**") // Permitindo CORS para todas as rotas
				.allowedOrigins("http://localhost:3000") // Substitua com a URL do seu frontend
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Defina os métodos HTTP permitidos
				.allowedHeaders("*") // Permite todos os cabeçalhos
				.allowCredentials(true); // Permite enviar cookies, se necessário
	}
}
