package locate.gigigo.com.locateuiselectors.builders;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.gigigo.baserecycleradapter.adapter.BaseRecyclerAdapter;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder;
import java.util.HashMap;
import java.util.List;

/**
 * Created by nubor on 23/01/2017.
 */
public class LocateSelectorRecyclerBuilder {

  Context mContext;
  BaseRecyclerAdapter<Object> mAdapter;
  private GridLayoutManager layoutManager;
  private CustomViewHolder mViewHolder;
  //private List<Object> mData;
  //private HashMap<Class, Class> mBindablesClassforAdapter;

  public LocateSelectorRecyclerBuilder(Context context) {
    this.mContext = context;
  }

  public Context getContext() {
    return mContext;
  }

  //public CustomViewHolder getViewHolder() {
  //  return mViewHolder;
  //}
  //
  //public LocateSelectorRecyclerBuilder setViewHolder(CustomViewHolder mViewHolder) {
  //  this.mViewHolder = mViewHolder;
  //  return this;
  //}

  public RecyclerView.Adapter<BaseViewHolder> getAdapter() {
    return mAdapter;
  }

  public LocateSelectorRecyclerBuilder setAdapter(BaseRecyclerAdapter<Object> adapter) {
    this.mAdapter = adapter;
    return this;
  }

  public LocateSelectorRecyclerBuilder setLayoutManager(GridLayoutManager layoutManager) {
    this.layoutManager = layoutManager;
    return this;
  }

  public LocateSelectorRecyclerBuilder build() {
    return this;
  }

  public GridLayoutManager getLayoutManager() {
    return layoutManager;
  }

  //public LocateSelectorRecyclerBuilder setData(List<Object> data) {
  //  this.mData = data;
  //  return this;
  //}

  //public HashMap<Class, Class> getBindablesClassforAdapter() {
  //  return mBindablesClassforAdapter;
  //}
  //
  //public LocateSelectorRecyclerBuilder setBindablesClassforAdapter(
  //    HashMap<Class, Class> mBindablesClassforAdapter) {
  //  this.mBindablesClassforAdapter = mBindablesClassforAdapter;
  //  return this;
  //}
}





