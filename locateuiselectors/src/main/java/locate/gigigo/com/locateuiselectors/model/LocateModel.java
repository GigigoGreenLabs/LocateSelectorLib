package locate.gigigo.com.locateuiselectors.model;

import android.graphics.drawable.Drawable;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateModel {

  //todo if multichoice is necesary, will be over here ;) boolean are selected)
  String isoCode = "";
  Drawable flagDrawable = null;
  String flagURL="";
  //todo placeHOLDER?

  String countryName = "";
  String languageName = "";
  boolean checked = false;

  public LocateModel(String isoCode) {
    this.isoCode = isoCode;
  }

  public LocateModel(String isoCode, String countryName, String languageName) {
    this.isoCode = isoCode;

    this.countryName = countryName;
    this.languageName = languageName;
  }

  public String getFlagURL() {
    return flagURL;
  }

  public void setFlagURL(String flagURL) {
    this.flagURL = flagURL;
  }

  public boolean getChecked() {
    return checked;
  }

  public void setChecked(boolean checked) {
    this.checked = checked;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getLanguageName() {
    return languageName;
  }

  public void setLanguageName(String languageName) {
    this.languageName = languageName;
  }

  public String getIsoCode() {
    return isoCode;
  }

  public void setIsoCode(String isoCode) {
    this.isoCode = isoCode;
  }

  public Drawable getFlagDrawable() {
    return flagDrawable;
  }

  public void setFlagDrawable(Drawable flagDrawable) {
    this.flagDrawable = flagDrawable;
  }
}
