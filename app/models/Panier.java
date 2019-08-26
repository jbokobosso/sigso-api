package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Panier extends Model {

    @Id
    public Long idPanier;

    @OneToOne(mappedBy = "panier")
    public List<Commande> commande;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    public List<ContenuPanier> contenuPanier;

    public  static Finder<Long, Panier> find = new Finder<>(Panier.class);

    public Long getIdPanier() {
        return idPanier;
    }

    public Panier(Long idPanier) {
        this.idPanier = idPanier;
    }

    public Panier(List<Commande> commande, List<ContenuPanier> contenuPanier) {
        this.commande = commande;
        this.contenuPanier = contenuPanier;
    }
}
