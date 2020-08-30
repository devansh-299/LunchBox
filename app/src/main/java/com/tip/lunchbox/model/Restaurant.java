package com.tip.lunchbox.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Restaurant {
    @SerializedName("id")
        private String id;

    @SerializedName("name")
        private String name;

    @SerializedName("url")
        private String url;

    @SerializedName("location")
        private Location location;

    @SerializedName("average_cost_for_two")
        private String averageCostForTwo;

    @SerializedName("price_range")
        private String priceRange;

    @SerializedName("currency")
        private String currency;

    @SerializedName("thumb")
        private String thumb;

    @SerializedName("featured_image")
        private String featuredImage;

    @SerializedName("photos_url")
        private String photosUrl;

    @SerializedName("menu_url")
        private String menuUrl;

    @SerializedName("events_url")
        private String eventsUrl;

    @SerializedName("user_rating")
        private UserRating userRating;

    @SerializedName("has_online_delivery")
        private String hasOnlineDelivery;

    @SerializedName("is_delivering_now")
        private String isDeliveringNow;

    @SerializedName("has_table_booking")
        private String hasTableBooking;

    @SerializedName("deeplink")
        private String deeplink;

    @SerializedName("cuisines")
        private String cuisines;

    @SerializedName("all_reviews_count")
        private String allReviewsCount;

    @SerializedName("photo_count")
        private String photoCount;

    @SerializedName("phone_numbers")
        private String phoneNumbers;

    @SerializedName("photos")
        private List<RestaurantPhotos> photos = null;

    @SerializedName("all_reviews")
        private List<Reviews> allReviews = null;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Location getLocation() {
        return location;
    }

    public String getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public String getThumb() {
        return thumb;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public String getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public String getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public String getHasTableBooking() {
        return hasTableBooking;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public String getCuisines() {
        return cuisines;
    }

    public String getAllReviewsCount() {
        return allReviewsCount;
    }

    public String getPhotoCount() {
        return photoCount;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<RestaurantPhotos> getPhotos() {
        return photos;
    }

    public List<Reviews> getAllReviews() {
        return allReviews;
    }
}
