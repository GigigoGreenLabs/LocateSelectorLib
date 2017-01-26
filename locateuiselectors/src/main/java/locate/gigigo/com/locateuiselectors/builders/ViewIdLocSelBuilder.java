package locate.gigigo.com.locateuiselectors.builders;

import locate.gigigo.com.locateuiselectors.R;

/**
 * Created by nubor on 24/01/2017.
 */
public class ViewIdLocSelBuilder {
  int imageViewForFlag = R.id.imgFlag;
  int textViewForText = R.id.txtLocate;

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
}
