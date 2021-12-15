package TG;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class SampleTest {

     final String baserURI = "https://reqres.in/api";

    @Test
    public void test_ListUsersReturnsSixRecordsPerPage() {
          given().
                  baseUri(baserURI).
          when().
                  get("/users?page=2").
          then().
                  assertThat().
                  statusCode(200).
                  body("data.id",hasSize(6));

    }

    @Test
    public void test_SingleUserNotFound() {
        given().
                baseUri(baserURI).
        when().
                get("/users/23").
        then().
                assertThat().
                statusCode(404);
    }

    @Test
    public void test_CreateAUser() {
        String data = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        Response response =
      given().
              baseUri(baserURI).
              and().
              body(data).
        when().
              post("/users").
        then().
              extract().response();
        System.out.println(response.getBody().asString());
        Assert.assertEquals(201,response.statusCode());
    }

    @Test
    public void test_InvalidLogin() {
        String data = "{ email: peter@klaven}" ;
        Response response =
                given().
                        baseUri(baserURI).
                        and().
                        body(data).
                        when().
                        post("/login").
                        then().
                        extract().response();
        Assert.assertEquals("Missing email or username",response.jsonPath().getString("error"));
        Assert.assertEquals(400,response.statusCode());

    }

}
