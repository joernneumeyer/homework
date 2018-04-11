package wsrs;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author rickj
 */
public class WiremockExercise2 {
  WireMockServer wireMockServer;
  public WiremockExercise2() {
     wireMockServer = new WireMockServer();
        
        wireMockServer.start();
  }

    /*
     * In this method you will create a stub that only responds to GET requests to /getVehicle
     * which do NOT contain the header 'vehicletype', but do have a header
     * 'color' with value 'black'.
     * You will respond with a body that is 'valid get request'.
     * Hint: absent()
     */
    public void absentStub() {
        //TODO 2.1 absentStub
        wireMockServer.stubFor(
                get("/getVehicle")
                .withHeader("vehicletype", absent())
                .withHeader("color", matching("black"))
                .willReturn(
                  aResponse()
                  .withBody("valid get request")
                )
        );
    }


    /*
     * Here you are going to create 2 stubs that overlap.
     * One stub will listen to GET requests to all the endpoints starting with /motorbikes/ and will
     * return a 401 status.
     * The second stub should override the first one and only listen to /motorbikes/yamaha and
     * respond with a 200 status.
     */
    public void motorbikesStub() {
        //TODO 2.2.a get all (motorbikes) case
        wireMockServer.stubFor(
                get("/motorbikes")
                .willReturn(
                  aResponse()
                  .withStatus(401)
                ));
        
        wireMockServer.stubFor(
                get("/motorbikes/yamaha")
                .willReturn(
                  aResponse()
                  .withStatus(200)
                ));
        //TODO 2.2.b Specific case for yamaha
        
    }

    /*
     * In this stub you'll be responding to a GET request to /getAllCars.
     * You'll answer with a HTTP status code 200 and a response that contains a header.
     * This header will specify that the Content-Type is 'application/json'.
     */
    public void getAllCarsStub() {
        //TODO 2.3 getAllCarsStub
        wireMockServer.stubFor(
                get("/getAllCars")
                .willReturn(
                  aResponse()
                  .withStatus(200)
                  .withHeader("Content-Type", "application/json")
                ));
    }

    /*
     * Authentication time! In this stub, make sure all GET requests to /authenticateMe are
     * accompanied by basic authentication. The username is wiremock and the password
     * should be restassured. Respond with a body equal to 'successfully authenticated' only if the
     * authentication is valid.
     */
    public void authenticationStub() {
        //TODO 2.4 authenticationStub
        wireMockServer.stubFor(
                get("/authenticateMe")
                .withBasicAuth("wiremock", "restassured")
                .willReturn(
                  aResponse()
                  .withBody("successfully authenticated")
                ));
    }

    /*
     * Time to check for cookies! Make sure the GET request in this stub comes with a cookie called
     * Country.
     * The value should be Netherlands.
     * Respond with a body equal to "Get Succeeded". Let the stub listen to /cookieTime
     */
    public void cookieStub() {
        //TODO 2.5 cookieStub
        wireMockServer.stubFor(
                get("/cookieTime")
                        .withCookie("Country", matching("Netherlands"))
                .willReturn(
                  aResponse()
                    .withBody("Get Succeeded")
                        
                ));
    }
}
