package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false, nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date createdAt;
}