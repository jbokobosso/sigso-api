package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Fournisseur;
import models.Client;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class FournisseurController {

    public Result getAll() {
        Object liste = Fournisseur.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_fournisseur = request.body().asJson();
        Fournisseur fournisseur = new ObjectMapper().treeToValue(json_node_fournisseur, Fournisseur.class);
        Client.db().save(fournisseur);
        return ok(Json.toJson(fournisseur));
    }



    public Result getById(Long fournisseurId) {
        Fournisseur fournisseur = Fournisseur.find.query().select("*").where().eq("id_fournisseur", fournisseurId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object fournisseurTest = Fournisseur.find.query().select("*").where().eq("id_fournisseur", fournisseurId).findOne();

        if(fournisseurTest != null) {
            return ok(Json.toJson(fournisseur));
        } else {
            return badRequest("Fournisseur of id: " + fournisseurId+ " not found");
        }

    }



    public Result update(Http.Request request, Long fournisseurId) throws JsonProcessingException {
        JsonNode fournisseur_to_be_modified_temp = request.body().asJson();
        Fournisseur fournisseur_to_be_modified = new ObjectMapper().treeToValue(fournisseur_to_be_modified_temp, Fournisseur.class);

        List<Fournisseur> all_fournisseur = Fournisseur.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_fournisseur.forEach(database_fournisseur -> {
            if(database_fournisseur.idFournisseur == fournisseurId) {
                if (fournisseur_to_be_modified.idFournisseur == fournisseurId ) {
                    Fournisseur.db().update(fournisseur_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given fournisseur was not found");
        else if (test.get() == 119)
            return badRequest("The given fournisseur's id and the fournisseur to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(fournisseur_to_be_modified)));
    }
    
}
