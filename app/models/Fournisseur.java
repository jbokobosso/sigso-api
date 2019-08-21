package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Fournisseur {
    @Id
    public Long idFournisseur;
    public String raisonSociale, tel, adresseFournisseur;

    public  static Finder<Long, Fournisseur> find = new Finder<>(Fournisseur.class);

    public Fournisseur() {
    }

    public Fournisseur(String raisonSociale, String tel, String adresseFournisseur) {
        this.raisonSociale = raisonSociale;
        this.tel = tel;
        this.adresseFournisseur = adresseFournisseur;
    }
}