package com.ath.demo.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ParcelableModel implements Parcelable {
    private int mData;
    private String mData2;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
        out.writeString(mData2);
    }

    public ParcelableModel(String name) {
        mData2 = name;
        mData = 1;
    }

    private ParcelableModel(Parcel in) {
        mData = in.readInt();
        mData2 = in.readString();
    }

    public static final Parcelable.Creator<ParcelableModel> CREATOR
            = new Parcelable.Creator<ParcelableModel>() {
        public ParcelableModel createFromParcel(Parcel in) {
            return new ParcelableModel(in);
        }

        public ParcelableModel[] newArray(int size) {
            return new ParcelableModel[size];
        }
    };

    public String serialize() {
        return toString();
    }

    @Override
    public String toString() {
        return "ParcelableModel{" +
                "mData=" + mData +
                '}';
    }
}
