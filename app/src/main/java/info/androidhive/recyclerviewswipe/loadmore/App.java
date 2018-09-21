package info.androidhive.recyclerviewswipe.loadmore;

import android.graphics.drawable.Drawable;

/**
 * Created by ha_hai on 9/20/2018.
 */

public class App {

    private String name;
    private String packageName;
    private Drawable icon;

    public App() {
    }

    public App(String name, String packageName, Drawable icon) {
        this.name = name;
        this.packageName = packageName;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
