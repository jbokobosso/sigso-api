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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;

    public  static Finder<Long, Achat> find = new Finder<>(Achat.class);

    
}