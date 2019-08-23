package models;

import io.ebean.Finder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Fournisseur {
    @Id
    public Long idFournisseur;

    public String raisonSociale, tel, adresseFournisseur;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    List<Achat> achat;

    public  static Finder<Long, Fournisseur> find = new Finder<>(Fournisseur.class);

    public Fournisseur() {
    }

    public Fournisseur(String raisonSociale, String tel, String adresseFournisseur) {
        this.raisonSociale = raisonSociale;
        this.tel = tel;
        this.adresseFournisseur = adresseFournisseur;
    }
}