package info.androidhive.recyclerviewswipe.decoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import info.androidhive.recyclerviewswipe.R;

public class DecorationActivity extends AppCompatActivity {

    RecyclerView rcv_decoration;
    DecorationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoration);

        rcv_decoration = findViewById(R.id.rcv_decoration);

        rcv_decoration.setHasFixedSize(true);
        rcv_decoration.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DecorationAdapter(this, getData());
        rcv_decoration.setAdapter(adapter);

        HeaderItemDecoration sectionItemDecoration =
                new HeaderItemDecoration(DecorationActivity.this,
                        getResources().getDimensionPixelSize(R.dimen.spaceHeight),
                        getResources().getDimensionPixelSize(R.dimen.header),
                        true,
                        getSectionCallback(getData()));
        rcv_decoration.addItemDecoration(sectionItemDecoration);
    }

    public ArrayList<String> getData() {
        String[] strings = getResources().getStringArray(R.array.animals);
        ArrayList<String> list = new ArrayList(Arrays.asList(strings));
        return list;
    }

    private HeaderItemDecoration.SectionCallback getSectionCallback(final List<String> items) {
        return new HeaderItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {
                return position == 0 || items.get(position).charAt(0) != items.get(position - 1).charAt(0);
            }

            @Override
            public CharSequence getSectionHeader(int position) {
                return items.get(position).subSequence(0, 1);
            }
        };
    }
}
