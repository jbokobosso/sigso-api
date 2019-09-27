package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Entree extends Model {
    @Id
    public Long idEntree;
    public String raisonEntree;
    public Integer qteEntree;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date dateEntree;

    @ManyToOne(optional=false)
    @JoinColumn(name = "id_produit")
    public Produit produit;

    public  static Finder<Long, Entree> find = new Finder<>(Entree.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;
}
