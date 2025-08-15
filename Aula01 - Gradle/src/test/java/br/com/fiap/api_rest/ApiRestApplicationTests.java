package br.com.fiap.api_rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@OpenAPIDefinition(info = @Info(title = "API DE CLIENTES",description = "Exemplo de API RESTful com Swagger",version = "0.0.1"))
class ApiRestApplicationTests {

	@Test
	void contextLoads() {
	}

}