package models;

import io.ebean.Model;

import javax.persistence.*;

@Entity
public class panierCommande extends Model {

    @EmbeddedId
    panierCommandeKey id;

    @ManyToOne(optional = false)
    @MapsId("id_cmde")
    @JoinColumn(name = "id_cmde")
    public Commande commande;

    @ManyToOne(optional = false)
    @MapsId("id_produit")
    @JoinColumn(name = "id_produit")
    public Produit produit;

    public int qteProduit;

    public panierCommande() {
    }

    public panierCommande(panierCommandeKey id, Commande commande, Produit produit, int qteProduit) {
        this.id = id;
        this.commande = commande;
        this.produit = produit;
        this.qteProduit = qteProduit;
    }
}
