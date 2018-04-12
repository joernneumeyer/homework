package restmockserver;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.stubbing.Scenario.STARTED;

public class SimpleTests {
    public static void stub(WireMockServer wireMockServer) {
        wireMockServer.stubFor(get("/students").inScenario("createStudent")
                .whenScenarioStateIs(STARTED)
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"students\":[]}"))
        );

        wireMockServer.stubFor(post("/students").inScenario("createStudent")
                .whenScenarioStateIs(STARTED)
                .withHeader("Content-Type", containing("application/json"))
                .withRequestBody(equalToJson("{\"id\": 1, \"name\":\"Bob\"}"))
                .willReturn(aResponse()
                        .withStatus(201))
                .willSetStateTo("created")
        );

        wireMockServer.stubFor(get("/students").inScenario("createStudent")
                .whenScenarioStateIs("created")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("students.json"))
        );

        wireMockServer.stubFor(get("/students").inScenario("createStudent")
                .whenScenarioStateIs("deleted")
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"students\": []}"))
        );

        wireMockServer.stubFor(delete("/student/1").inScenario("createStudent")
                .whenScenarioStateIs("created")
                .willReturn(aResponse()
                        .withStatus(200)
                )
                .willSetStateTo("deleted")
        );
    }
}
