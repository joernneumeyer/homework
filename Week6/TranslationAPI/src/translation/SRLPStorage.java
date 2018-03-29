/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

import java.util.HashMap;
import java.util.Map;
import translation.exceptions.TranslationAlreadySetException;
import translation.exceptions.TranslationNotFoundException;

/**
 *
 * @author joernneumeyer
 */
public class SRLPStorage implements TranslationStorage {
  private Map<String, String> translations = new HashMap<>();
  
  public SRLPStorage() { }
  public SRLPStorage(Map<String, String> translations) {
    this.translations = new HashMap<>(translations);
  }

  @Override
  public String get(String english) throws TranslationNotFoundException {
    String translation = this.translations.get(english);
    if (translation == null) {
      throw new TranslationNotFoundException();
    }
    return translation;
  }

  @Override
  public void set(String english, String dutch) {
    try {
      this.add(english, dutch);
    } catch (TranslationAlreadySetException ex) {
      
    }
  }

  @Override
  public boolean delete(String english) {
    return this.translations.remove(english) != null;
  }

  @Override
  public void add(String english, String dutch) throws TranslationAlreadySetException {
    if (this.translations.get(english) != null) {
      throw new TranslationAlreadySetException();
    }
    this.translations.put(english, dutch);
  }

  @Override
  public Map<String, String> getAllTranslations() {
    return new HashMap<>(this.translations);
  }
  
}
