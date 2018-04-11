/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

/**
 *
 * @author joernneumeyer
 */
public class SRLPServerResponse {
  private final int statusCode;
  private final String statusMessage;
  private final String body;
  
  public SRLPServerResponse(int statusCode, String statusMessage, String body) {
    this.statusCode = statusCode;
    this.statusMessage = statusMessage;
    this.body = body;
  }
  
  public int getStatusCode() {
    return this.statusCode;
  }
  
  public String getStatusMessage() {
    return this.statusMessage;
  }
  
  public String getBody() {
    return this.body;
  }
  
  public static SRLPServerResponse fromString(String response) {
    String[] parts = response.split("\r\n\r\n", 2);
    String[] headers = parts[0].split(" ", 2);
    String body = parts.length > 1 ? parts[1] : "";
    int statusCode = Integer.parseInt(headers[0]);
    String statusMessage = headers[1];
    return new SRLPServerResponse(statusCode, statusMessage, body);
  }
}
