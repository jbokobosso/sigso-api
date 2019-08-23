package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Livraison;
import models.Client;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class LivraisonController {

    public Result getAll() {
        Object liste = Livraison.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_livraison = request.body().asJson();
        Livraison livraison = new ObjectMapper().treeToValue(json_node_livraison, Livraison.class);
        Client.db().save(livraison);
        return ok(Json.toJson(livraison));
    }



    public Result getById(Long livraisonId) {
        Livraison livraison = Livraison.find.query().select("*").where().eq("id_livraison", livraisonId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object livraisonTest = Livraison.find.query().select("*").where().eq("id_livraison", livraisonId).findOne();

        if(livraisonTest != null) {
            return ok(Json.toJson(livraison));
        } else {
            return badRequest("Livraison of id: " + livraisonId+ " not found");
        }

    }



    public Result update(Http.Request request, Long livraisonId) throws JsonProcessingException {
        JsonNode livraison_to_be_modified_temp = request.body().asJson();
        Livraison livraison_to_be_modified = new ObjectMapper().treeToValue(livraison_to_be_modified_temp, Livraison.class);

        List<Livraison> all_livraison = Livraison.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_livraison.forEach(database_livraison -> {
            if(database_livraison.idLivraison == livraisonId) {
                if (livraison_to_be_modified.idLivraison == livraisonId ) {
                    Livraison.db().update(livraison_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given livraison was not found");
        else if (test.get() == 119)
            return badRequest("The given livraison's id and the livraison to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(livraison_to_be_modified)));
    }
}
