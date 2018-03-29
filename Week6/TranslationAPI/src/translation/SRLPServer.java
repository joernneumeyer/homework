package translation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import translation.exceptions.SRLPException;

/**
 *
 * @author joernneumeyer
 */
public class SRLPServer {
  private ServerSocket listenerSocket = new ServerSocket();
  private Thread listenerThread = null;
  
  public SRLPServer() throws IOException { }
  
  public void bind(SocketAddress addr) throws IOException {
    this.listenerSocket.bind(addr);
  }
  
  public void start() {
    this.listenerThread = new Thread(() -> {
      while (true) {
        Socket client;
        try {
          client = this.listenerSocket.accept();
          
          OutputStream clientOutStream = client.getOutputStream();;
          InputStream clientInStream = client.getInputStream();
          BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientInStream));
          PrintStream clientWriter = new PrintStream(clientOutStream);
          
          String line = clientReader.readLine();
          String[] requestParameters = line.trim().split(" ");
          if (requestParameters.length == 0) {
            clientWriter.print("422 Missing request method\r\n\r\n");
            clientWriter.flush();
            client.close();
            continue;
          }
          
          if (requestParameters.length == 1 && !requestParameters[0].equals("GET")) {
            clientWriter.print("400 Missing additional parameters\r\n\r\n");
            clientWriter.flush();
            client.close();
            continue;
          }
          switch (requestParameters[0]) {
            case "GET":
              if (requestParameters.length == 2) {
                try {
                  String dutch = SRLPMethod.GET.process(requestParameters[1]);
                  clientWriter.print("200 successfully translated\r\n\r\n".concat(dutch));
                } catch (SRLPException ex) {
                  clientWriter.print("400 No translation known\r\n\r\n");
                }
              } else {
                try {
                  String translations = SRLPMethod.GET.process();
                  clientWriter.print("200 success\r\n\r\n".concat(translations));
                } catch (SRLPException ex) {
                  clientWriter.print("500 Error processing the request\r\n\r\n");
                }
              }
              break;
              
            case "POST":
              if (requestParameters.length == 2) {
                clientWriter.print("422 Missing translation for word\r\n\r\n");
              } else {
                try {
                  String response = SRLPMethod.POST.process(requestParameters[1], requestParameters[2]);
                  clientWriter.print("200 success\r\n\r\n".concat(response));
                } catch (SRLPException ex) {
                  clientWriter.print("409 conflict\r\n\r\n");
                }
              }
              break;
              
            case "PUT":
              if (requestParameters.length == 2) {
                clientWriter.print("422 Missing translation for word\r\n\r\n");
              } else {
                try {
                  String response = SRLPMethod.PUT.process(requestParameters[1], requestParameters[2]);
                  clientWriter.print("200 success\r\n\r\n".concat(response));
                } catch (SRLPException ex) {
                  clientWriter.print("500 error processing your request\r\n\r\n");
                }
              }
              break;
              
            case "DELETE":
              try {
                  String response = SRLPMethod.DELETE.process(requestParameters[1]);
                  if (response != null) {
                    clientWriter.print("200 success\r\n\r\n".concat(response));
                  } else {
                    clientWriter.print("400 word does not exist\r\n\r\n");
                  }
                } catch (SRLPException ex) {
                }
              break;
            
            default:
              clientWriter.print("400 Do not understand".concat(requestParameters[0]).concat("\r\n\r\n"));
              break;
          }
          clientWriter.flush();
          client.close();
        } catch (IOException ex) {
          // TODO - push message to logger
        }
      }
    });
    SRLPMethod.initializeStorage();
    this.listenerThread.start();
  }
  
  public static void main(String[] args) {
    SRLPServer server;
    try {
      server = new SRLPServer();
      server.bind(new InetSocketAddress("0.0.0.0", 12345));
    } catch (IOException ex) {
      System.out.println("Could not create or bind server! Error: " + ex.getMessage());
      return;
    }
    
    server.start();
    System.out.println("Server is running!");
  }
}
