package restmockserver;

import com.github.tomakehurst.wiremock.WireMockServer;

public class Main {
    public static void main(String[] args) {
        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();

        Runtime.getRuntime().addShutdownHook(new Thread(wireMockServer::shutdown));

        SimpleTests.stub(wireMockServer);
        AuthTests.stub(wireMockServer);
        System.out.println("Server is running!");
    }
}
