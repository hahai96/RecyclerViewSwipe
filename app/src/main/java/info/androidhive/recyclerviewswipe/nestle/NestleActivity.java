package info.androidhive.recyclerviewswipe.nestle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import info.androidhive.recyclerviewswipe.MyApplication;
import info.androidhive.recyclerviewswipe.R;
import info.androidhive.recyclerviewswipe.list_androidhive_swipe_drag_and_drop.model.Item;

public class NestleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HashMap<Integer, List<Item>> cartList;
    private NestleRecyclerViewAdapter mAdapter;

    private static final String URL = "https://api.androidhive.info/json/menu.json";
    private static final String TAG = NestleActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nestle);

        recyclerView = findViewById(R.id.rcv_nestle);
        cartList = new HashMap<>();
        mAdapter = new NestleRecyclerViewAdapter(cartList, this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        // making http call and fetching menu json
        prepareCart();

    }

    /**
     * method make volley network call and parses json
     */
    private void prepareCart() {
        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        List<Item> items = new Gson().fromJson(response.toString(), new TypeToken<List<Item>>() {
                        }.getType());

                        // adding items to cart list
                        cartList.clear();
                        cartList.put(0, items);
                        cartList.put(1, items);
                        cartList.put(2, items);
                        cartList.put(3, items);
                        cartList.put(4, items);

                        // refreshing recycler view
                        mAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);
    }
}
