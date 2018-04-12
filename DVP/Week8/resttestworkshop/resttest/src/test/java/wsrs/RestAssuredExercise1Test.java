package wsrs;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Make sure the server is running! And port 8080 is free for the server.
 */
public class RestAssuredExercise1Test {

    /**
     * Perform a GET request on localhost:8080/cars and verify the status code is 200
     * and the response header 'Content-Type' is 'application/json'.
     */
    @Test
    public void testSimpleGet() {
        //TODO 4.1 testSimpleGet
        
    }

    /**
     * Perform a GET request on localhost:8080/car/1 and verify the request is successful and
     * the response body has an 'id' field with value '1'.
     */
    @Test
    public void testGetAndVerifyObject() {
        //TODO 4.2 testGetAndVerifyObject
        
    }

    /**
     * Perform a POST request to localhost:8080/students with a JSON object containing an `id`
     * of 1 and a `name` of Bob. Either correctly escape the quotation marks and input the JSON as string or find a more clever approach ;)
     * Make sure the request header 'Content-Type' is 'application/json' and you set that before you set the body.
     * Perform a GET request to that same endpoint and verify you get an object with a property 'students'
     * which is an array of size 1. Do this with the help of Hamcrest Matchers. Some googling may be required.
     * <p>
     * Afterwards,
     * Perform a DELETE request to localhost:8080/student/1.
     * Confirm afterwards that the object is actually gone by performing another GET and checking the size.
     */
    @Test
    public void testStudentAPI() {
        //TODO 4.3 testStudentAPI
        
    }
}
