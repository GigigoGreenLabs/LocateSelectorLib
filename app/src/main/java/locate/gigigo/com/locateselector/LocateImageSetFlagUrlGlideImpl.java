package locate.gigigo.com.locateselector;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import locate.gigigo.com.locateuiselectors.LocateImageSetFlag;
import locate.gigigo.com.locateuiselectors.model.LocateModel;

/**
 * Created by nubor on 31/01/2017.
 */
public class LocateImageSetFlagUrlGlideImpl implements LocateImageSetFlag {
  @Override
  public void setFlagIntoImageView(LocateModel model, Context context, ImageView imageView) {
    if (model.getFlagURL()  == "") {
      //todo placeHOLDER?
      // imageView.setImageDrawable(LocateUtil.getDrawable(model.getIsoCode(), context));
    } else {
      Glide.
          with(context).
          load(model.getFlagURL()).
          asBitmap().
          into(imageView);
    }
  }
}
