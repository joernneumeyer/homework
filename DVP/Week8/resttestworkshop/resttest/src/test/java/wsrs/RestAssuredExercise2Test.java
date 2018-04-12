package wsrs;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

/**
 * Make sure the server is running! And port 8080 is free for the server.
 */
public class RestAssuredExercise2Test {

    /**
     * There is an api endpoint /secureapi that only lets you GET stuff with the proper
     * authorization. This one uses a preemptive BasicAuth to authorize users.
     * The credentials are username 'admin' and password 'admin'.
     * Perform this GET request and verify the status code is 200.
     * Of course also verify that any unauthorized requests result in a 403.
     */
    @Test
    public void testBasicAuthSuccess() {
        //TODO 5.1a testBasicAuth
        
    }

    /**
     * There are two api endpoints /tokenapi and /input. /input only lets you POST stuff
     * with the proper access token that can be GET from /tokenapi. This endpoint requires the
     * username
     * 'giveme' and password 'atoken'.
     * The /tokenapi endpoint will return a token in a JSON object. Parse this token
     * and use it in the request body as JSON object as well to the /input endpoint. Verify both
     * status codes as 200.
     * If you want to, add additional tests to verify you get a 403 when you are unauthorized on the
     * /tokenapi endpoint,
     * and a 400 if you send a bad request to the /input endpoint.
     */
    @Test
    public void testTokenAuth() {
        //TODO 5.2 three tests, see description above
        
    }
}
