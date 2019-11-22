package com.courier.trackmonitor.api.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by basselchaitani on 2019-11-22.
 */
public class Event implements Parcelable {

    @SerializedName("companyName")
    private String companyName;

    @SerializedName("lastUpdate")
    private String lastUpdate;

    @SerializedName("lastUpdatedInMinutes")
    private int lastUpdatedInMinutes;

    @SerializedName("group")
    private String group;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getLastUpdatedInMinutes() {
        return lastUpdatedInMinutes;
    }

    public void setLastUpdatedInMinutes(int lastUpdatedInMinutes) {
        this.lastUpdatedInMinutes = lastUpdatedInMinutes;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.companyName);
        dest.writeString(this.lastUpdate);
        dest.writeInt(this.lastUpdatedInMinutes);
        dest.writeString(this.group);
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.companyName = in.readString();
        this.lastUpdate = in.readString();
        this.lastUpdatedInMinutes = in.readInt();
        this.group = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
