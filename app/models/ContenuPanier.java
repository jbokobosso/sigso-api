package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;

@Entity
public class ContenuPanier extends Model {

    @Id
    public Long idContenuPanier;

    public Long qteProduit;
    public double prixVente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produit")
    public Produit produit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_panier")
    public Panier panier;


    public  static Finder<Long, ContenuPanier> find = new Finder<>(ContenuPanier.class);

    public ContenuPanier(Long idContenuPanier) {
        this.idContenuPanier = idContenuPanier;
    }

    public ContenuPanier(Long qteProduit, double prixVente, Produit produit, Panier panier) {
        this.qteProduit = qteProduit;
        this.prixVente = prixVente;
        this.produit = produit;
        this.panier = panier;
    }
}
