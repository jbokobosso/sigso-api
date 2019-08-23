package models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class panierCommandeKey implements Serializable {

    @Column(name = "id_cmde")
    public Long idCmde;

    @Column(name = "id_produit")
    public Long idProduit;

    public panierCommandeKey() {
    }

    public panierCommandeKey(Long idCmde, Long idProduit) {
        this.idCmde = idCmde;
        this.idProduit = idProduit;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
