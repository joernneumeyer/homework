package wsrs;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.http.ContentType;
import org.junit.Rule;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author rickj
 */
public class WiremockExercise2Test {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9876);
    WiremockExercise2 wm2 = new WiremockExercise2();

    @Test
    public void testAbsentStub() {
        wm2.absentStub();

        given().
                header("color", "black").
                when().
                get("http://localhost:9876/getVehicle").
                then().
                assertThat().
                body(equalTo("valid get request"));
    }

    @Test
    public void testMotorbikesStub() {
        wm2.motorbikesStub();

        given().when().get("http://localhost:9876/motorbikes/randomrequest").
                then().
                assertThat().
                statusCode(401);

        given().when().get("http://localhost:9876/motorbikes/yamaha").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void testGetAllCarsStub() {
        wm2.getAllCarsStub();

        given().when().get("http://localhost:9876/getAllCars").
                then().
                assertThat().
                contentType(ContentType.JSON);
    }

    @Test
    public void testAuthenticationStub() {
        wm2.authenticationStub();

        given().
                auth().
                preemptive().
                basic("wiremock", "restassured").
                when().
                get("http://localhost:9876/authenticateMe").
                then().
                assertThat().
                body(equalTo("successfully authenticated"));
    }

    @Test
    public void testCookieStub() {
        wm2.cookieStub();

        given().
                cookie("Country", "Netherlands").
                when().
                get("http://localhost:9876/cookieTime").
                then().
                assertThat().
                body(equalTo("Get Succeeded"));
    }
}
