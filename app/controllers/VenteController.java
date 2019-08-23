package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Vente;
import models.Client;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class VenteController {

    public Result getAll() {
        Object liste = Vente.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_vente = request.body().asJson();
        Vente vente = new ObjectMapper().treeToValue(json_node_vente, Vente.class);
        Client.db().save(vente);
        return ok(Json.toJson(vente));
    }



    public Result getById(Long venteId) {
        Vente vente = Vente.find.query().select("*").where().eq("id_vente", venteId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object venteTest = Vente.find.query().select("*").where().eq("id_vente", venteId).findOne();

        if(venteTest != null) {
            return ok(Json.toJson(vente));
        } else {
            return badRequest("Vente of id: " + venteId+ " not found");
        }

    }



    public Result update(Http.Request request, Long venteId) throws JsonProcessingException {
        JsonNode vente_to_be_modified_temp = request.body().asJson();
        Vente vente_to_be_modified = new ObjectMapper().treeToValue(vente_to_be_modified_temp, Vente.class);

        List<Vente> all_vente = Vente.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_vente.forEach(database_vente -> {
            if(database_vente.idVente == venteId) {
                if (vente_to_be_modified.idVente == venteId ) {
                    Vente.db().update(vente_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given vente was not found");
        else if (test.get() == 119)
            return badRequest("The given vente's id and the vente to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(vente_to_be_modified)));
    }
}
