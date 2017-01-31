package locate.gigigo.com.locateuiselectors;

import android.content.Context;
import android.widget.ImageView;
import locate.gigigo.com.locateuiselectors.model.LocateModel;

/**
 * Created by nubor on 31/01/2017.
 */
public class LocateImageSetFlagDrawableImpl implements LocateImageSetFlag {
  @Override
  public void setFlagIntoImageView(LocateModel model, Context context, ImageView imageView) {
    if (model.getFlagDrawable() == null) {
      imageView.setImageDrawable(LocateUtil.getDrawable(model.getIsoCode(), context));
    } else {
      imageView.setImageDrawable(model.getFlagDrawable());
    }
  }
}

