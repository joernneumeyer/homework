package restmockserver;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class AuthTests {
    public static void stub(WireMockServer wireMockServer) {
        wireMockServer.stubFor(get("/secureapi")
                .willReturn(aResponse()
                    .withStatus(403)));

        wireMockServer.stubFor(get("/secureapi")
                .withBasicAuth("admin", "admin")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("successfully authenticated")
                )
        );

        wireMockServer.stubFor(get("/tokenapi")
                .willReturn(aResponse()
                        .withStatus(403)
                ));

        wireMockServer.stubFor(get("/tokenapi")
                .withBasicAuth("giveme", "atoken")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("{ \"token\": \"tokenAzB6vb566V4rcYJ8b5E\" }")
                        .withHeader("Content-Type", "application/json")
                ));

        wireMockServer.stubFor(post("/input")
                .willReturn(aResponse()
                        .withStatus(400)
                ));

        wireMockServer.stubFor(post("/input")
                .withRequestBody(containing("{ \"token\": \"tokenAzB6vb566V4rcYJ8b5E\" }"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                ));
    }
}
