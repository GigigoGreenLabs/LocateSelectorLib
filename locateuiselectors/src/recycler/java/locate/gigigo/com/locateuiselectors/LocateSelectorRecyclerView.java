package locate.gigigo.com.locateuiselectors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
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
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorBuilder;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorRecyclerBuilder;

/**
 * Created by nubor on 24/01/2017.
 */
public class LocateSelectorRecyclerView extends RecyclerView {
  LocateSelectorRecyclerBuilder mBuilder;
  //region constructors
  public LocateSelectorRecyclerView(Context context) {
    super(context);
  }

  public LocateSelectorRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public LocateSelectorRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
  //endregion

  public void init(LocateSelectorRecyclerBuilder builder) {
    this.mBuilder = builder;
    this.setLayoutManager(mBuilder.getLayoutManager());
    this.setAdapter(mBuilder.getAdapter());
  }
}
