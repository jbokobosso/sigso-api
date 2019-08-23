package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Entity
public class Commande extends Model {
    @Id
    public Long idCmde;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
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