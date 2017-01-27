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
import locate.gigigo.com.locateuiselectors.LocateUtil;
import locate.gigigo.com.locateuiselectors.builders.ViewIdLocSelBuilder;
import locate.gigigo.com.locateuiselectors.model.LocateModel;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorBuilder;
import locate.gigigo.com.locateuiselectors.LocateSelectorCallback;
import locate.gigigo.com.locateuiselectors.LocateSelectorListView;
import locate.gigigo.com.locateuiselectors.LocateSelectorSpinner;
import locate.gigigo.com.locateuiselectors.LocateSelectorUIMode;

public class TestActivity extends AppCompatActivity {
    LocateSelectorListView mLocateListViewMASTER ;
    LocateSelectorListView mLocateListViewDETAIL   ;

  //region LocateSelectorCallback
  LocateSelectorCallback mShowMessageLocateSelectorCallback = new LocateSelectorCallback() {
    @Override public void onClickItem(String Country, String Language, String IsoCode) {
      showMessage(Country, Language, IsoCode);
    }

    @Override public void onCheckItem(String Country, String Language, String IsoCode) {
      showMessage(Country, "Cheched:" + Language, IsoCode);
    }
  };

  LocateSelectorCallback mFromMasterToDetailLocateSelectorCallback = new LocateSelectorCallback() {
    @Override public void onClickItem(String Country, String Language, String IsoCode) {
      mLocateListViewMASTER.setVisibility(View.GONE);
      mLocateListViewDETAIL.setVisibility(View.VISIBLE);
      LocateSelectorBuilder builderLanguages = getBuilderListViewDetail(IsoCode);
      mLocateListViewDETAIL.init(builderLanguages);
    }

    @Override public void onCheckItem(String Country, String Language, String IsoCode) {
      this.onClickItem(Country, Language, IsoCode);
    }
  };

