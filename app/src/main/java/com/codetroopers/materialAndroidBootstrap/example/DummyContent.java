package com.codetroopers.materialAndroidBootstrap.example;

import android.os.Parcelable;

import java.util.Date;

import auto.parcel.AutoParcel;

@AutoParcel
public abstract class DummyContent implements Parcelable {
    public abstract String content();

    public abstract Date creationDate();

    public static DummyContent create(final String content, final Date creationDate) {
        return new AutoParcel_DummyContent(content, creationDate);
    }
}
