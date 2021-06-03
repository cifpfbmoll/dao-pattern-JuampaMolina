package org.acme.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
@Transactional
public class FruitResourceTest {

        @Test
        public void testListEndpoint() {
                List<Map<String, Object>> fruits = given().contentType(ContentType.JSON).when().get("/fruits")
                                .as(new TypeRef<List<Map<String, Object>>>() {
                                });

                Assertions.assertThat(fruits).hasSize(2);
        }

        @Test
        public void testList() {
                given().contentType(ContentType.JSON).when().get("/fruits").then().statusCode(200).body("$.size()",
                                is(2), "name", containsInAnyOrder("Strawberry", "Orange"), "description",
                                containsInAnyOrder("Winter fruit", "Summer fruit"));
        }

        @Test
        public void testGetFruit() {
                given().pathParam("name", "Orange").when().get("/fruits/{name}").then().contentType(ContentType.JSON)
                                .body("name", equalTo("Orange"));

                given().pathParam("name", "Pera").when().get("/fruits/{name}").then().contentType(ContentType.JSON)
                                .statusCode(404).body(equalTo("The fruit Pera doesn't exist."));
        }

        @Test
        public void addDeleteFruitTest() {
                given().body("{\"name\": \"Watermelon\", \"description\": \"Best summer fruit\"}")
                                .header("Content-Type", MediaType.APPLICATION_JSON).when().post("/fruits").then()
                                .statusCode(200).body("name", equalTo("Watermelon"));

                given().contentType(ContentType.JSON).when().delete("/fruits/Watermelon").then().statusCode(200)
                                .body("name", equalTo("Watermelon"));

                given().contentType(ContentType.JSON).when().delete("/fruits/Watermelon").then().statusCode(404)
                                .body(equalTo("The fruit Watermelon doesn't exist."));
        }

}