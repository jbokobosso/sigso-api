package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.ContenuPanier;
import models.Client;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class ContenuPanierController extends Controller {

    public Result getAll() {
        Object liste = ContenuPanier.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_contenuPanier = request.body().asJson();
        ContenuPanier contenuPanier = new ObjectMapper().treeToValue(json_node_contenuPanier, ContenuPanier.class);
        Client.db().save(contenuPanier);
        return ok(Json.toJson(contenuPanier));
    }



    public Result getById(Long contenuPanierId) {
        ContenuPanier contenuPanier = ContenuPanier.find.query().select("*").where().eq("id_contenuPanier", contenuPanierId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object contenuPanierTest = ContenuPanier.find.query().select("*").where().eq("id_contenuPanier", contenuPanierId).findOne();

        if(contenuPanierTest != null) {
            return ok(Json.toJson(contenuPanier));
        } else {
            return badRequest("ContenuPanier of id: " + contenuPanierId+ " not found");
        }

    }



    public Result update(Http.Request request, Long contenuPanierId) throws JsonProcessingException {
        JsonNode contenuPanier_to_be_modified_temp = request.body().asJson();
        ContenuPanier contenuPanier_to_be_modified = new ObjectMapper().treeToValue(contenuPanier_to_be_modified_temp, ContenuPanier.class);

        List<ContenuPanier> all_contenuPanier = ContenuPanier.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_contenuPanier.forEach(database_contenuPanier -> {
            if(database_contenuPanier.idContenuPanier == contenuPanierId) {
                if (contenuPanier_to_be_modified.idContenuPanier == contenuPanierId ) {
                    ContenuPanier.db().update(contenuPanier_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given contenuPanier was not found");
        else if (test.get() == 119)
            return badRequest("The given contenuPanier's id and the contenuPanier to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(contenuPanier_to_be_modified)));
    }

}
