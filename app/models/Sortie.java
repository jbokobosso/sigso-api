package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Sortie extends Model {
    @Id
    public Long idSortie;
    public String raisonSortie;
    public Integer qteSortie;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date dateSortie;

    @ManyToOne(optional=false)
    @JoinColumn(name = "id_produit")
    public Produit produit;

    public  static Finder<Long, Sortie> find = new Finder<>(Sortie.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;
}