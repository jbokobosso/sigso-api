package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Vente extends Model {
    
    @Id
    public Long idVente;

    public Integer qteProduit;
    public Date dateVente;
    public Double prixVente;

    @ManyToOne
    public Produit produit;

    @ManyToOne
    public Client client;

    public static Finder<Long, Vente> find = new Finder<>(Vente.class);

    public Vente() {

    }

    public Vente(Integer qteProduit, Date dateVente, Double prixVente, Produit produit, Client client) {
        this.qteProduit = qteProduit;
        this.dateVente = dateVente;
        this.prixVente = prixVente;
        this.produit = produit;
        this.client = client;
    }

}