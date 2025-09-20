package dev.markgm.microservices.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// to use non-static method with @BeforeAll
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductServiceApplicationTests {
    @LocalServerPort
    private int port;

    @BeforeAll
    void setup() {
        RestAssured.baseURI = "http://localhost";
        // apache tomcat server port
        RestAssured.port = port;
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
                {
                  "name": "Galaxy S28 Ultra",
                  "description": "Flagship android phone",
                  "price": 87000
                }
                """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("api/v1/products")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.is("Galaxy S28 Ultra"))
                .body("description", Matchers.is("Flagship android phone"))
                .body("price", Matchers.is(87000));
    }
}
