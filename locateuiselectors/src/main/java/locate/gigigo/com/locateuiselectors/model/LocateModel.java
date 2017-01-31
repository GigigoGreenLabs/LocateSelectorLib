package locate.gigigo.com.locateuiselectors.model;

import android.graphics.drawable.Drawable;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateModel {

  //todo if multichoice is necesary, will be over here ;) boolean are selected)
  String IsoCode = "";
  Drawable flagDrawable = null;

  String CountryName = "";
  String LanguageName = "";

  public LocateModel(String isoCode) {
    IsoCode = isoCode;
  }

  public LocateModel(String isoCode,  String countryName, String languageName) {
    IsoCode = isoCode;

    CountryName = countryName;
    LanguageName = languageName;
  }

  public String getCountryName() {
    return CountryName;
  }

  public void setCountryName(String countryName) {
    CountryName = countryName;
  }

  public String getLanguageName() {
    return LanguageName;
  }

  public void setLanguageName(String languageName) {
    LanguageName = languageName;
  }

  public String getIsoCode() {
    return IsoCode;
  }

  public void setIsoCode(String isoCode) {
    IsoCode = isoCode;
  }

  public Drawable getFlagDrawable() {
    return flagDrawable;
  }

  public void setFlagDrawable(Drawable flagDrawable) {
    this.flagDrawable = flagDrawable;
  }
}
