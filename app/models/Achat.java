package models;

import play.data.format.Formats;
import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Achat {
    @Id
    public Long idAchat;
    public Integer qteAchat;
    public Date dateAchat;
    
    @ManyToOne
    public Fournisseur fournisseur;
    
    @ManyToOne
    public Utilisateurs utilisateurs;
    
    @ManyToOne
    public Produit produit;

    public  static Finder<Long, Achat> find = new Finder<>(Achat.class);

    public Achat() {
    }

    public Achat(Integer qteAchat, Date dateAchat, Fournisseur fournisseur, Utilisateurs utilisateurs,
            Produit produit) {
        this.qteAchat = qteAchat;
        this.dateAchat = dateAchat;
        this.fournisseur = fournisseur;
        this.utilisateurs = utilisateurs;
        this.produit = produit;
    }

    
}