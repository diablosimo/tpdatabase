package com.example.tpdatabase;

public class Personne {
    private int id;
    private String nom,prenom,numTel;

    public Personne(int id,String nom, String prenom, String numTel) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
    }

    public int getId() {
        return id;
    }

    public Personne() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numTel='" + numTel + '\'' +
                '}';
    }
}
