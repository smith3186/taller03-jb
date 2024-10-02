package com.vichamalab.serenity.screenplay;

import org.junit.jupiter.api.extension.ExtendWith;

import com.vichamalab.serenity.dto.ProductRequest;

import io.cucumber.java.Before;
//import io.cucumber.java.Before;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.thucydides.model.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("SerenityRestInteractions")
@ExtendWith(SerenityJUnit5Extension.class)
public class ScreenplayRestInteractionsTestSuite {
	private EnvironmentVariables environmentVariables; 
	
	@Test
	@Tag("rest")
	@DisplayName("Listar productos")
	public void listar_productos() {
		String baseUrl = environmentVariables.optionalProperty("restapi.baseurl") 
                .orElse("");
				
	    Actor authorizeUser = Actor.named("Usuario Autorizado")
	                     .whoCan(CallAnApi.at(baseUrl));

	    authorizeUser.attemptsTo(
	            Get.resource("api/v1/product/")
	    );
	    authorizeUser.should(
	            seeThatResponse("El codigo de respuesta es 200 y el estado es verdadero",
	                    response -> response.statusCode(200)
	                                        .body("status",equalTo(true)))
	    );
	}
	
	@Test
	@Tag("rest")
	@DisplayName("crear producto")
	public void crear_producto() {
		ProductRequest productRequest= ProductRequest.builder()
				.name("Iphone 13 Premium")
				.description("Telefono alta gama")
				.price(1400)
				.build();

	    Actor authorizeUser = Actor.named("Usuario Autorizado")
	                     .whoCan(CallAnApi.at("http://localhost:8081/"));

	    authorizeUser.attemptsTo(
	            Post.to("api/v1/product/")
	            .with(request -> request.header("Content-Type", "application/json")
                        .body(productRequest) 
)
	    );
	    authorizeUser.should(
	            seeThatResponse("El codigo de respuesta es 200 y el estado es verdadero",
	                    response -> response.statusCode(201)
	                                        .body("status",equalTo(true)))
	    );
	}
}
