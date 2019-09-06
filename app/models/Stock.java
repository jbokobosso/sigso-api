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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;
}