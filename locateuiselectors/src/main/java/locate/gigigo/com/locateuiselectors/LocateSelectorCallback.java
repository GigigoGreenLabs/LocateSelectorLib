package locate.gigigo.com.locateuiselectors;

/**
 * Created by nubor on 23/01/2017.
 */
public interface LocateSelectorCallback {
  void onClickItem(String Country, String Language, String IsoCode);
  void onCheckItem(String Country, String Language, String IsoCode); //ras only for listview
}
