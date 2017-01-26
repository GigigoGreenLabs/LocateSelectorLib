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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
  int mPositionItemClicked = -1;

  public void init(LocateSelectorBuilder builder) {
    if (builder.getmItem_Layout() == -1) builder.setmItem_Layout(R.layout.lang_item_list_view);
    this.mBuilder = builder;
    loadData();
    //this is for propagate event click item
    this.setAddStatesFromChildren(true);
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

    @Override public View getView(final int position, View convertView, ViewGroup parent) {
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

          String textRow = LocateUtil.getTextFromRow(mBuilder.getmLocateSelectorUIMode(),
              mBuilder.getShowIsoCodeInRowText(), country, language, isoCode);
          //endregion

          if (holder.txtLocate != null) {
            if (mBuilder.getmViewIdLocSelBuilder().isShowTextViewForText()) {
              holder.txtLocate.setVisibility(VISIBLE);
              holder.txtLocate.setText(textRow);
              if (mBuilder.getFontTypeFace() != null) {
                Typeface tf = mBuilder.getFontTypeFace();
                holder.txtLocate.setTypeface(tf);
              } else {
                holder.txtLocate.setVisibility(GONE);
              }
            }
          }

          if (holder.imgLocate != null) {
            if (mBuilder.getmViewIdLocSelBuilder().isShowImageViewForFlag()) {
              holder.imgLocate.setVisibility(VISIBLE);
              holder.imgLocate.setImageDrawable(LocateUtil.getDrawable(isoCode, mContext));
            } else {
              holder.imgLocate.setVisibility(GONE);
            }
          }

          //singlechoice

          if (holder.checkLocate != null) {
            if (mBuilder.getmViewIdLocSelBuilder().isShowCheckViewForSelect()) {
              holder.checkLocate.setVisibility(VISIBLE);
              holder.checkLocate.setChecked(position == mPositionItemClicked);
              holder.checkLocate.setClickable(false);
              holder.checkLocate.setOnCheckedChangeListener(
                  new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                      CustomizedListViewAdapter.this.notifyDataSetChanged();
                    }
                  });

              holder.mView.setOnClickListener(new OnClickListener() {
                @Override public void onClick(View v) {
                  mPositionItemClicked = position;
                  CustomizedListViewAdapter.this.notifyDataSetChanged();
                  String isoCode = mData[position];
                  if (isoCode != null) {
                    String country =
                        LocateUtil.getCountryResourceFromIsoCode(isoCode, mBuilder.getmContext());
                    String language =
                        LocateUtil.getLanguageResourceFromIsoCode(isoCode, mBuilder.getmContext());
                    mBuilder.getmCallback().onCheckItem(country, language, isoCode);
                  }
                }
              });
            }
            else
            {
              holder.checkLocate.setVisibility(GONE);
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      return convertView;
    }

    private class ViewHolder {
      private TextView txtLocate;
      private ImageView imgLocate;
      private CheckBox checkLocate;
      private View mView;
      //
      //private RelativeLayout lytContainer;

      public ViewHolder(View view) {
        txtLocate =
            (TextView) view.findViewById(mBuilder.getmViewIdLocSelBuilder().getTextViewForText());
        imgLocate =
            (ImageView) view.findViewById(mBuilder.getmViewIdLocSelBuilder().getImageViewForFlag());

        checkLocate = (CheckBox) view.findViewById(
            mBuilder.getmViewIdLocSelBuilder().getCheckViewForSelect());
        mView = view;
        view.setTag(this);
      }
    }
  }
}
