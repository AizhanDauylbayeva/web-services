package tests;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.user.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RestAssuredTest {
    @BeforeClass
    public void initTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode() {
        Response rp = given().get("/users").andReturn();
        int actualStatusCode = rp.getStatusCode();
        System.out.println(actualStatusCode);
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeader() {
        Response rp = given().get("/users").andReturn();
        String valueOfContentTypeHeader = rp.getHeader("content-type");
        Assert.assertTrue(valueOfContentTypeHeader.contains("application/json; charset=utf-8"));
    }

    @Test
    public void checkResponseBody() {
        Response rp = given().get("/users").andReturn();
        User[] posts = rp.as(User[].class);
        Assert.assertEquals(posts.length, 10);
    }
}
