package locate.gigigo.com.locateuiselectors.builders;

import android.content.Context;
import android.graphics.Typeface;
import java.util.List;
import locate.gigigo.com.locateuiselectors.R;
import locate.gigigo.com.locateuiselectors.model.LocateModel;
import locate.gigigo.com.locateuiselectors.LocateSelectorCallback;
import locate.gigigo.com.locateuiselectors.LocateSelectorUIMode;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateSelectorBuilder {
  //region Variables
  //todo maybe in future create sub Builders, and keep in this builder all shared data, and extend it
  //in the future maybe use reclycler and change the layoutmanager(LocateSelectorRecyclerBuilder)
  Context mContext; //list//combo//mandatory
  LocateSelectorUIMode mLocateSelectorUIMode;//list//combo//mandatory
  int mItem_Layout = -1;
  //list//combo //each control Spiner||ListView if value.equals(-1) set item_layout by default
  List<LocateModel> mData;//list//combo
  LocateSelectorCallback mCallback;
  Typeface fontTypeFace;//list//combo
  String defaultText = "[Select One..]";//combo
  ViewIdLocSelBuilder mViewIdLocSelBuilder = new ViewIdLocSelBuilder();//list//combo
  boolean showIsoCodeInRowText = true;//list//combo
  //endregion

  //region CONSTRUCTOR Context/LocateSelectorUIMode
  public LocateSelectorBuilder(Context mContext, LocateSelectorUIMode mLocateSelectorUIMode) {
    this.mContext = mContext;
    this.mLocateSelectorUIMode = mLocateSelectorUIMode;
    //todo more default values
  }
  public Context getContext() {
    return mContext;
  }

  public LocateSelectorUIMode getLocateSelectorUIMode() {
    return mLocateSelectorUIMode;
  }
  //endregion

  //region ShowIsoCodeInRowText
  public boolean getShowIsoCodeInRowText() {
    return showIsoCodeInRowText;
  }

  public LocateSelectorBuilder setShowIsoCodeInRowText(boolean showIsoCodeInRowText) {
    this.showIsoCodeInRowText = showIsoCodeInRowText;
    return this;
  }
  //endregion

  //region ViewIdLocSelBuilder
  public ViewIdLocSelBuilder getViewIdLocSelBuilder() {
    return mViewIdLocSelBuilder;
  }

  public LocateSelectorBuilder setViewIdLocSelBuilder(ViewIdLocSelBuilder mViewIdLocSelBuilder) {
    this.mViewIdLocSelBuilder = mViewIdLocSelBuilder;
    return this;
  }
  //endregion

  //region FontTypeFace
  public Typeface getFontTypeFace() {
    return fontTypeFace;
  }

  public LocateSelectorBuilder setFontTypeFace(Typeface fontTypeFace) {
    this.fontTypeFace = fontTypeFace;
    return this;
  }
  //endregion

  //region DefaultText for Spinner
  public String getDefaultText() {
    return defaultText;
  }

  public LocateSelectorBuilder setDefaultText(String defaultText) {
    this.defaultText = defaultText;
    return this;
  }
  //endregion

  //region Callback
  public LocateSelectorCallback getCallback() {
    return mCallback;
  }

  public LocateSelectorBuilder setCallback(LocateSelectorCallback mCallback) {
    this.mCallback = mCallback;
    return this;
  }
  //endregion

  //region ItemLayout
  public int getItemLayout() {
    return mItem_Layout;
  }

  public LocateSelectorBuilder setItem_Layout(int mItem_Layout) {
    this.mItem_Layout = mItem_Layout;
    return this;
  }
  //endregion

  //region Data
  public List<LocateModel> getData() {
    return mData;
  }

  public LocateSelectorBuilder setData(List<LocateModel> mData) {
    this.mData = mData;
    return this;
  }
  //endregion

}
