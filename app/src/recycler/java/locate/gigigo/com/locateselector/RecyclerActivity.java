package locate.gigigo.com.locateselector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import com.gigigo.baserecycleradapter.adapter.BaseRecyclerAdapter;
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder;
import com.gigigo.ui.imageloader.ImageLoader;
import com.gigigo.ui.imageloader.glide.GlideImageLoaderImp;
import java.util.HashMap;
import java.util.List;
import locate.gigigo.com.locateselector.customAdapter.DataGenerator;
import locate.gigigo.com.locateselector.customAdapter.entities.ImageCell;
import locate.gigigo.com.locateselector.customAdapter.entities.TextCell;
import locate.gigigo.com.locateselector.customAdapter.factory.CustomViewHolderFactory;
import locate.gigigo.com.locateselector.customAdapter.viewholder.ImageViewHolder;
import locate.gigigo.com.locateselector.customAdapter.viewholder.TextViewHolder;
import locate.gigigo.com.locateuiselectors.LocateSelectorRecyclerView;
import locate.gigigo.com.locateuiselectors.builders.CustomViewHolder;
import locate.gigigo.com.locateuiselectors.builders.LocateSelectorRecyclerBuilder;

public class RecyclerActivity extends AppCompatActivity {

  private BaseRecyclerAdapter mAdapter;
  private ImageLoader mImageLoader;
  int numberRows = 9;
  int numberColumns = 3;

  private void initAdapter() {
    //imageloader
    mImageLoader = new GlideImageLoaderImp(this);
    //viewholder
    CustomViewHolderFactory customViewHolderFactory =
        new CustomViewHolderFactory(this, mImageLoader);
    //adapter with diferents vh/pojosclass
    mAdapter = new BaseRecyclerAdapter(customViewHolderFactory);
    mAdapter.bind(ImageCell.class, ImageViewHolder.class);
    mAdapter.bind(TextCell.class, TextViewHolder.class);
    mAdapter.append(DataGenerator.generateRandomDataList(numberColumns * numberRows));
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.content_scrolling_recycler);
    initLayout();
  }

  private void initLayout() {
    initAdapter();
    createRecyclerViewCustomItemTemplate();
  }

  private void createRecyclerViewCustomItemTemplate() {
    LocateSelectorRecyclerBuilder builder = new LocateSelectorRecyclerBuilder(RecyclerActivity.this)
        .setAdapter(mAdapter)
        .setLayoutManager(new GridLayoutManager(this, numberColumns))
        .build();

    LocateSelectorRecyclerView locateListView0 =
        (LocateSelectorRecyclerView) findViewById(R.id.listview1);

    locateListView0.init(builder);
  }
}




