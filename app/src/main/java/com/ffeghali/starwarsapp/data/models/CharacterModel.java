package com.ffeghali.starwarsapp.data.models;

public class CharacterModel {
//    String uid;
    String name;
//    int height;
//    float mass;
//    String hair;
//    String skin;
//    String eye;
//    String birthdate;
//    String gender;
    boolean favorite;

    public CharacterModel(String name, boolean favorite) {
//        this.uid = uid;
        this.name = name;
//        this.height = height;
//        this.mass = mass;
//        this.hair = hair;
//        this.skin = skin;
//        this.eye = eye;
//        this.birthdate = birthdate;
//        this.gender = gender;
        this.favorite = false;
    }
    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
