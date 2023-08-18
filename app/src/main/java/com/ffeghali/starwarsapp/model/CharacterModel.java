package com.ffeghali.starwarsapp.model;


public class CharacterModel {
    String name;
    String height;
    String mass;
    String hair;
    String skin;
    String eye;
    String birthdate;
    String gender;
    boolean favorite;

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

    public CharacterModel() {
    }

    public String getName() {
        return name;
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
