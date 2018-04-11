/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

import java.util.Map;
import translation.exceptions.TranslationAlreadySetException;
import translation.exceptions.TranslationNotFoundException;

/**
 *
 * @author joernneumeyer
 */
public interface TranslationStorage {
  String get(String english) throws TranslationNotFoundException;
  void add(String english, String dutch) throws TranslationAlreadySetException;
  void set(String english, String dutch);
  boolean delete(String english);
  Map<String, String> getAllTranslations();
}
