package wsrs;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;

/**
 * @author rickj
 */
public class WiremockExercise1 {
  WireMockServer wireMockServer;
  public WiremockExercise1() {
     wireMockServer = new WireMockServer();
        
        wireMockServer.start();
  }

    /*
    * Here you are going to create your first stub.
    * Make sure the stub listens to /cars/all. Don't forget the / in front of the URL.
    * The stub should respond to all GET request with a 200 HTTP status code.
    */
    public void getCarsStub() {
        //TODO 1.1 getCarsStub
        wireMockServer
          .stubFor(get("/cars/all")
          .willReturn(
            aResponse()
              .withStatus(200)
          ));
    }

    /*
    * Here you will be making a stub that listens
    * to /cars/25-BB-83 and responds to GET requests with the following body
    * 'brand: lamborghini' as a simple string.
    */
    public void getSpecificCarStub(){
        //TODO 1.2 getSpecificCarStub
        wireMockServer
          .stubFor(get("/cars/25-BB-83")
          .willReturn(
            aResponse()
              .withBody("brand: lamborghini")
          ));
    }

    /*
    * Now it is time to create a stub that also returns a 200 HTTP response for GET requests.
    * This time it should have a status message which is 'valid response'.
    * Make sure it listens to /occasions.
    */
    public void getOccasionsStub(){
        //TODO 1.3 getOccasionsStub
        wireMockServer
          .stubFor(get("/occasions")
          .willReturn(
            aResponse()
              .withStatusMessage("valid response")
                  .withStatus(200)
          ));
    }

    /*
    * Mock the endpoint /cars/new which listens to POST requests and only to requests containing a body
    * that is 'brand: audi, color: black, license: 22-AA-33' as simple string.
    * You'll respond with a status 200 and body 'car inserted'
    * HINT: containing("...")
    */
    public void postNewCar() {
        //TODO 1.4 postNewCar
        wireMockServer
          .stubFor(post("/cars/new")
          //.withRequestBody(containing("brand: audi, color: black, license: 22-AA-33"))
          .willReturn(
            aResponse()
              .withBody("car inserted")
              .withStatus(200)
          ));
    }
}
