package locate.gigigo.com.locateuiselectors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorBuilder;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateSelectorSpinner extends Spinner {

  //region variables
  boolean mBFirstHit = true; //mandatory for don't do a callback firsttime load combo
  LocateSelectorBuilder mBuilder;
  CustomizedSpinnerAdapter adapter;
  //endregion

  //region constructors
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
  //endregion

  public void init(LocateSelectorBuilder builder) {
    if (builder.getItemLayout() == -1) builder.setItem_Layout(R.layout.lang_item);
    this.mBuilder = builder;
    loadData();
  }

  private void loadData() {

    mBFirstHit = true;

    //region create String array
    String[] IsoCodesList = LocateUtil.getLocateModelIntoStringArray(mBuilder.getData());
    //endregion
    adapter =
        new CustomizedSpinnerAdapter((Activity) mBuilder.getContext(), mBuilder.getItemLayout(),
            IsoCodesList);
    adapter.setDropDownViewResource(mBuilder.getItemLayout());
    this.setAdapter(adapter);
    this.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
        if (!mBFirstHit) {
          String isoCode = mBuilder.getData().get(position).getIsoCode();
          String country = LocateUtil.getCountryCodeFromIsoCode(isoCode);
          String language = LocateUtil.getLanguageCodeFromIsoCode(isoCode);
          mBuilder.getCallback().onClickItem(country, language, isoCode);
          //todo selection trolling?? or what?
          LocateSelectorSpinner.this.setSelection(position);
        }
        if (mBFirstHit) mBFirstHit = false;
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {
        //so what?
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
      if (mData != null && mData[position] != null) {
        TextView text1 =
            (TextView) row.findViewById(mBuilder.getViewIdLocSelBuilder().getTextViewForText());
        ImageView imgFlag =
            (ImageView) row.findViewById(mBuilder.getViewIdLocSelBuilder().getImageViewForFlag());
        CheckBox chk =
            (CheckBox) row.findViewById(mBuilder.getViewIdLocSelBuilder().getCheckViewForSelect());

        putDataIntoUIRow(text1, imgFlag, chk, position);
      }

      return row;
    }
    //todo refactor LocateSeletorListView.putDataIntoUIRow, and change Viewholder by 3 views
    private void putDataIntoUIRow(TextView text1, ImageView imgFlag, CheckBox chk, int position) {
      String isoCode = mData[position];
      String textRow = LocateUtil.getTextFromRow(mBuilder.getLocateSelectorUIMode(),
        mBuilder.getShowIsoCodeInRowText(),   mBuilder.getContext(),mBuilder.getData().get(position));

      if (text1 != null) {
        if (mBuilder.getViewIdLocSelBuilder().getShowTextViewForText()) {
          text1.setVisibility(VISIBLE);
          //set text
          text1.setText(textRow);
          if (mBuilder.getFontTypeFace() != null) {
            Typeface tf = mBuilder.getFontTypeFace();
            text1.setTypeface(tf);
          }
        } else {
          text1.setVisibility(GONE);
        }
      }

      if (imgFlag != null) {
        if (mBuilder.getViewIdLocSelBuilder().getShowImageViewForFlag()) {
          imgFlag.setVisibility(VISIBLE);
          mBuilder.getLocateImageSetFlag().setFlagIntoImageView(mBuilder.getData().get(position),mContext,imgFlag);
         // imgFlag.setImageDrawable(LocateUtil.getDrawable(isoCode, mContext));
        } else {
          imgFlag.setVisibility(GONE);
        }
      }

      if (chk != null) {
        if (mBuilder.getViewIdLocSelBuilder().getShowCheckViewForSelect()) {
          chk.setVisibility(VISIBLE);
        } else {
          chk.setVisibility(GONE);
        }
      }
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      System.out.println(position);
      return getCustomView(position, convertView, parent);
    }

    boolean isfirstTime = true; //set default Value

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {
      LayoutInflater inflater = ((Activity) mBuilder.getContext()).getLayoutInflater();
      View row = inflater.inflate(mBuilder.getItemLayout(), parent, false);
      TextView text1 =
          (TextView) row.findViewById(mBuilder.getViewIdLocSelBuilder().getTextViewForText());
      if (isfirstTime) {
        isfirstTime = false;
        text1.setText(mBuilder.getDefaultText());
      }
      return row;
    }
  }
}
