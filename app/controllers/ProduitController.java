package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Produit;
import models.Client;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class ProduitController extends Controller {

    public Result getAll() {
        Object liste = Produit.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_produit = request.body().asJson();
        Produit produit = new ObjectMapper().treeToValue(json_node_produit, Produit.class);
        Client.db().save(produit);
        return ok(Json.toJson(produit));
    }



    public Result getById(Long produitId) {
        Produit produit = Produit.find.query().select("*").where().eq("id_produit", produitId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object produitTest = Produit.find.query().select("*").where().eq("id_produit", produitId).findOne();

        if(produitTest != null) {
            return ok(Json.toJson(produit));
        } else {
            return badRequest("Produit of id: " + produitId+ " not found");
        }

    }



    public Result update(Http.Request request, Long produitId) throws JsonProcessingException {
        JsonNode produit_to_be_modified_temp = request.body().asJson();
        Produit produit_to_be_modified = new ObjectMapper().treeToValue(produit_to_be_modified_temp, Produit.class);

        List<Produit> all_produit = Produit.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_produit.forEach(database_produit -> {
            if(database_produit.idProduit == produitId) {
                if (produit_to_be_modified.idProduit == produitId ) {
                    Produit.db().update(produit_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given produit was not found");
        else if (test.get() == 119)
            return badRequest("The given produit's id and the produit to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(produit_to_be_modified)));
    }
    
}
