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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorBuilder;

/**
 * Created by nubor on 24/01/2017.
 */
public class LocateSelectorListView extends ListView {
  public LocateSelectorListView(Context context) {
    super(context);
  }

  public LocateSelectorListView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LocateSelectorListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  LocateSelectorBuilder mBuilder;
  CustomizedListViewAdapter mAdapter;

  public void init(LocateSelectorBuilder builder) {
    if (builder.getmItem_Layout() == -1) builder.setmItem_Layout(R.layout.lang_item);
    this.mBuilder = builder;
    loadData();
  }

  private void loadData() {
    //region create String array
    String[] IsoCodesList = LocateUtil.getLocateModelIntoStringArray(mBuilder.getmData());
    //endregion

    mAdapter =
        new CustomizedListViewAdapter((Activity) mBuilder.getmContext(), mBuilder.getmItem_Layout(),
            IsoCodesList);

    this.setAdapter(mAdapter);
    this.setOnItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String isoCode = mBuilder.getmData().get(position).getIsoCode();
        String country = LocateUtil.getCountryCodeFromIsoCode(isoCode);
        String language = LocateUtil.getLanguageCodeFromIsoCode(isoCode);
        mBuilder.getmCallback().onClickItem(country, language, isoCode);
      }
    });
  }

  private class CustomizedListViewAdapter extends BaseAdapter {

    private Activity mContext;
    String[] mData = null;
    int mItemTemplate = 0;

    public CustomizedListViewAdapter(Activity context, int resource, String[] data2) {
      this.mContext = context;
      this.mData = data2;
      this.mItemTemplate = resource;
    }

    @Override public int getCount() {
      if (mData != null && mData.length > 0) {
        return mData.length;
      } else {
        return 0;
      }
    }

    @Override public String getItem(int arg0) {
      return this.mData[arg0];
    }

    @Override public long getItemId(int arg0) {
      return arg0;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
      try {
        ViewHolder holder;
        LayoutInflater inflater = mContext.getLayoutInflater();
        if (convertView != null) {
          holder = (ViewHolder) convertView.getTag();
        } else {
          holder = new ViewHolder(convertView = inflater.inflate(mBuilder.getmItem_Layout(), null));
        }

        //put the data in it
        String isoCode = mData[position];
        if (isoCode != null) {
          //region get values for show
          String country =
              LocateUtil.getCountryResourceFromIsoCode(isoCode, mBuilder.getmContext());
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
          holder.txtLocate.setText(textRow);
          if (mBuilder.getFontTypeFace() != null) {
            Typeface tf = mBuilder.getFontTypeFace();
            holder.txtLocate.setTypeface(tf);
          }
          holder.imgLocate.setImageDrawable(drawFlag);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      return convertView;
    }

    private class ViewHolder {
      private TextView txtLocate;
      private ImageView imgLocate;

      private RelativeLayout lytContainer;

      public ViewHolder(View view) {
        txtLocate =
            (TextView) view.findViewById(mBuilder.getmViewIdLocSelBuilder().getTextViewForText());
        imgLocate =
            (ImageView) view.findViewById(mBuilder.getmViewIdLocSelBuilder().getImageViewForFlag());

        view.setTag(this);
      }
    }
  }
}
