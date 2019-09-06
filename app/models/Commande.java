package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Commande extends Model {
    @Id
    public Long idCmde;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    public Date dateCmde;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_client")
    public Client client;

    public  static Finder<Long, Commande> find = new Finder<>(Commande.class);

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(nullable = true)
    public Date deletedAt;

}