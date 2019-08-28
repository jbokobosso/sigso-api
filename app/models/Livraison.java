package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Livraison extends Model {

    @Id
    public Long idLivraison;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date dateLivraison;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_commande")
    public Commande commande;

    public  static Finder<Long, Livraison> find = new Finder<>(Livraison.class);

    public Livraison() {
    }

    public Livraison(Date dateLivraison, Commande commande) {
        this.dateLivraison = dateLivraison;
        this.commande = commande;
    }
}