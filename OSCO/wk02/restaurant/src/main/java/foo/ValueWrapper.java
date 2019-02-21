package foo;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author joernneumeyer
 */
public class ValueWrapper<T> {

  private T value = null;
  private Semaphore lock = new Semaphore(1);

  public ValueWrapper() {
  }

  public ValueWrapper(T value) {
    this.value = value;
  }

  public T getValue() {
    T result = null;
    try {
      this.lock.acquire();
      result = this.value;
    } catch (InterruptedException ex) {
      Logger.getLogger(ValueWrapper.class.getName()).log(Level.SEVERE, null, ex);
    }
      this.lock.release();
    return result;
  }

  public ValueWrapper setValue(T value) {
    try {
      this.lock.acquire();
      this.value = value;
    } catch (InterruptedException ex) {
      Logger.getLogger(ValueWrapper.class.getName()).log(Level.SEVERE, null, ex);
    }
    
      this.lock.release();
    return this;
  }
}
