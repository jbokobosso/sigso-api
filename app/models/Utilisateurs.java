package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Utilisateurs extends Model {

    @Id
    public Long idUtilisateur;

    public String nom, prenom, tel, email, pseudo, mdp, typeUtilisateur;

    @OneToMany(mappedBy = "utilisateurs", cascade = CascadeType.ALL)
    List<Achat> achat;

    public  static Finder<Long, Utilisateurs> find = new Finder<>(Utilisateurs.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;
}