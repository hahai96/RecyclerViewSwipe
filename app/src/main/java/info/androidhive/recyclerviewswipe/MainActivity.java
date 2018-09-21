package info.androidhive.recyclerviewswipe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import info.androidhive.recyclerviewswipe.gid_swipe_drag_and_drop.GridActivity;
import info.androidhive.recyclerviewswipe.decoration.DecorationActivity;
import info.androidhive.recyclerviewswipe.header_grid.GridHeaderActivity;
import info.androidhive.recyclerviewswipe.list_androidhive_swipe_drag_and_drop.AndroidhiveActivity;
import info.androidhive.recyclerviewswipe.list_swipe_drag_and_drop.ListActivity;
import info.androidhive.recyclerviewswipe.loadmore.LoadMoreActivity;
import info.androidhive.recyclerviewswipe.nestle.NestleActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAndroidHive, btnList, btnGrid;
    Button btnDecoration, btnNestleRcv, btnLoadMore;
    Button btnGridHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAndroidHive = findViewById(R.id.btnAndroidHive);
        btnList = findViewById(R.id.btnList);
        btnGrid = findViewById(R.id.btnGrid);
        btnNestleRcv = findViewById(R.id.btnNestleRcv);
        btnDecoration = findViewById(R.id.btnItemDecoration);
        btnLoadMore = findViewById(R.id.btnLoadMore);
        btnGridHeader = findViewById(R.id.btnGridHeader);

        btnAndroidHive.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnGrid.setOnClickListener(this);
        btnDecoration.setOnClickListener(this);
        btnNestleRcv.setOnClickListener(this);
        btnLoadMore.setOnClickListener(this);
        btnGridHeader.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds cartList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnList:
                startActivity(ListActivity.class);
                break;
            case R.id.btnGrid:
                startActivity(GridActivity.class);
                break;
            case R.id.btnAndroidHive:
                startActivity(AndroidhiveActivity.class);
                break;
            case R.id.btnItemDecoration:
                startActivity(DecorationActivity.class);
                break;
            case R.id.btnNestleRcv:
                startActivity(NestleActivity.class);
                break;
            case R.id.btnLoadMore:
                startActivity(LoadMoreActivity.class);
                break;
            case R.id.btnGridHeader:
                startActivity(GridHeaderActivity.class);
                break;
        }
    }

    private void startActivity(Class classActivity) {
        Intent intent = new Intent(MainActivity.this, classActivity);
        startActivity(intent);
    }
}
