package info.androidhive.recyclerviewswipe.loadmore;

import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.recyclerviewswipe.R;

public class LoadMoreActivity extends AppCompatActivity {

    // Store a member variable for the listener
    private PackageManager mPackageManager;
    private LoadMoreRecyclerAdapter mAdapter;
    private RecyclerView rvItems;
    private ArrayList<App> mItems;
    private int number = 0;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);

        mPackageManager = getPackageManager();
        mItems = new ArrayList<>();

        // Configure the RecyclerView
        rvItems = findViewById(R.id.rcv_load_more);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvItems.setLayoutManager(linearLayoutManager);
        mAdapter = new LoadMoreRecyclerAdapter(this, rvItems);
        rvItems.setAdapter(mAdapter);
        //load pre data
        loadNextDataFromApi();

        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadNextDataFromApi();
            }
        });
    }

    // Append the next page of data into the adapter
    // This method probably sends out a network request and appends new data items to your adapter.
    public void loadNextDataFromApi() {
        final int iStartLoading = number;

        if (number == 0) {
            dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...");
            dialog.show();
        } else {
            rvItems.post(new Runnable() {
                @Override
                public void run() {
                    mItems.add(null);
                    mAdapter.notifyItemInserted(mItems.size() - 1);
                }
            });
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                int elLoaded = 0;

                if (number != 0) {
                    mItems.remove(mItems.size() - 1);
                    mAdapter.notifyItemRemoved(mItems.size() - 1);
                }

                List<ApplicationInfo> list = mPackageManager.getInstalledApplications(PackageManager.GET_META_DATA);
                for (int i = iStartLoading; elLoaded < 10 && i < list.size(); i++) {
                    try {
                        Drawable icon = list.get(i).loadIcon(mPackageManager);
                        String name = list.get(i).loadLabel(mPackageManager).toString();
                        String packageName = list.get(i).packageName;

                        if (mPackageManager.getLaunchIntentForPackage(list.get(i).packageName) != null) {
                            App app = new App(name, packageName, icon);
                            mItems.add(app);
                            elLoaded++;
                        }

                        number ++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (dialog.isShowing()) {
                    dialog.cancel();
                }

                rvItems.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setApps(mItems);
                        mAdapter.notifyDataSetChanged();
                        mAdapter.setLoaded();
                    }
                });
            }
        }, 1000);
    }
}
