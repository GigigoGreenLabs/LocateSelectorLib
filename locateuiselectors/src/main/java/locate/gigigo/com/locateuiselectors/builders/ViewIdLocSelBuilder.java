package locate.gigigo.com.locateuiselectors.builders;

import locate.gigigo.com.locateuiselectors.R;

/**
 * Created by nubor on 24/01/2017.
 */
public class ViewIdLocSelBuilder {

  //region Variables && initialization
  int imageViewForFlag = R.id.imgFlag;
  int textViewForText = R.id.txtLocate;
  int checkViewForSelect = R.id.checkLocate;

  boolean showImageViewForFlag = true;
  boolean showTextViewForText = true;
  boolean showCheckViewForSelect = true;

  //endregion

  //region Show/hide views Image/text/checkbox
  public boolean getShowImageViewForFlag() {
    return showImageViewForFlag;
  }

  public ViewIdLocSelBuilder setShowImageViewForFlag(boolean showImageViewForFlag) {
    this.showImageViewForFlag = showImageViewForFlag;
    return this;
  }

  public boolean getShowTextViewForText() {
    return showTextViewForText;
  }

  public ViewIdLocSelBuilder setShowTextViewForText(boolean showTextViewForText) {
    this.showTextViewForText = showTextViewForText;
    return this;
  }

  public boolean getShowCheckViewForSelect() {
    return showCheckViewForSelect;
  }

  public ViewIdLocSelBuilder setShowCheckViewForSelect(boolean showCheckViewForSelect) {
    this.showCheckViewForSelect = showCheckViewForSelect;
    return this;
  }

  //endregion

  //region Re-set Views Image/text/checkbox
  public int getCheckViewForSelect() {
    return checkViewForSelect;
  }

  public ViewIdLocSelBuilder setCheckViewForSelect(int checkViewForSelect) {
    this.checkViewForSelect = checkViewForSelect;
    return this;
  }

  public int getImageViewForFlag() {
    return imageViewForFlag;
  }

  public ViewIdLocSelBuilder setImageViewForFlag(int imageViewForFlag) {
    this.imageViewForFlag = imageViewForFlag;
    return this;
  }

  public int getTextViewForText() {
    return textViewForText;
  }

  public ViewIdLocSelBuilder setTextViewForText(int textViewForText) {
    this.textViewForText = textViewForText;
    return this;
  }
  //endregion

}
