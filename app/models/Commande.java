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

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    List<Livraison> livraison;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_client")
    public Client client;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_panier")
    public Panier panier;

    public  static Finder<Long, Commande> find = new Finder<>(Commande.class);

    public Commande() {
    }

    public Commande(Date dateCmde, Client client) {
        this.dateCmde = dateCmde;
        this.client = client;
    }

    public Commande(Long idCmde) {
        this.idCmde = idCmde;
    }

    public Commande(Date dateCmde, List<Livraison> livraison, Client client, Panier panier) {
        this.dateCmde = dateCmde;
        this.livraison = livraison;
        this.client = client;
        this.panier = panier;
    }
}