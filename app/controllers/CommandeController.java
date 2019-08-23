package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Commande;
import models.Commande;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CommandeController {

    public Result getAll() {
        Object liste = Commande.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result getById(Long catProduitId) {
        Commande commande = Commande.find.query().select("*").where().eq("id_cmde", catProduitId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object commandeTest = Commande.find.query().select("*").where().eq("id_cmde", catProduitId).findOne();

        if(commandeTest != null) {
            return ok(Json.toJson(commande));
        } else {
            return badRequest("Product category of id : " + catProduitId + " does not exist");
        }

    }





    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_commande = request.body().asJson();
        Commande commande = new ObjectMapper().treeToValue(json_node_commande, Commande.class);
        Commande.db().save(commande);
        return ok(Json.toJson(commande));
    }




    public Result update(Http.Request request, Long idAchatcatProduitId) throws JsonProcessingException {
        JsonNode cat_to_be_modified_temp = request.body().asJson();
        Commande cat_to_be_modified = new ObjectMapper().treeToValue(cat_to_be_modified_temp, Commande.class);

        List<Commande> all_achat = Commande.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_achat.forEach(database_achat -> {
            if(database_achat.idCmde == idAchatcatProduitId) {
                if (cat_to_be_modified.idCmde == idAchatcatProduitId ) {
                    Commande.db().update(cat_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The command with id: " + idAchatcatProduitId + " does not exist");
        else if (test.get() == 119)
            return badRequest("The givend comand id and the command to be modified id do not match");
        else
            return ok(Json.toJson(Json.toJson(cat_to_be_modified)));
    }
}
