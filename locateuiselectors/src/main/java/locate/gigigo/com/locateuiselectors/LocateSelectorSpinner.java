package locate.gigigo.com.locateuiselectors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorBuilder;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateSelectorSpinner extends Spinner {
  boolean mBFirstHit = true;
  LocateSelectorBuilder mBuilder;
  CustomizedSpinnerAdapter adapter;

  public LocateSelectorSpinner(Context context) {
    super(context);
  }

  public LocateSelectorSpinner(Context context, int mode) {
    super(context, mode);
  }

  public LocateSelectorSpinner(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LocateSelectorSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public void init(LocateSelectorBuilder builder) {
    if (builder.getmItem_Layout() == -1) builder.setmItem_Layout(R.layout.lang_item);
    this.mBuilder = builder;
    loadData();
  }

  /**
   * esto cambia el combo de traducciones
   */

  private void loadData() {

    mBFirstHit = true;

    //region create String array
    String[] IsoCodesList = LocateUtil.getLocateModelIntoStringArray(mBuilder.getmData());
    //endregion
    adapter =
        new CustomizedSpinnerAdapter((Activity) mBuilder.getmContext(), mBuilder.getmItem_Layout(),
            IsoCodesList);
    adapter.setDropDownViewResource(mBuilder.getmItem_Layout());
    this.setAdapter(adapter);
    this.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
        if (!mBFirstHit) {
          String isoCode = mBuilder.getmData().get(position).getIsoCode();
          String country = LocateUtil.getCountryCodeFromIsoCode(isoCode);
          String language = LocateUtil.getLanguageCodeFromIsoCode(isoCode);
          mBuilder.getmCallback().onClickItem(country, language, isoCode);
          //todo no se queda bien seleccionado el valor elegido, quizás por la imagen
          LocateSelectorSpinner.this.setSelection(position);
        }
        if (mBFirstHit) mBFirstHit = false;
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {
        //todo ?
      }
    });
  }

  public class CustomizedSpinnerAdapter extends ArrayAdapter<String> {

    private Activity mContext;
    String[] mData = null;
    int mItemTemplate = 0;

    public CustomizedSpinnerAdapter(Activity context, int resource, String[] data2) {
      super(context, resource, data2);
      this.mContext = context;
      this.mData = data2;
      this.mItemTemplate = resource;
    }

    @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
      View row = convertView;
      if (row == null) {
        //inflate your customlayout for the textview
        LayoutInflater inflater = mContext.getLayoutInflater();
        row = inflater.inflate(mItemTemplate, parent, false);
      }
      //put the data in it
      String isoCode = mData[position];
      if (isoCode != null) {
        //region get values for show
        String country = LocateUtil.getCountryResourceFromIsoCode(isoCode, mBuilder.getmContext());
        String language =
            LocateUtil.getLanguageResourceFromIsoCode(isoCode, mBuilder.getmContext());
        String textRow = "";
        Drawable drawFlag = LocateUtil.getDrawable(isoCode, mContext);

        if (mBuilder.getmLocateSelectorUIMode() == LocateSelectorUIMode.COUNTRY) {
          textRow = country + "    " + isoCode;
        }
        if (mBuilder.getmLocateSelectorUIMode() == LocateSelectorUIMode.LANGUAGE) {
          textRow = language + "    " + isoCode;
        }
        if (mBuilder.getmLocateSelectorUIMode() == LocateSelectorUIMode.COUNTRY_LANGUAGE) {
          textRow = language + "-" + country + "    " + isoCode;
        }
        //endregion

        TextView text1 = (TextView) row.findViewById(mBuilder.getmViewIdLocSelBuilder().getTextViewForText());
        text1.setText(textRow);
        if (mBuilder.getFontTypeFace() != null) {
          Typeface tf = mBuilder.getFontTypeFace();
          text1.setTypeface(tf);
        }
        ImageView imgFlag = (ImageView) row.findViewById(mBuilder.getmViewIdLocSelBuilder().getImageViewForFlag());
        imgFlag.setImageDrawable(drawFlag);
      }

      return row;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      System.out.println(position);
      return getCustomView(position, convertView, parent);
    }

    boolean isfirstTime = true; //set default Value

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = ((Activity) mBuilder.getmContext()).getLayoutInflater();
      View row = inflater.inflate(mBuilder.getmItem_Layout(), parent, false);
      TextView text1 = (TextView) row.findViewById(mBuilder.getmViewIdLocSelBuilder().getTextViewForText());
      if (isfirstTime) {
        isfirstTime = false;
        text1.setText(mBuilder.getDefaultText());
      }
      return row;
    }
  }
}
