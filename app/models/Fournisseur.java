package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Fournisseur extends Model {
    @Id
    public Long idFournisseur;

    public String raisonSociale, tel, adresseFournisseur;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    List<Achat> achat;

    public  static Finder<Long, Fournisseur> find = new Finder<>(Fournisseur.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;
}