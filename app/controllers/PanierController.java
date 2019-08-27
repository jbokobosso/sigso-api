package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Panier;
import models.Client;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class PanierController extends Controller {

    public Result getAll() {
        Object liste = Panier.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_panier = request.body().asJson();
        Panier panier = new ObjectMapper().treeToValue(json_node_panier, Panier.class);
        Client.db().save(panier);
        return ok(Json.toJson(panier));
    }



    public Result getById(Long panierId) {
        Panier panier = Panier.find.query().select("*").where().eq("id_panier", panierId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object panierTest = Panier.find.query().select("*").where().eq("id_panier", panierId).findOne();

        if(panierTest != null) {
            return ok(Json.toJson(panier));
        } else {
            return badRequest("Panier of id: " + panierId+ " not found");
        }

    }



    public Result update(Http.Request request, Long panierId) throws JsonProcessingException {
        JsonNode panier_to_be_modified_temp = request.body().asJson();
        Panier panier_to_be_modified = new ObjectMapper().treeToValue(panier_to_be_modified_temp, Panier.class);

        List<Panier> all_panier = Panier.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_panier.forEach(database_panier -> {
            if(database_panier.idPanier == panierId) {
                if (panier_to_be_modified.idPanier == panierId ) {
                    Panier.db().update(panier_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given panier was not found");
        else if (test.get() == 119)
            return badRequest("The given panier's id and the panier to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(panier_to_be_modified)));
    }

}
