package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class CatProduit extends Model {
    
    @Id
    public Long idCatProd;

    public String libelleCat;

    public String descCatProd;

    @OneToMany(mappedBy="catProduit", cascade=CascadeType.ALL)
    List<Produit> produit;

    public  static Finder<Long, CatProduit> find = new Finder<>(CatProduit.class);

    public CatProduit() {
    }

    public CatProduit(String libelleCat, String descCatProd) {
        this.libelleCat = libelleCat;
        this.descCatProd = descCatProd;
    }
}