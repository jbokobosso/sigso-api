package models;

import play.data.format.Formats;

import javax.persistence.*;

import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;
import java.util.List;

@Entity
public class Produit {
    @Id
    public Long idProduit;
    public String designation;
    public boolean estPerissable;
    public Double prixU;

    @ManyToOne
    public CatProduit catProduit;
    

    public  static Finder<Long, Produit> find = new Finder<>(Produit.class);

    public Produit() {
    }

    public Produit(String designation, boolean estPerissable, Double prixU, CatProduit catProduit) {
        this.designation = designation;
        this.estPerissable = estPerissable;
        this.prixU = prixU;
        this.catProduit = catProduit;
    }
}