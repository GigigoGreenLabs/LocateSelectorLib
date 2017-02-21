package locate.gigigo.com.locateselector;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
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
  LocateSelectorListView mLocateListViewMASTER;
  LocateSelectorListView mLocateListViewDETAIL;

  //region LocateSelectorCallback
  LocateSelectorCallback mShowMessageLocateSelectorCallback = new LocateSelectorCallback() {
    @Override public void onClickItem(String countryCode, String languageCode, String isoCode) {
      showMessage(countryCode, languageCode, isoCode);
    }

    @Override public void onCheckItem(String countryCode, String languageCode, String isoCode,
        boolean checked) {
      showMessage(countryCode + "-" + languageCode, "Cheched:" + checked, isoCode);
    }
  };

  LocateSelectorCallback mFromMasterToDetailLocateSelectorCallback = new LocateSelectorCallback() {
    @Override public void onClickItem(String Country, String Language, String IsoCode) {
      mLocateListViewMASTER.setVisibility(View.GONE);
      mLocateListViewDETAIL.setVisibility(View.VISIBLE);
      LocateSelectorBuilder builderLanguages = getBuilderListViewDetail(IsoCode);
      mLocateListViewDETAIL.init(builderLanguages);
    }

    @Override
    public void onCheckItem(String Country, String Language, String IsoCode, boolean checked) {
      this.onClickItem(Country, Language, IsoCode);
    }
  };

  LocateSelectorCallback mFromDetailToMasterLocateSelectorCallback = new LocateSelectorCallback() {
    @Override public void onClickItem(String Country, String Language, String IsoCode) {
      showMessage(Country, Language, IsoCode);
      mLocateListViewMASTER.setVisibility(View.VISIBLE);
      mLocateListViewDETAIL.setVisibility(View.GONE);
    }

    @Override
    public void onCheckItem(String Country, String Language, String IsoCode, boolean checked) {
      showMessage(Country, Language, IsoCode);
      mLocateListViewMASTER.setVisibility(View.VISIBLE);
      mLocateListViewDETAIL.setVisibility(View.GONE);
    }
  };
  //endregion

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_scrolling);

    // initLayout();

    try {
      Intent intent = new Intent(this, RecyclerActivity.class);
      startActivity(intent);
    } catch (Exception e) {
      e.printStackTrace();
    }


    mLocateListViewMASTER = (LocateSelectorListView) findViewById(R.id.listview2);
    mLocateListViewDETAIL = (LocateSelectorListView) findViewById(R.id.listview3);
    generateLocateModelList();
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
            .setData(mLocateModelList)
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
        LocateSelectorUIMode.CUSTOM_MODEL_RULES).setViewIdLocSelBuilder(viewIdLocSelBuilder)
        .setData(mLocateModelList)
        .setShowIsoCodeInRowText(false)
        .setFontTypeFace(typefaceGBook)
        .setItem_Layout(R.layout.my_lang_item_list_view)
        .setLocateImageSetFlag(new LocateImageSetFlagUrlGlideImpl())
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
            .setData(mLocateModelList)
            .setFontTypeFace(typefaceGBook)
            .setDefaultText("COUNTRY_LANGUAGE");
    LocateSelectorSpinner mSpinner = new LocateSelectorSpinner(this);

    //endregion
    //region COUNTRY
    LocateSelectorBuilder builder2 =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.COUNTRY).setCallback(
            mShowMessageLocateSelectorCallback)
            .setData(mLocateModelList)
            .setFontTypeFace(typefaceGLight)
            .setDefaultText("COUNTRY");

    LocateSelectorSpinner mSpinner2 = new LocateSelectorSpinner(this);
    //endregion
    //region LANGUAGE
    LocateSelectorBuilder builder3 =
        new LocateSelectorBuilder(this, LocateSelectorUIMode.LANGUAGE).setCallback(
            mShowMessageLocateSelectorCallback)
            .setData(mLocateModelList)
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

  private List<LocateModel> mLocateModelList;

  private void generateLocateModelList() {
    mLocateModelList = new ArrayList<>();
    mLocateModelList.add(new LocateModel("es", "ESPAÑA", ""));
    mLocateModelList.add(new LocateModel("fr", "FRANCIA", ""));
    mLocateModelList.add(new LocateModel("de", "ALEMANIA", ""));
    mLocateModelList.add(new LocateModel("it-ch", "SUIZA", "Italiano"));
    mLocateModelList.add(new LocateModel("de-ch", "SUIZA", "Alemán"));
    mLocateModelList.add(new LocateModel("es-mx", "MEXICO", "Español"));
    mLocateModelList.add(new LocateModel("be-nl", "HOLANDA", "Flamenco"));
    mLocateModelList.get(1).setChecked(true);

    //region with LocateImageSetFlagURLGlideImpl
    for (int i = 0; i < mLocateModelList.size(); i++) {
      mLocateModelList.get(i)
          .setFlagURL("https://s3-eu-west-1.amazonaws.com/stream-public-dev/woah/flag-italy.png");
    }

    initLayout();
    //endregion
    //region with drawable impl, but obtain that drawables from url, not using localraw recources
    /*new AsyncTask<Void, Void, Void>() {
      @Override
      protected Void doInBackground(Void... params) {
        Looper.prepare();
        try {
          theBitmap = Glide.
              with(RecyclerActivity.this).
              load("https://s3-eu-west-1.amazonaws.com/stream-public-dev/woah/flag-italy.png").
              asBitmap().
              into(84,56).
              get();
        } catch (final ExecutionException e) {
          Log.e("", e.getMessage());
        } catch (final InterruptedException e) {
          Log.e("", e.getMessage());
        }
        return null;
      }
      @Override
      protected void onPostExecute(Void dummy) {
        if (null != theBitmap) {

          for (int i = 0; i < mLocateModelList.size(); i++) {
            mLocateModelList.get(i).setFlagDrawable(new BitmapDrawable(theBitmap));
          }

          Log.d("", "Image loaded");
          initLayout();
        };
      }
    }.execute();*/
    //endregion

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
  //region with drawable impl, but obtain that drawables from url, not using localraw recources
  private Bitmap theBitmap = null; //asynctask

  private Drawable getDrawableFromUrlWithGlide(String url) {

    ImageView myImgView = new ImageView(this);
    Glide.with(this)

        .load(url).into(myImgView);

    return myImgView.getDrawable();
  }
  //endregion
}
