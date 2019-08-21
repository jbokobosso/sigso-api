package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Sortie {
    @Id
    public Long idSortie;
    public String raisonSortie;
    public Integer qteSortie;
    public Date dateSortie;

    @ManyToOne
    public Produit produit;

    public  static Finder<Long, Sortie> find = new Finder<>(Sortie.class);

    public Sortie() {
    }

    public Sortie(String raisonSortie, Integer qteSortie, Date dateSortie, Produit produit) {
        this.raisonSortie = raisonSortie;
        this.qteSortie = qteSortie;
        this.dateSortie = dateSortie;
        this.produit = produit;
    }
}