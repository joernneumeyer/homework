/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsrs;

/**
 *
 * @author JoernNeumeyer
 */
public class Main {
  public static void main(String[] args) {
    WiremockExercise2 mock = new WiremockExercise2();
    mock.cookieStub();
    System.out.println("Server is running!");
  }
}
