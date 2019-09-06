package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends Model {
    @Id
    public Long idClient;

    public String nom, prenom, email, adresseLivraison, telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    List<Vente> vente;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    List<Commande> commande;


    public  static Finder<Long, Client> find = new Finder<>(Client.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;

}