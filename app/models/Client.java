package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client extends Model {
    @Id
    public Long idClient;

    public String nom, prenom, email, adresseLivraison, telephone;

    // public static final ClientFinder find = new ClientFinder();
    public  static Finder<Long, Client> find = new Finder<>(Client.class);

    public Client(String nom, String prenom, String email, String adresseLivraison, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adresseLivraison = adresseLivraison;
        this.telephone = telephone;
    }

}