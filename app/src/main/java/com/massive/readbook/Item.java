package com.massive.readbook;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Minafaw on 27/01/2017.
 */

public class Item implements Serializable, Parcelable {
    public String kind ;
    public String id ;
    public VolumeInfo volumeInfo ;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.kind);
        dest.writeString(this.id);
        dest.writeParcelable(this.volumeInfo, flags);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.kind = in.readString();
        this.id = in.readString();
        this.volumeInfo = in.readParcelable(VolumeInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}




