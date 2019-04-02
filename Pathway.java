package peppersapplications.com.simplescribbles;

import android.graphics.Path;

public class Pathway {
    public int color;
    public int brush;
    public Path path;

    public Pathway(int color, int brush, Path path) {
        this.color = color;
        this.brush = brush;
        this.path = path;
    }
}