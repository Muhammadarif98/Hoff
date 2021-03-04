package com.example.hoff;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prices {

    @SerializedName("new")
    @Expose
    private int _new;
    @SerializedName("old")
    @Expose
    private int old;

    public int getNew() {
        return _new;
    }

    public void setNew(int _new) {
        this._new = _new;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

}
