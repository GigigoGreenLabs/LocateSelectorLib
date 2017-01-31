package locate.gigigo.com.locateuiselectors;

/**
 * Created by nubor on 24/01/2017.
 */
public enum LocateSelectorUIMode {
  COUNTRY(1L), //by default
  LANGUAGE(2L),
  COUNTRY_LANGUAGE(3L),
  LANGUAGE_COUNTRY(4L),
  CUSTOM_MODEL_RULES(5L);

  LocateSelectorUIMode(long l) {

  }
}
