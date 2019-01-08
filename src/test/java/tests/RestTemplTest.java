package tests;

import model.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RestTemplTest {

    @Test
    public void checkStatusCode() {
        RestTemplate restTempl = new RestTemplate();
        ResponseEntity<User[]> response = restTempl.getForEntity("http://jsonplaceholder.typicode.com/users", User[].class);
        int actualStatusCode = response.getStatusCodeValue();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeader() {
        RestTemplate restTempl = new RestTemplate();
        ResponseEntity<User[]> response = restTempl.getForEntity("http://jsonplaceholder.typicode.com/users", User[].class);

        List<String> valueOfContentTypeHeader = response.getHeaders().get("content-type");
        Assert.assertTrue(valueOfContentTypeHeader.get(0).contains("application/json; charset=utf-8"));
    }

    @Test
    public void checkResponseBody() {
        RestTemplate restTempl = new RestTemplate();
        ResponseEntity<User[]> response = restTempl.getForEntity("http://jsonplaceholder.typicode.com/users", User[].class);
        Assert.assertEquals(response.getBody().length, 10);
    }
}
