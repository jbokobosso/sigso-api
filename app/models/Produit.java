package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Produit extends Model {

    @Id
    public Long idProduit;

    public String designation;
    public boolean estPerissable;
    public Double prixU;

    @ManyToOne(optional=false)
    @JoinColumn(name = "id_catProduit")
    public CatProduit catProduit;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Sortie> sortie;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Vente> vente;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Achat> achat;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    List<Panier> panier;
    

    public  static Finder<Long, Produit> find = new Finder<>(Produit.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date createdAt;
}