package com.tip.lunchbox.data;

import java.util.HashMap;
import java.util.List;

public class SearchQuery {
    private HashMap<String, String> params = new HashMap<>();

    public SearchQuery addEntity(Entity entity) {
        params.put("entity_id", Integer.toString(entity.getId()));
        params.put("entity_type", entity.getType());
        return this;
    }

    public SearchQuery addQuery(String query) {
        params.put("q", query);
        return this;
    }

    public SearchQuery addStart(int start) {
        params.put("start", Integer.toString(start));
        return this;
    }

    public SearchQuery addCount(int count) {
        params.put("count", Integer.toString(count));
        return this;
    }

    public SearchQuery addGeoLocation(double lat, double lng) {
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lng));
        return this;
    }

    public SearchQuery addGeoLocation(double lat, double lng, double radius) {
        params.put("lat", Double.toString(lat));
        params.put("lon", Double.toString(lng));
        params.put("radius", Double.toString(radius));
        return this;
    }

    // Data will be fetched by api call
    public SearchQuery addCuisines(List<Integer> ids) {
        params.put("cuisines", ids.toString());
        return this;
    }

    // Data will be fetched by api call
    public SearchQuery addEstablishmentType(String type) {
        params.put("establishment_type", type);
        return this;
    }

    // Data will be fetched by api call
    public SearchQuery addCollection(int id) {
        params.put("collection_id", Integer.toString(id));
        return this;
    }

    // Data will be fetched by api call
    public SearchQuery addCategory(List<Integer> ids) {
        params.put("category", ids.toString());
        return this;
    }

    public SearchQuery sort(Sort sort) {
        params.put("sort", sort.getMethod());
        return this;
    }

    public SearchQuery order(Order order) {
        params.put("order", order.getOrder());
        return this;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public class Sort {
        private String method;

        public Sort byCost() {
            method = "cost";
            return this;
        }

        public Sort byRating() {
            method = "rating";
            return this;
        }

        public Sort byDistance() {
            method = "real_distance";
            return this;
        }

        public String getMethod() {
            return method;
        }
    }

    public class Order {
        private String order;

        public Order asc() {
            order = "asc";
            return this;
        }

        public Order desc() {
            order = "desc";
            return this;
        }

        public String getOrder() {
            return order;
        }
    }

    public static class Entity {
        private String type;
        private int id;

        public Entity city(int id) {
            type = "city";
            this.id = id;
            return this;
        }

        public Entity subzone(int id) {
            type = "subzone";
            this.id = id;
            return this;
        }

        public Entity zone(int id) {
            type = "zone";
            this.id = id;
            return this;
        }

        public Entity landmark(int id) {
            type = "landmark";
            this.id = id;
            return this;
        }

        public Entity metro(int id) {
            type = "metro";
            this.id = id;
            return this;
        }

        public Entity group(int id) {
            type = "group";
            this.id = id;
            return this;
        }

        public String getType() {
            return type;
        }

        public int getId() {
            return id;
        }
    }
}
