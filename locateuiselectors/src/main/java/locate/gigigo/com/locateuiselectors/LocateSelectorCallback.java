package locate.gigigo.com.locateuiselectors;

/**
 * Created by nubor on 23/01/2017.
 */
public interface LocateSelectorCallback {
  void onClickItem(String countryCode, String languageCode, String isoCode);
  void onCheckItem(String countryCode, String languageCode, String isoCode,boolean checked); //ras only for listview
}
