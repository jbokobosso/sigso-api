package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class CatProduit extends Model {
    
    @Id
    public Long idCatProd;

    public String libelleCat;
    public String descCatProd;

    public  static Finder<Long, CatProduit> find = new Finder<>(CatProduit.class);

    public CatProduit() {
    }

    public CatProduit(String libelleCat, String descCatProd) {
        this.libelleCat = libelleCat;
        this.descCatProd = descCatProd;
    }
}