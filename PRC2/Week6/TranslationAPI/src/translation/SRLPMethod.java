/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

import translation.exceptions.SRLPException;
import translation.exceptions.TranslationAlreadySetException;
import translation.exceptions.TranslationNotFoundException;

/**
 *
 * @author joernneumeyer
 */
public enum SRLPMethod {
  GET() {
    @Override
    String process(String... words) throws SRLPException {
      if (words.length == 0) {
        return SRLPMethod
            .storage
            .getAllTranslations()
            .entrySet()
            .stream()
            .map((entry) -> entry.getKey() + "=" + entry.getValue() + "\r\n")
            .reduce("", String::concat);
      }
      try {
        return SRLPMethod.storage.get(words[0]);
      } catch (TranslationNotFoundException ex) {
        throw new SRLPException("Translation was not found!", ex);
      }
    }
  },
  POST() {
    @Override
    String process(String ...words) throws SRLPException {
      try {
        SRLPMethod.storage.add(words[0], words[1]);
      } catch (TranslationAlreadySetException ex) {
        throw new SRLPException("Translation has already been set!", ex);
      }
      return words[1];
    }
  },
  PUT() {
    @Override
    String process(String ...words) throws SRLPException {
      SRLPMethod.storage.set(words[0], words[1]);
      return words[1];
    }
  },
  DELETE() {
    @Override
    String process(String[] words) throws SRLPException {
      if (SRLPMethod.storage.delete(words[0])) {
        return words[0];
      }
      return null;
    }
  };
  
  SRLPMethod() { }
  
  String process(String ...words) throws SRLPException {
    System.out.println("called default");
    return "";
  }
  
  private static TranslationStorage storage = null;
  
  public static void setStorage(TranslationStorage storage) {
    SRLPMethod.storage = storage;
  }
  
  public static void initializeStorage() {
    SRLPMethod.storage = new SRLPStorage();
  }
}
