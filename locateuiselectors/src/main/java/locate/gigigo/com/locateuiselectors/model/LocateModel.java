package locate.gigigo.com.locateuiselectors.model;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateModel {

  //todo if multichoice is necesary, will be over here ;)
  String IsoCode="";
  int flagDrawable=0;

  public LocateModel(String isoCode) {
    IsoCode = isoCode;
  }

  public String getIsoCode() {
    return IsoCode;
  }

  public void setIsoCode(String isoCode) {
    IsoCode = isoCode;
  }

  public int getFlagDrawable() {
    return flagDrawable;
  }

  public void setFlagDrawable(int flagDrawable) {
    this.flagDrawable = flagDrawable;
  }


}