  LocateSelectorCallback mFromDetailToMasterLocateSelectorCallback = new LocateSelectorCallback() {
    @Override public void onClickItem(String Country, String Language, String IsoCode) {
      showMessage(Country, Language, IsoCode);
      mLocateListViewMASTER.setVisibility(View.VISIBLE);
      mLocateListViewDETAIL.setVisibility(View.GONE);
    }

    @Override public void onCheckItem(String Country, String Language, String IsoCode) {
      showMessage(Country, Language, IsoCode);
      mLocateListViewMASTER.setVisibility(View.VISIBLE);
      mLocateListViewDETAIL.setVisibility(View.GONE);
    }
  };
  //endregion

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_scrolling);
    mLocateListViewMASTER = (LocateSelectorListView) findViewById(R.id.listview2);
    mLocateListViewDETAIL = (LocateSelectorListView) findViewById(R.id.listview3);
    initLayout();
  }

  private void initLayout() {
    //LISTVIEW custom item template
    createListViewCustomItemTemplate();
    //LISTVIEW with Master-Detail
    createListViewMaster();
    //SPINNERS
    addSpinnersFromCode();
  }

  //region LISTVIEW custom item template
  private void createListViewMaster() {

    Typeface typefaceGUltra = Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Ultra.ttf");
    LocateSelectorBuilder builderMASTER =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.COUNTRY).setCallback(
            mFromMasterToDetailLocateSelectorCallback)
            .setViewIdLocSelBuilder(new ViewIdLocSelBuilder().setShowCheckViewForSelect(false))
            .setData(generateLocateModelList())
            .setFontTypeFace(typefaceGUltra);

    mLocateListViewMASTER.init(builderMASTER);

    //the DETAIL BUILDER is set inside mFromMasterToDetailLocateSelectorCallback

  }
  //endregion

  //region LISTVIEW with Master-Detail
  private LocateSelectorBuilder getBuilderListViewDetail(String isoCode) {
    Typeface typefaceGBook =
        Typeface.createFromAsset(TestActivity.this.getAssets(), "fonts/Gotham-Book.ttf");

    ViewIdLocSelBuilder withOutCheckBoxviewIdLocSelBuilder =
        new ViewIdLocSelBuilder().setShowCheckViewForSelect(false);

    String CountryCode = LocateUtil.getCountryCodeFromIsoCode(isoCode);
    LocateSelectorBuilder builderDetail =
        new LocateSelectorBuilder(TestActivity.this, LocateSelectorUIMode.LANGUAGE).setCallback(
            mFromDetailToMasterLocateSelectorCallback)
            .setData(generateLocateModelLanguagesList(CountryCode))
            .setViewIdLocSelBuilder(withOutCheckBoxviewIdLocSelBuilder)
            .setFontTypeFace(typefaceGBook)
            .setDefaultText("LANGUAGE");

    return builderDetail;
  }

  private void createListViewCustomItemTemplate() {

    Typeface typefaceGBook =
        Typeface.createFromAsset(TestActivity.this.getAssets(), "fonts/Gotham-Book.ttf");

    //re-set Views, beacuse we re set the item Layout
    ViewIdLocSelBuilder viewIdLocSelBuilder =
        new ViewIdLocSelBuilder().setImageViewForFlag(R.id.imgFlag2)
            .setTextViewForText(R.id.txtLocate2)
            .setCheckViewForSelect(R.id.checkLocate2);

    LocateSelectorBuilder builderMyOwnItemTemplate = new LocateSelectorBuilder(TestActivity.this,
        LocateSelectorUIMode.COUNTRY_LANGUAGE).setViewIdLocSelBuilder(viewIdLocSelBuilder)
        .setData(generateLocateModelList())
        .setShowIsoCodeInRowText(false)
        .setFontTypeFace(typefaceGBook)
        .setItem_Layout(R.layout.my_lang_item_list_view)
        .setCallback(mShowMessageLocateSelectorCallback);

    LocateSelectorListView locateListView0 = (LocateSelectorListView) findViewById(R.id.listview1);
    locateListView0.init(builderMyOwnItemTemplate);
  }

  //endregion

  //region SPINNERS
  private void addSpinnersFromCode() {
    final LinearLayout lytContainer = (LinearLayout) findViewById(R.id.container);

    ViewGroup.LayoutParams layoutParams =
        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    //region Typefaces
    Typeface typefaceGBook = Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Book.ttf");
    Typeface typefaceGLight = Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Light.ttf");
    Typeface typefaceGMedium =
        Typeface.createFromAsset(this.getAssets(), "fonts/Gotham-Medium.ttf");
    //endregion

    //region  COUNTRY_LANGUAGE
    LocateSelectorBuilder builder =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.COUNTRY_LANGUAGE).setCallback(
            mShowMessageLocateSelectorCallback)
            .setData(generateLocateModelList())
            .setFontTypeFace(typefaceGBook)
            .setDefaultText("COUNTRY_LANGUAGE");
    LocateSelectorSpinner mSpinner = new LocateSelectorSpinner(this);

    //endregion
    //region COUNTRY
    LocateSelectorBuilder builder2 =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.COUNTRY).setCallback(
            mShowMessageLocateSelectorCallback)
            .setData(generateLocateModelList())
            .setFontTypeFace(typefaceGLight)
            .setDefaultText("COUNTRY");

    LocateSelectorSpinner mSpinner2 = new LocateSelectorSpinner(this);
    //endregion
    //region LANGUAGE
    LocateSelectorBuilder builder3 =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.LANGUAGE).setCallback(
            mShowMessageLocateSelectorCallback)
            .setData(generateLocateModelList())
            .setFontTypeFace(typefaceGMedium)
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
  //endregion

  //region utils functions
  private List<LocateModel> generateLocateModelList() {
    List<LocateModel> locateModelList = new ArrayList<>();
    locateModelList.add(new LocateModel("es"));
    locateModelList.add(new LocateModel("fr"));
    locateModelList.add(new LocateModel("de"));
    locateModelList.add(new LocateModel("en-us"));
    locateModelList.add(new LocateModel("en-uk"));
    locateModelList.add(new LocateModel("es-mx"));
    locateModelList.add(new LocateModel("be-nl"));
    locateModelList.add(new LocateModel("it-Ch"));
    return locateModelList;
  }

  private List<LocateModel> generateLocateModelLanguagesList(String CountryCode) {
    List<LocateModel> locateModelList = new ArrayList<>();
    locateModelList.add(new LocateModel("en" + "-" + CountryCode));
    locateModelList.add(new LocateModel("de" + "-" + CountryCode));
    locateModelList.add(new LocateModel("it" + "-" + CountryCode));
    locateModelList.add(new LocateModel("es" + "-" + CountryCode));
    locateModelList.add(new LocateModel("fr" + "-" + CountryCode));
    return locateModelList;
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
  //endregion
}
