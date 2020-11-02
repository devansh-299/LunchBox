package com.tip.lunchbox.model.zomato;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponse {

    @SerializedName("categories")
    @Expose
    private List<CategoryContainer> categories = null;

    public List<CategoryContainer> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryContainer> categories) {
        this.categories = categories;
    }

}
