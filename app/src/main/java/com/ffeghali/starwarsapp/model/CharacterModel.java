package com.ffeghali.starwarsapp.model;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

public class CharacterModel implements Parcelable {
    private String name;
    private String height;
    private String mass;
    private String hair;
    private String skin;
    private String eye;
    private String birthdate;
    private String gender;
    private boolean favorite;

    public CharacterModel(String name, String height, String mass, String hair,
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

    protected CharacterModel(Parcel in) {
        name = in.readString();
        height = in.readString();
        mass = in.readString();
        hair = in.readString();
        skin = in.readString();
        eye = in.readString();
        birthdate = in.readString();
        gender = in.readString();
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            favorite = in.readBoolean();
        }*/
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(mass);
        dest.writeString(hair);
        dest.writeString(skin);
        dest.writeString(eye);
        dest.writeString(birthdate);
        dest.writeString(gender);
        //dest.writeByte((byte) (favorite ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHair() {
        return hair;
    }

    public String getSkin() {
        return skin;
    }

    public String getEye() {
        return eye;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getGender() {
        return gender;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite() { this.favorite = true;}

    public void changeFavorite() {

        this.favorite = !this.favorite;
    }

    @Override
    public String toString() {
        return name + height + mass + hair + skin + eye + birthdate + gender + favorite;
    }

}
