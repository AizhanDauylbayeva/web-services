package tests;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.user.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DeserializationTest {

    @Test
    public void deserializationTest() throws IOException {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users";
        RequestSpecification request = given();
        Response response = request.get();

        ObjectMapper mapper = new ObjectMapper();
        List<User> res = Lists.newArrayList(
                mapper.readValue(response.body().asString(), User[].class));

        Assert.assertEquals("Bret", res.get(0).getUsername());
    }

    @Test
    public void serializationTest() throws IOException {

    }
}
