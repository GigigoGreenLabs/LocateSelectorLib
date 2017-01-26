package locate.gigigo.com.locateuiselectors;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.List;
import locate.gigigo.com.locateuiselectors.model.LocateModel;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateUtil {

  public static Drawable getDrawable(String isoCode, Context context) {
    String conCode = "";
    try {
      conCode = getCountryCodeFromIsoCode(isoCode);
      if (conCode == "do") {
        conCode = "d0";//do is a keyword u¡in java and dominicarepublic iso, change to d0
      }
      int resourceId = context.getResources()
          .getIdentifier(conCode.toLowerCase(), "drawable", context.getPackageName());
      return context.getResources().getDrawable(resourceId);
    } catch (Resources.NotFoundException e) {
      System.out.println("NO RESOURCE" + isoCode + conCode);
    }
    return null;
  }

  public static String getString(String name, Context context) {
    System.out.println(name);
    try {
      int resourceId =
          context.getResources().getIdentifier(name, "string", context.getPackageName());
      return context.getResources().getString(resourceId);
    } catch (Resources.NotFoundException e) {
      System.out.println("NO RESOURCE" + name);
    }
    return "";
  }

  public static String getCountryResourceFromIsoCode(String isoCode, Context context) {
    return getString("con_" + getCountryCodeFromIsoCode(isoCode), context);
  }

  public static String getLanguageResourceFromIsoCode(String isoCode, Context context) {
    return getString("lang_" + getLanguageCodeFromIsoCode(isoCode), context);
  }

  public static String getCountryCodeFromIsoCode(String isoCode) {
    String country_Code = isoCode;
    try {
      int ini = 0;
      if (isoCode != null && isoCode.indexOf("-") > 1) {
        ini = isoCode.indexOf("-");
        country_Code = isoCode.substring(ini + 1, isoCode.length());
      }
    } catch (Exception e) {
      System.out.println("NO RESOURCE" + isoCode);
    }
    return country_Code;
  }

  public static String getLanguageCodeFromIsoCode(String isoCode) {
    String language_Code = isoCode.toLowerCase();
    try {
      int fin = 0;
      if (isoCode != null && isoCode.indexOf("-") > 1) {
        fin = isoCode.indexOf("-");
        language_Code = isoCode.substring(0, fin);
      }
    } catch (Exception e) {
      System.out.println("NO RESOURCE" + isoCode);
    }
    return language_Code;
  }

  public static String[] getLocateModelIntoStringArray(List<LocateModel> data) {
    String[] IsoCodeList = new String[data.size()];
    for (int i = 0; i < data.size(); i++) {
      IsoCodeList[i] = data.get(i).getIsoCode();
    }

    return IsoCodeList;
  }

  public static String getTextFromRow(LocateSelectorUIMode locateSelectorUIMode,
      boolean showIsoCode, String country, String language, String isoCode) {
    String textRow = "";

    if (locateSelectorUIMode == LocateSelectorUIMode.COUNTRY) {
      textRow = country.toUpperCase();
    }
    if (locateSelectorUIMode == LocateSelectorUIMode.LANGUAGE) {
      textRow = language;
    }
    if (locateSelectorUIMode == LocateSelectorUIMode.LANGUAGE_COUNTRY) {
      textRow = language + "-" + country.toUpperCase();
    }
    if (locateSelectorUIMode == LocateSelectorUIMode.COUNTRY_LANGUAGE) {
      textRow = country.toUpperCase() + "-" + language;
    }

    if (showIsoCode) textRow = textRow + "    " + isoCode;

    return textRow;
  }
}
