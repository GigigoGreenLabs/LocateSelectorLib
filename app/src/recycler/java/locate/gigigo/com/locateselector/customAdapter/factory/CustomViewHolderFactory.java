package locate.gigigo.com.locateselector.customAdapter.factory;

import android.content.Context;
import android.view.ViewGroup;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolderFactory;

import com.gigigo.ui.imageloader.ImageLoader;
import locate.gigigo.com.locateselector.customAdapter.entities.ImageCell;
import locate.gigigo.com.locateselector.customAdapter.entities.TextCell;
import locate.gigigo.com.locateselector.customAdapter.viewholder.ImageViewHolder;
import locate.gigigo.com.locateselector.customAdapter.viewholder.TextViewHolder;

/**
 * Created by rui.alonso on 8/11/16.
 */

public class CustomViewHolderFactory extends BaseViewHolderFactory {

  private ImageLoader imageLoader;

  public CustomViewHolderFactory(Context context, ImageLoader imageLoader) {
    super(context);
    this.imageLoader = imageLoader;
  }

  @Override public BaseViewHolder create(Class valueClass, ViewGroup parent) {
    if (valueClass == ImageCell.class) {
      return new ImageViewHolder(context, parent, imageLoader);
    } else if (valueClass == TextCell.class) {
      return new TextViewHolder(context, parent);
    } else {
      return null;
    }
  }
}
