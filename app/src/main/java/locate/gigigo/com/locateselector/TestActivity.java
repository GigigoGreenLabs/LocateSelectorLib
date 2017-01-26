package locate.gigigo.com.locateselector;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;
import locate.gigigo.com.locateuiselectors.builders.ViewIdLocSelBuilder;
import locate.gigigo.com.locateuiselectors.model.LocateModel;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorBuilder;
import locate.gigigo.com.locateuiselectors.LocateSelectorCallback;
import locate.gigigo.com.locateuiselectors.LocateSelectorListView;
import locate.gigigo.com.locateuiselectors.LocateSelectorSpinner;
import locate.gigigo.com.locateuiselectors.LocateSelectorUIMode;

public class TestActivity extends AppCompatActivity implements LocateSelectorCallback {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_scrolling);
    initLayout();
  }

  private void initLayout() {
    final LinearLayout lytContainer = (LinearLayout) findViewById(R.id.container);

    /****
     *
     * custom item template LIST VIEW
     */

    LocateSelectorBuilder builderMyOwnItemTemplate =
        new LocateSelectorBuilder(TestActivity.this,LocateSelectorUIMode.COUNTRY_LANGUAGE)
            .setmViewIdLocSelBuilder(
        new ViewIdLocSelBuilder()
            .setImageViewForFlag(R.id.imgFlag2)
            .setTextViewForText(R.id.txtLocate2))
        .setmData(generateLocateModelList())
        .setFontTypeFace(Typeface.createFromAsset(TestActivity.this.getAssets(), "fonts/Gotham-Book.ttf"))
        .setmItem_Layout(R.layout.my_lang_item_list_view)
        .setmCallback(new LocateSelectorCallback() {
          @Override public void onClickItem(String Country, String Language, String IsoCode) {
            showMessage(Country, Language, IsoCode);
          }
        });
    LocateSelectorListView locateListView0 = (LocateSelectorListView) findViewById(R.id.listview1);
    locateListView0.init(builderMyOwnItemTemplate);

    //*SPINNERS*/
    addSpinnersFromCode();
    /*
    * LISTVIEW with SubListView
    * */
    final LocateSelectorListView locateListViewMASTER =  (LocateSelectorListView) findViewById(R.id.listview2);
    final LocateSelectorListView locateListViewDETAIL =   (LocateSelectorListView) findViewById(R.id.listview3);

    final LocateSelectorCallback selectorCallbackDETAIL = new LocateSelectorCallback() {
      @Override public void onClickItem(String Country, String Language, String IsoCode) {
        showMessage(Country, Language, IsoCode);
        locateListViewMASTER.setVisibility(View.VISIBLE);
        locateListViewDETAIL.setVisibility(View.GONE);
      }
    };

    final LocateSelectorCallback  selectorCallbackMASTER = new LocateSelectorCallback() {
      @Override public void onClickItem(String Country, String Language, String IsoCode) {
        locateListViewMASTER.setVisibility(View.GONE);
        locateListViewDETAIL.setVisibility(View.VISIBLE);

        LocateSelectorBuilder builderLanguages = new LocateSelectorBuilder(TestActivity.this,
            LocateSelectorUIMode.LANGUAGE)
            .setmCallback(selectorCallbackDETAIL)
            .setmData(generateLocateModelLanguagesList(Country))
            .setFontTypeFace(
                Typeface.createFromAsset(TestActivity.this.getAssets(), "fonts/Gotham-Book.ttf"))
            .setDefaultText("LANGUAGE");

        locateListViewDETAIL.init(builderLanguages);

      }
    };

    LocateSelectorBuilder builderListView =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.COUNTRY)
            .setmCallback(selectorCallbackMASTER)
            .setmData(generateLocateModelList())
            .setFontTypeFace(Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Ultra.ttf"));

    locateListViewMASTER.init(builderListView);
  }

  private void addSpinnersFromCode() {
    final LinearLayout lytContainer = (LinearLayout) findViewById(R.id.container);
    //lytContainer.removeAllViews();
    ViewGroup.LayoutParams layoutParams =
        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    /***
     *
     *
     * Spinners
     *
     *
     */
    //region  COUNTRY_LANGUAGE
    LocateSelectorBuilder builder =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.COUNTRY_LANGUAGE).setmCallback(this)
            .setmData(generateLocateModelList())
            .setFontTypeFace(Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Book.ttf"))
            .setDefaultText("COUNTRY_LANGUAGE");
    LocateSelectorSpinner mSpinner = new LocateSelectorSpinner(this);

    //endregion
    //region COUNTRY
    LocateSelectorBuilder builder2 =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.COUNTRY).setmCallback(this)
            .setmData(generateLocateModelList())
            .setFontTypeFace(Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Light.ttf"))
            .setDefaultText("COUNTRY");

    LocateSelectorSpinner mSpinner2 = new LocateSelectorSpinner(this);
    //endregion
    //region LANGUAGE
    LocateSelectorBuilder builder3 =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.LANGUAGE).setmCallback(this)
            .setmData(generateLocateModelList())
            .setFontTypeFace(Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Medium.ttf"))
            .setDefaultText("LANGUAGE");
    LocateSelectorSpinner mSpinner3 = new LocateSelectorSpinner(this);

    //endregion

    lytContainer.addView(mSpinner, layoutParams);
    lytContainer.addView(mSpinner2, layoutParams);
    lytContainer.addView(mSpinner3, layoutParams);

    mSpinner.init(builder);
    mSpinner2.init(builder2);
    mSpinner3.init(builder3);
  }

  private List<LocateModel> generateLocateModelList() {
    List<LocateModel> locateModelList = new ArrayList<>();
    locateModelList.add(new LocateModel("es"));
    locateModelList.add(new LocateModel("fr"));
    locateModelList.add(new LocateModel("de"));
    locateModelList.add(new LocateModel("en-us"));
    locateModelList.add(new LocateModel("en-uk"));
    locateModelList.add(new LocateModel("es-mx"));
    locateModelList.add(new LocateModel("be-nl"));
    locateModelList.add(new LocateModel("uy-it"));
    return locateModelList;
  }

  private List<LocateModel> generateLocateModelLanguagesList(String Country) {
    //private List<LocateModel> generateLocateModelLanguagesList() {
    List<LocateModel> locateModelList = new ArrayList<>();
    //+"-"+ Country
    //    +"-"+ Country
    //    +"-"+ Country
    //    +"-"+ Country
    //    +"-"+ Country
    locateModelList.add(new LocateModel("en" + "-" + Country));
    locateModelList.add(new LocateModel("de" + "-" + Country));
    locateModelList.add(new LocateModel("it" + "-" + Country));
    locateModelList.add(new LocateModel("es" + "-" + Country));
    locateModelList.add(new LocateModel("fr" + "-" + Country));
    return locateModelList;
  }

  @Override public void onClickItem(String Country, String Language, String IsoCode) {
    showMessage(Country, Language, IsoCode);
  }

  void showMessage(String Country, String Language, String IsoCode) {
    new AlertDialog.Builder(this).setTitle(Country)
        .setMessage(Language + "ISO: " + IsoCode)
        .setCancelable(false)
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
          @Override public void onClick(DialogInterface dialog, int which) {

          }
        })
        .create()
        .show();
  }
}
