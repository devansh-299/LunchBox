
package com.tip.lunchbox.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiningOptions {

    @SerializedName("has_menu_status")
    @Expose
    private HasMenuStatus hasMenuStatus;
    @SerializedName("res_id")
    @Expose
    private long resId;
    @SerializedName("is_grocery_store")
    @Expose
    private boolean isGroceryStore;

    public HasMenuStatus getHasMenuStatus() {
        return hasMenuStatus;
    }

    public void setHasMenuStatus(HasMenuStatus hasMenuStatus) {
        this.hasMenuStatus = hasMenuStatus;
    }

    public long getResId() {
        return resId;
    }

    public void setResId(long resId) {
        this.resId = resId;
    }

    public boolean isIsGroceryStore() {
        return isGroceryStore;
    }

    public void setIsGroceryStore(boolean isGroceryStore) {
        this.isGroceryStore = isGroceryStore;
    }

}
