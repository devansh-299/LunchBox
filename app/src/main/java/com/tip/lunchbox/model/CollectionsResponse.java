
package com.tip.lunchbox.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CollectionsResponse {

    @SerializedName("collections")
    @Expose
    private List<CollectionsContainer> collectionsContainer = null;
    @SerializedName("has_more")
    @Expose
    private int hasMore;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("display_text")
    @Expose
    private String displayText;
    @SerializedName("has_total")
    @Expose
    private int hasTotal;
    @SerializedName("user_has_addresses")
    @Expose
    private boolean userHasAddresses;

    public List<CollectionsContainer> getCollectionsContainer() {
        return collectionsContainer;
    }

    public void setCollectionsContainer(List<CollectionsContainer> collectionsContainer) {
        this.collectionsContainer = collectionsContainer;
    }

    public int getHasMore() {
        return hasMore;
    }

    public void setHasMore(int hasMore) {
        this.hasMore = hasMore;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public int getHasTotal() {
        return hasTotal;
    }

    public void setHasTotal(int hasTotal) {
        this.hasTotal = hasTotal;
    }

    public boolean isUserHasAddresses() {
        return userHasAddresses;
    }

    public void setUserHasAddresses(boolean userHasAddresses) {
        this.userHasAddresses = userHasAddresses;
    }

}
