package models;

import play.data.format.Formats;

import javax.persistence.*;
import io.ebean.Model;
import io.ebean.Finder;
import java.util.Date;

@Entity
public class Stock {
    @Id
    public Long idStock;
    public Integer qteStock;
    public Date datePeremption;
    public Boolean estValide = Boolean.TRUE;

    @ManyToOne
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