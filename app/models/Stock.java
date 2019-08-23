package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Stock extends Model {
    @Id
    public Long idStock;
    public Integer qteStock;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date datePeremption;

    public Boolean estValide = Boolean.TRUE;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_achat")
    public Achat achat;

    public  static Finder<Long, Stock> find = new Finder<>(Stock.class);

    public Stock() {
    }

    public Stock(Integer qteStock, Date datePeremption, Boolean estValide, Achat achat) {
        this.qteStock = qteStock;
        this.datePeremption = datePeremption;
        this.estValide = estValide;
        this.achat = achat;
    }
}