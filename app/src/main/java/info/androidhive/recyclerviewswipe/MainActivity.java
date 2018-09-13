package info.androidhive.recyclerviewswipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import info.androidhive.recyclerviewswipe.list_androidhive.AndroidhiveActivity;
import info.androidhive.recyclerviewswipe.list_demo.ListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAndroidHive, btnList, btnGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAndroidHive = findViewById(R.id.btnAndroidHive);
        btnList = findViewById(R.id.btnList);
        btnGrid = findViewById(R.id.btnGrid);

        btnAndroidHive.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnGrid.setOnClickListener(this);

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
                break;
            case R.id.btnAndroidHive:
                startActivity(AndroidhiveActivity.class);
                break;
        }
    }

    private void startActivity(Class classActivity) {
        Intent intent = new Intent(MainActivity.this, classActivity);
        startActivity(intent);
    }
}
