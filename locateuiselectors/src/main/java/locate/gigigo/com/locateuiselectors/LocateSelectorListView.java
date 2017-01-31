package locate.gigigo.com.locateuiselectors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
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
import android.widget.TextView;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorBuilder;

/**
 * Created by nubor on 24/01/2017.
 */
public class LocateSelectorListView extends ListView {
  //todo nit method willbe in interface, that this and Spinner implements

  LocateSelectorBuilder mBuilder;
  CustomizedListViewAdapter mAdapter;
  int mPositionItemClicked = -1;

  //region constructors
  public LocateSelectorListView(Context context) {
    super(context);
  }

  public LocateSelectorListView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LocateSelectorListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
  //endregion

  public void init(LocateSelectorBuilder builder) {
    if (builder.getItemLayout() == -1) builder.setItem_Layout(R.layout.lang_item_list_view);
    this.mBuilder = builder;
    loadData();
    //this is for propagate event click item, but no work ^_^
    this.setAddStatesFromChildren(true);
  }

  private void loadData() {
    //region create String array
    String[] IsoCodesList = LocateUtil.getLocateModelIntoStringArray(mBuilder.getData());
    //endregion

    mAdapter =
        new CustomizedListViewAdapter((Activity) mBuilder.getContext(), mBuilder.getItemLayout(),
            IsoCodesList);

    this.setAdapter(mAdapter);
    this.setOnItemClickListener(new OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String isoCode = mBuilder.getData().get(position).getIsoCode();
        String country = LocateUtil.getCountryCodeFromIsoCode(isoCode);
        String language = LocateUtil.getLanguageCodeFromIsoCode(isoCode);

        mBuilder.getCallback().onClickItem(country, language, isoCode);
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
          holder = new ViewHolder(convertView = inflater.inflate(mBuilder.getItemLayout(), null));
        }
        //put the data in it
        if (mData != null && mData[position] != null) {
          putDataIntoUIRow(holder, position);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      return convertView;
    }

    private void putDataIntoUIRow(ViewHolder holder, final int position) {
      //region get isocode and text for row
      String isoCode = mData[position];
      String textRow = LocateUtil.getTextFromRow(mBuilder.getLocateSelectorUIMode(),
          mBuilder.getShowIsoCodeInRowText(), mBuilder.getContext(),
          mBuilder.getData().get(position));
      //endregion

      //region txt
      if (holder.txtLocate != null) {
        if (mBuilder.getViewIdLocSelBuilder().getShowTextViewForText()) {
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
      //endregion
      //region image
      if (holder.imgLocate != null) {
        if (mBuilder.getViewIdLocSelBuilder().getShowImageViewForFlag()) {
          holder.imgLocate.setVisibility(VISIBLE);

          if (mBuilder.getData().get(position).getFlagDrawable() == null) {
            holder.imgLocate.setImageDrawable(LocateUtil.getDrawable(isoCode, mContext));
          } else {
            holder.imgLocate.setImageDrawable(mBuilder.getData().get(position).getFlagDrawable());
          }
        } else {
          holder.imgLocate.setVisibility(GONE);
        }
      }
      //endregion
      //region CheckBox
      if (holder.checkLocate != null) {
        if (mBuilder.getViewIdLocSelBuilder().getShowCheckViewForSelect()) {
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
                    LocateUtil.getCountryCodeFromIsoCode(isoCode);
                String language =
                    LocateUtil.getLanguageCodeFromIsoCode(isoCode);
                mBuilder.getCallback().onCheckItem(country, language, isoCode,true);
                mBuilder.getCallback().onClickItem(country, language, isoCode);
              }
            }
          });
        } else {
          holder.checkLocate.setVisibility(GONE);
        }
      }
      //endregion

    }

    private class ViewHolder {
      private TextView txtLocate;
      private ImageView imgLocate;
      private CheckBox checkLocate;
      private View mView;

      public ViewHolder(View view) {
        txtLocate =
            (TextView) view.findViewById(mBuilder.getViewIdLocSelBuilder().getTextViewForText());
        imgLocate =
            (ImageView) view.findViewById(mBuilder.getViewIdLocSelBuilder().getImageViewForFlag());

        checkLocate =
            (CheckBox) view.findViewById(mBuilder.getViewIdLocSelBuilder().getCheckViewForSelect());
        mView = view;
        view.setTag(this);
      }
    }
  }
}
