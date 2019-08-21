package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Livraison {
    @Id
    public Long idLivraison;
    public Date dateLivraison;

    @ManyToOne
    public Commande commande;

    public  static Finder<Long, Livraison> find = new Finder<>(Livraison.class);

    public Livraison() {
    }

    public Livraison(Date dateLivraison, Commande commande) {
        this.dateLivraison = dateLivraison;
        this.commande = commande;
    }
}