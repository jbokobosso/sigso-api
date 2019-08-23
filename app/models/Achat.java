package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Achat extends Model {
    @Id
    public Long idAchat;
    public Integer qteAchat;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date dateAchat;

    @OneToMany(mappedBy = "achat", cascade = CascadeType.ALL)
    List<Stock> stock;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_fournisseur")
    public Fournisseur fournisseur;
    
    @ManyToOne(optional = false)
    @JoinColumn(name="id_utilisateurs")
    public Utilisateurs utilisateurs;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produit")
    public Produit produit;

    public  static Finder<Long, Achat> find = new Finder<>(Achat.class);

    public Achat() {
    }

    public Achat(Long idAchat, Integer qteAchat, Date dateAchat, Fournisseur fournisseur, Utilisateurs utilisateurs, Produit produit) {
        this.idAchat = idAchat;
        this.qteAchat = qteAchat;
        this.dateAchat = dateAchat;
        this.fournisseur = fournisseur;
        this.utilisateurs = utilisateurs;
        this.produit = produit;
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