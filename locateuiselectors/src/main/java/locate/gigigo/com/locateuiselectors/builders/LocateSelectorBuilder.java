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

  Context mContext; //mandatory
  LocateSelectorUIMode mLocateSelectorUIMode;//mandatory
  int mItem_Layout = -1; //LocateSelectorUIModedetermine that for default
  List<LocateModel> mData;
  LocateSelectorCallback mCallback;
  Typeface fontTypeFace;//list//combo
  String defaultText = "[Select One..]";//now only for combo

  ViewIdLocSelBuilder mViewIdLocSelBuilder= new ViewIdLocSelBuilder();

  public ViewIdLocSelBuilder getmViewIdLocSelBuilder() {
    return mViewIdLocSelBuilder;
  }

  public LocateSelectorBuilder setmViewIdLocSelBuilder(ViewIdLocSelBuilder mViewIdLocSelBuilder) {
    this.mViewIdLocSelBuilder = mViewIdLocSelBuilder;
    return this;
  }

  public Typeface getFontTypeFace() {
    return fontTypeFace;
  }

  public LocateSelectorBuilder setFontTypeFace(Typeface fontTypeFace) {
    this.fontTypeFace = fontTypeFace;
    return this;
  }

  public String getDefaultText() {
    return defaultText;
  }

  public LocateSelectorBuilder setDefaultText(String defaultText) {
    this.defaultText = defaultText;
    return this;
  }
  //fixme Object LayorManagerUtilizadoPOrLaListView;//lsit

  //Duda, crear otra builder xclusiva para List(layoutmanager) y otra para spinner

  public LocateSelectorBuilder(Context mContext, LocateSelectorUIMode mLocateSelectorUIMode) {
    this.mContext = mContext;
    this.mLocateSelectorUIMode = mLocateSelectorUIMode;

    //todo more default values

  }

  public LocateSelectorCallback getmCallback() {
    return mCallback;
  }

  public LocateSelectorBuilder setmCallback(LocateSelectorCallback mCallback) {
    this.mCallback = mCallback;
    return this;
  }

  public Context getmContext() {
    return mContext;
  }

  public LocateSelectorUIMode getmLocateSelectorUIMode() {
    return mLocateSelectorUIMode;
  }

  public int getmItem_Layout() {
    return mItem_Layout;
  }

  public List<LocateModel> getmData() {
    return mData;
  }

  public LocateSelectorBuilder setmItem_Layout(int mItem_Layout) {
    this.mItem_Layout = mItem_Layout;
    return this;
  }

  public LocateSelectorBuilder setmData(List<LocateModel> mData) {
    this.mData = mData;
    return this;
  }

}
