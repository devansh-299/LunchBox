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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setAverageCostForTwo(String averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public void setHasOnlineDelivery(String hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public void setIsDeliveringNow(String isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public void setHasTableBooking(String hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public void setAllReviewsCount(String allReviewsCount) {
        this.allReviewsCount = allReviewsCount;
    }

    public void setPhotoCount(String photoCount) {
        this.photoCount = photoCount;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setPhotos(List<RestaurantPhotos> photos) {
        this.photos = photos;
    }

    public void setAllReviews(List<Reviews> allReviews) {
        this.allReviews = allReviews;
    }

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
