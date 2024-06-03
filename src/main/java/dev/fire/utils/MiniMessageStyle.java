package dev.fire.utils;

import java.util.ArrayList;

public class MiniMessageStyle {
    public ArrayList<Integer> colorsList;
    public int bold = 0;
    public int italic = 0;
    public int underline = 0;
    public int strikethrough = 0;
    public int obfuscated = 0;

    public MiniMessageStyle() {
        this.colorsList = new ArrayList<>();
    }

    public void addColor(int color) {
        this.colorsList.add(color);
    }

    public boolean removeColor(int color) {
        if (this.colorsList.contains(color)) {
            this.colorsList.remove((Object) Integer.valueOf(color));
            return true;
        } else {
            return false;
        }
    }


}
