package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Utilisateurs extends Model {
    @Id
    public Long idUtilisateur;
    public String nom, prenom, tel, email, pseudo, mdp, typeUtilisateur;

    public  static Finder<Long, Utilisateurs> find = new Finder<>(Utilisateurs.class);

    public Utilisateurs() {
    }

    public Utilisateurs(String nom, String prenom, String tel, String email, String pseudo, String mdp,
            String typeUtilisateur) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.typeUtilisateur = typeUtilisateur;
    }
}