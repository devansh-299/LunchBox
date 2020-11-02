package com.tip.lunchbox.model.zomato;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryContainer {

    @SerializedName("categories")
    @Expose
    private Category categories;

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

}
