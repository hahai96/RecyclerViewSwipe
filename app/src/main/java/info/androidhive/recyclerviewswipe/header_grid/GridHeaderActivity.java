package info.androidhive.recyclerviewswipe.header_grid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.androidhive.recyclerviewswipe.R;

public class GridHeaderActivity extends AppCompatActivity {

    private RecyclerView rcvGridHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_grid);

        rcvGridHeader = findViewById(R.id.rcv_grid_header);

        ArrayList<String> items = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.items_grid)));

        final GridLayoutManager manager = new GridLayoutManager(this, 3);
        rcvGridHeader.setLayoutManager(manager);

        final HeaderNumberedAdapter adapter
                = new HeaderNumberedAdapter(this, items);
        rcvGridHeader.setAdapter(adapter);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isHeader(position) ? manager.getSpanCount() : 1;
            }
        });

        HeaderItemDecoration sectionItemDecoration =
                new HeaderItemDecoration(getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(items));
        rcvGridHeader.addItemDecoration(sectionItemDecoration);

    }

    private HeaderItemDecoration.SectionCallback getSectionCallback(final List<String> list) {
        return new HeaderItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return list.get(position).length() == 1 || position == 0 || list.get(position).charAt(0) != list.get(position - 1).charAt(0);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return list.get(position).subSequence(0, 1);
            }
        };
    }
}
