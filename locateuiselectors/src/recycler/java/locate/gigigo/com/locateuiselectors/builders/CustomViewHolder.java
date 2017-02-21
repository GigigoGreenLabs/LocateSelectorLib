package locate.gigigo.com.locateuiselectors.builders;

import android.content.Context;
import android.view.ViewGroup;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolderFactory;
import java.util.HashMap;


/**
 * Created by nubor on 08/02/2017.
 */

public class CustomViewHolder extends BaseViewHolderFactory {
  private HashMap<Class,BaseViewHolder> mLstClassViewHolders;
  public CustomViewHolder(Context context, HashMap<Class, BaseViewHolder> lstClassVH) {
    super(context);
    this.mLstClassViewHolders = lstClassVH;
  }
  @Override public  BaseViewHolder create(Class valueClass, ViewGroup parent) {
      return mLstClassViewHolders.get(valueClass);
  }
}
