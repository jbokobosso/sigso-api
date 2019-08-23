package models;

import io.ebean.Finder;

import javax.persistence.*;
import java.util.List;

@Entity
public class Produit {

    @Id
    public Long idProduit;

    public String designation;
    public boolean estPerissable;
    public Double prixU;

    @ManyToOne(optional=false)
    @JoinColumn(name = "id_catProduit")
    public CatProduit catProduit;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Sortie> sortie;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Vente> vente;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Achat> achat;
    

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