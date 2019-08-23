package models;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date dateVente;

    public Double prixVente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produit")
    public Produit produit;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_client")
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