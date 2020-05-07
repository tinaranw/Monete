package com.uc.monete.activities;

import com.google.gson.annotations.SerializedName;

public class Growth {

    @SerializedName("year")
    private int Year;
    @SerializedName("growth_rate")
    private Float  Growth;

    public int getYear() {
        return Year;
    }

    public Float getGrowth() {
        return Growth;
    }
}
