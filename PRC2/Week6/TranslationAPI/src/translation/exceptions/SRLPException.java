/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation.exceptions;

/**
 *
 * @author joernneumeyer
 */
public class SRLPException extends Exception {
  public SRLPException() {
    super();
  }
  public SRLPException(String message) {
    super(message);
  }
  public SRLPException(Throwable cause) {
    super(cause);
  }
  public SRLPException(String message, Throwable cause) {
    super(message, cause);
  }
}
