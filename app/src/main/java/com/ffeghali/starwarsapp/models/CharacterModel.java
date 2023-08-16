package com.ffeghali.starwarsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class CharacterModel implements Parcelable {
    String name;
    int height;
    float mass;
    String hair;
    String skin;
    String eye;
    String birthdate;
    String gender;
    boolean favorite;

    public CharacterModel(String name, int height, float mass, String hair,
                          String skin, String eye, String birthdate, String gender) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hair = hair;
        this.skin = skin;
        this.eye = eye;
        this.birthdate = birthdate;
        this.gender = gender;
        this.favorite = false;
    }

    public CharacterModel() {
    }

    protected CharacterModel(Parcel in) {
        name = in.readString();
        height = in.readInt();
        mass = in.readFloat();
        hair = in.readString();
        skin = in.readString();
        eye = in.readString();
        birthdate = in.readString();
        gender = in.readString();
        favorite = in.readByte() != 0;
    }

    public static final Creator<CharacterModel> CREATOR = new Creator<CharacterModel>() {
        @Override
        public CharacterModel createFromParcel(Parcel in) {
            return new CharacterModel(in);
        }

        @Override
        public CharacterModel[] newArray(int size) {
            return new CharacterModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(height);
        parcel.writeFloat(mass);
        parcel.writeString(hair);
        parcel.writeString(skin);
        parcel.writeString(eye);
        parcel.writeString(birthdate);
        parcel.writeString(gender);
        parcel.writeByte((byte) (favorite ? 1 : 0));
    }
}
