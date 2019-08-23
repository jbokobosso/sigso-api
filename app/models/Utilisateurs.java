package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Utilisateurs extends Model {

    @Id
    public Long idUtilisateur;

    public String nom, prenom, tel, email, pseudo, mdp, typeUtilisateur;

    @OneToMany(mappedBy = "utilisateurs", cascade = CascadeType.ALL)
    List<Achat> achat;

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