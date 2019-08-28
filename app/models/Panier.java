package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Panier extends Model {

    @Id
    public Long idPanier;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_cmde")
    public Commande commande;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produit")
    public Produit produit;

    public Long qteProduit;

    public  static Finder<Long, Panier> find = new Finder<>(Panier.class);
}
