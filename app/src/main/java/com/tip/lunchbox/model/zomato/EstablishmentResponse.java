package com.tip.lunchbox.model.zomato;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstablishmentResponse {

    @SerializedName("establishments")
    @Expose
    private List<EstablishmentContainer> establishments = null;

    public List<EstablishmentContainer> getEstablishments() {
        return establishments;
    }

    public void setEstablishments(List<EstablishmentContainer> establishments) {
        this.establishments = establishments;
    }

}
