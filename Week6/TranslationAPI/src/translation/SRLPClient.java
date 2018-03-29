/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author joernneumeyer
 */
public class SRLPClient {
  private Socket socket = new Socket();
  private OutputStream outStream;
  private InputStream inStream;
  private BufferedReader reader;
  private PrintStream writer;
  
  public void connect(SocketAddress addr) throws IOException {
    this.socket.connect(addr);
    this.outStream = this.socket.getOutputStream();
    this.inStream = this.socket.getInputStream();
    this.reader = new BufferedReader(new InputStreamReader(this.inStream));
    this.writer = new PrintStream(this.outStream);
  }
  
  public void close() throws IOException {
    this.socket.close();
  }
  
  /*public SRLPServerResponse sendRequest(String request) throws IOException {
    this.writer.print(request);
    this.writer.flush();
    String response = "";
    while (this.inStream.available() == -1) ;
    while (this.inStream.available() > -1) {
      response += this.reader.readLine();
    }
    return SRLPServerResponse.fromString(response);
  }*/
  
  public String sendRequest(String request) throws IOException {
    if (!request.endsWith("\r\n")) {
      request += "\r\n";
    }
    this.writer.print(request);
    this.writer.flush();
    String response = "";
    String line;
    while ((line = this.reader.readLine()) != null) {
      response += line.concat("\r\n");
    }
    return response.length() >= 2 ? response.substring(0, response.length() - 2) : "";
  }
  
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Call SRLPClient as follows: <clientpath> <method> [parameters]");
      return;
    }
    if (!args[0].equals("GET") && args.length == 1) {
      System.out.println("Call SRLPClient as follows: <clientpath> <method> <parameters>");
      return;
    }
    SRLPClient client = new SRLPClient();
    try {
      client.connect(new InetSocketAddress("127.0.0.1", 12345));
    } catch (IOException ex) {
      System.out.println("Could not connect to the local server!");
      return;
    }
    String request = String.join(" ", args);
    try {
      String response = client.sendRequest(request);
      System.out.println(response);
      client.close();
    } catch (IOException ex) {
      System.out.println("[Error] Error while sending request to the server!");
    }
  }
}
