package com.daengproject.msi.myapplication.simple_list;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class AbstractModel implements Parcelable {

    private String title;

    private String message;


    public AbstractModel(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public AbstractModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.message);
    }

    protected AbstractModel(Parcel in) {
        this.title = in.readString();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<AbstractModel> CREATOR = new Parcelable.Creator<AbstractModel>() {
        @Override
        public AbstractModel createFromParcel(Parcel source) {
            return new AbstractModel(source);
        }

        @Override
        public AbstractModel[] newArray(int size) {
            return new AbstractModel[size];
        }
    };
}
