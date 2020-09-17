
package com.tip.lunchbox.model;

import java.util.List;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {

    @SerializedName("DiningOptions")
    @Expose
    private DiningOptions diningOptions;
    @SerializedName("apikey")
    @Expose
    private String apikey;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("switch_to_order_menu")
    @Expose
    private long switchToOrderMenu;
    @SerializedName("cuisines")
    @Expose
    private String cuisines;
    @SerializedName("timings")
    @Expose
    private String timings;
    @SerializedName("average_cost_for_two")
    @Expose
    private long averageCostForTwo;
    @SerializedName("price_range")
    @Expose
    private long priceRange;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("highlights")
    @Expose
    private List<String> highlights = null;
    @SerializedName("offers")
    @Expose
    private List<Object> offers = null;
    @SerializedName("opentable_support")
    @Expose
    private long opentableSupport;
    @SerializedName("is_zomato_book_res")
    @Expose
    private long isZomatoBookRes;
    @SerializedName("mezzo_provider")
    @Expose
    private String mezzoProvider;
    @SerializedName("is_book_form_web_view")
    @Expose
    private long isBookFormWebView;
    @SerializedName("book_form_web_view_url")
    @Expose
    private String bookFormWebViewUrl;
    @SerializedName("book_again_url")
    @Expose
    private String bookAgainUrl;
    @SerializedName("thumb")
    @Expose
    private String thumb;
    @SerializedName("user_rating")
    @Expose
    private UserRating userRating;
    @SerializedName("all_reviews_count")
    @Expose
    private long allReviewsCount;
    @SerializedName("photos_url")
    @Expose
    private String photosUrl;
    @SerializedName("photo_count")
    @Expose
    private long photoCount;
    @SerializedName("menu_url")
    @Expose
    private String menuUrl;
    @SerializedName("featured_image")
    @Expose
    private String featuredImage;
    @SerializedName("has_online_delivery")
    @Expose
    private long hasOnlineDelivery;
    @SerializedName("is_delivering_now")
    @Expose
    private long isDeliveringNow;
    @SerializedName("store_type")
    @Expose
    private String storeType;
    @SerializedName("include_bogo_offers")
    @Expose
    private boolean includeBogoOffers;
    @SerializedName("deeplink")
    @Expose
    private String deeplink;
    @SerializedName("order_url")
    @Expose
    private String orderUrl;
    @SerializedName("order_deeplink")
    @Expose
    private String orderDeeplink;
    @SerializedName("is_table_reservation_supported")
    @Expose
    private long isTableReservationSupported;
    @SerializedName("has_table_booking")
    @Expose
    private long hasTableBooking;
    @SerializedName("events_url")
    @Expose
    private String eventsUrl;
    @SerializedName("phone_numbers")
    @Expose
    private String phoneNumbers;
    @SerializedName("all_reviews")
    @Expose
    private AllReviews allReviews;
    @SerializedName("establishment")
    @Expose
    private List<String> establishment = null;
    @SerializedName("establishment_types")
    @Expose
    private List<Object> establishmentTypes = null;

    private Mapinfo mapinfo;

    public DiningOptions getDiningOptions() {
        return diningOptions;
    }

    public void setDiningOptions(DiningOptions diningOptions) {
        this.diningOptions = diningOptions;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getSwitchToOrderMenu() {
        return switchToOrderMenu;
    }

    public void setSwitchToOrderMenu(long switchToOrderMenu) {
        this.switchToOrderMenu = switchToOrderMenu;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public long getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(long averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public long getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(long priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<String> highlights) {
        this.highlights = highlights;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    public long getOpentableSupport() {
        return opentableSupport;
    }

    public void setOpentableSupport(long opentableSupport) {
        this.opentableSupport = opentableSupport;
    }

    public long getIsZomatoBookRes() {
        return isZomatoBookRes;
    }

    public void setIsZomatoBookRes(long isZomatoBookRes) {
        this.isZomatoBookRes = isZomatoBookRes;
    }

    public String getMezzoProvider() {
        return mezzoProvider;
    }

    public void setMezzoProvider(String mezzoProvider) {
        this.mezzoProvider = mezzoProvider;
    }

    public long getIsBookFormWebView() {
        return isBookFormWebView;
    }

    public void setIsBookFormWebView(long isBookFormWebView) {
        this.isBookFormWebView = isBookFormWebView;
    }

    public String getBookFormWebViewUrl() {
        return bookFormWebViewUrl;
    }

    public void setBookFormWebViewUrl(String bookFormWebViewUrl) {
        this.bookFormWebViewUrl = bookFormWebViewUrl;
    }

    public String getBookAgainUrl() {
        return bookAgainUrl;
    }

    public void setBookAgainUrl(String bookAgainUrl) {
        this.bookAgainUrl = bookAgainUrl;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public long getAllReviewsCount() {
        return allReviewsCount;
    }

    public void setAllReviewsCount(long allReviewsCount) {
        this.allReviewsCount = allReviewsCount;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public long getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(long photoCount) {
        this.photoCount = photoCount;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public long getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(long hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public long getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(long isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public boolean isIncludeBogoOffers() {
        return includeBogoOffers;
    }

    public void setIncludeBogoOffers(boolean includeBogoOffers) {
        this.includeBogoOffers = includeBogoOffers;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public String getOrderUrl() {
        return orderUrl;
    }

    public void setOrderUrl(String orderUrl) {
        this.orderUrl = orderUrl;
    }

    public String getOrderDeeplink() {
        return orderDeeplink;
    }

    public void setOrderDeeplink(String orderDeeplink) {
        this.orderDeeplink = orderDeeplink;
    }

    public long getIsTableReservationSupported() {
        return isTableReservationSupported;
    }

    public void setIsTableReservationSupported(long isTableReservationSupported) {
        this.isTableReservationSupported = isTableReservationSupported;
    }

    public long getHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(long hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public AllReviews getAllReviews() {
        return allReviews;
    }

    public void setAllReviews(AllReviews allReviews) {
        this.allReviews = allReviews;
    }

    public List<String> getEstablishment() {
        return establishment;
    }

    public void setEstablishment(List<String> establishment) {
        this.establishment = establishment;
    }

    public List<Object> getEstablishmentTypes() {
        return establishmentTypes;
    }

    public void setEstablishmentTypes(List<Object> establishmentTypes) {
        this.establishmentTypes = establishmentTypes;
    }

    public Mapinfo getMapinfo() {
        if (this.mapinfo ==null)
            setMapinfo();
        return mapinfo;
    }

    public void setMapinfo (){
        double latitude = Double.parseDouble(this.location.getLatitude());
        double longitude = Double.parseDouble(this.location.getLongitude());
        this.mapinfo = new Mapinfo(new LatLng(latitude,longitude),this.name,this.cuisines);
    }



    public class Mapinfo{

        private LatLng latLng;
        private String name;
        private String desc;

        public Mapinfo(LatLng latLng, String name,String desc) {
            this.latLng = latLng;
            this.name = name;
            this.desc = desc;
        }

        public LatLng getLatLng() {
            return latLng;
        }

        public void setLatLng(LatLng latLng) {
            this.latLng = latLng;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
