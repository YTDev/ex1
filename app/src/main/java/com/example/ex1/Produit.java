package com.example.ex1;

import java.io.Serializable;
import java.util.ArrayList;

public class Produit implements Serializable {
public static ArrayList<Produit> panier=new ArrayList<>();
    String code;
    String nom;
    Double prix;
    String cat;
    String photo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getCat() {
        return cat;
    }



    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Produit(String code,String nom, Double prix, String cat, String photo) {
        this.code=code;
        this.nom = nom;
        this.prix = prix;
        this.cat = cat;
        this.photo = photo;
    }
}
