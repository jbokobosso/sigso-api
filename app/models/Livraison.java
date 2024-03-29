package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Livraison extends Model {

    @Id
    public Long idLivraison;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date dateLivraison;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_commande")
    public Commande commande;

    @Enumerated(EnumType.STRING)
    public EtatLivraison etatLivraison;

    public  static Finder<Long, Livraison> find = new Finder<>(Livraison.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;
}