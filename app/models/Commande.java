package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;
import models.*;
import java.util.List;

@Entity
public class Commande {
    @Id
    public Long idCmde;
    public Date dateCmde;

    @ManyToOne
    public Client client;

    @ManyToMany
    public List<Produit> produit;

    public  static Finder<Long, Commande> find = new Finder<>(Commande.class);

    public Commande() {
    }

    public Commande(Date dateCmde, Client client, List<Produit> produit) {
        this.dateCmde = dateCmde;
        this.client = client;
        this.produit = produit;
    }
}