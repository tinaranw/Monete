package com.uc.monete.model;

import android.os.Parcel;
import android.os.Parcelable;

public class History implements Parcelable {
    private String id;
    private String user_id;
    private String amount;
    private String type;
    private String tag;
    private String date;
    private String memo;
    private String curr_balance;

    public History(String id, String user_id, String amount, String type, String tag, String date, String memo, String curr_balance) {
        this.id = id;
        this.user_id = user_id;
        this.amount = amount;
        this.type = type;
        this.tag = tag;
        this.date = date;
        this.memo = memo;
        this.curr_balance = curr_balance;
    }

    public History(){

    }

    protected History(Parcel in) {
        id = in.readString();
        user_id = in.readString();
        amount = in.readString();
        type = in.readString();
        tag = in.readString();
        date = in.readString();
        memo = in.readString();
        curr_balance = in.readString();
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCurr_balance() {
        return curr_balance;
    }

    public void setCurr_balance(String curr_balance) {
        this.curr_balance = curr_balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(user_id);
        dest.writeString(amount);
        dest.writeString(type);
        dest.writeString(tag);
        dest.writeString(date);
        dest.writeString(memo);
        dest.writeString(curr_balance);
    }
}
