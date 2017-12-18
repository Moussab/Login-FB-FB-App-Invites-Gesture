package com.example.root.poclogin.Facebook;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 17/12/17.
 */

public class Friend  implements Parcelable {

    private String id;
    private String name;

    public Friend() {}

    public Friend(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public Friend(String id, String name) {
        this.id = id;
        this.name = name;
    }

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


    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Friend> CREATOR = new Parcelable.Creator<Friend>()
    {
        public Friend createFromParcel(Parcel in)
        {
            return new Friend(in);
        }
        public Friend[] newArray(int size)
        {
            return new Friend[size];
        }
    };
}
