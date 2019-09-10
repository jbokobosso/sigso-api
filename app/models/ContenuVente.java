package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ContenuVente extends Model {

    @Id
    public Long idContenuVente;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_vente")
    public Vente vente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produit")
    public Produit produit;

    public Long qteProduit;

    public  static Finder<Long, ContenuVente> find = new Finder<>(ContenuVente.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;
}
