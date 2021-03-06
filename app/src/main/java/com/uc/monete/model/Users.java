package com.uc.monete.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
    private String id;
    private String name;
    private String email;
    private String password;
    private String balance;
    private String coins;
    private String themes;
    private String limit;

    public Users() {
    }

    public Users(String id, String name, String email, String password, String balance, String coins, String themes, String limit) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.coins = coins;
        this.themes = themes;
        this.limit = limit;
    }

    protected Users(Parcel in) {
        id = in.readString();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        balance = in.readString();
        coins = in.readString();
        themes = in.readString();
        limit = in.readString();
    }

    public static final Creator<Users> CREATOR = new Creator<Users>() {
        @Override
        public Users createFromParcel(Parcel in) {
            return new Users(in);
        }

        @Override
        public Users[] newArray(int size) {
            return new Users[size];
        }
    };

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getThemes() {
        return themes;
    }

    public void setThemes(String themes) {
        this.themes = themes;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(balance);
        dest.writeString(coins);
        dest.writeString(themes);
        dest.writeString(limit);
    }
}
