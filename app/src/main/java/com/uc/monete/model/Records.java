package com.uc.monete.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Records implements Parcelable {
    private static String value_id;
    private static String value_type;
    private static String value_amount;
    public Records() {
    }

    public Records(String value_id, String value_type, String value_amount) {
        this.value_id = value_id;
        this.value_type = value_type;
        this.value_amount = value_amount;
    }

    public static String getValue_id() {
        return value_id;
    }

    public void setValue_id(String value_id) {
        this.value_id = value_id;
    }

    public static String getValue_type() {
        return value_type;
    }

    public void setValue_type(String value_type) {
        this.value_type = value_type;
    }

    public static String getValue_amount() {
        return value_amount;
    }

    public void setValue_amount(String value_amount) {
        this.value_amount = value_amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value_id);
        dest.writeString(this.value_type);
        dest.writeString(this.value_amount);
    }

    protected Records(Parcel in) {
        this.value_id = in.readString();
        this.value_type = in.readString();
        this.value_amount = in.readString();
    }

    public static final Parcelable.Creator<Records> CREATOR = new Parcelable.Creator<Records>() {
        @Override
        public Records createFromParcel(Parcel source) {
            return new Records(source);
        }

        @Override
        public Records[] newArray(int size) {
            return new Records[size];
        }
    };
}
