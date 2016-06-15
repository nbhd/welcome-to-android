package net.boondockradio.recyclerview.dto;

import android.support.annotation.DrawableRes;

public class StateItem {

    public String title;
    public int resId;

    public StateItem(String title, @DrawableRes int resId) {
        this.title = title;
        this.resId = resId;
    }
}
