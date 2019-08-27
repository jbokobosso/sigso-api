package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Client;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ClientController  extends Controller {

    public Result getClientsAll() {
        Object liste = Client.find.query().select("*").findList();
        return ok(Json.toJson(liste));

        //return ok(liste.toString());
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_client = request.body().asJson();
        Client client = new ObjectMapper().treeToValue(json_node_client, Client.class);
        Client.db().save(client);
        return ok(Json.toJson(client));
    }



    public Result getClientById(Long clientId) {
        Client client = Client.find.query().select("*").where().eq("id_client", clientId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object clientTest = Client.find.query().select("*").where().eq("id_client", clientId).findOne();

        if(clientTest != null) {
            return ok(Json.toJson(client));
        } else {
            return badRequest("Client of id: " + clientId+ " not found");
        }

    }



    public Result updateClient(Http.Request request, Long clientId) throws JsonProcessingException {
        JsonNode client_to_be_modified_temp = request.body().asJson();
        Client client_to_be_modified = new ObjectMapper().treeToValue(client_to_be_modified_temp, Client.class);

        List<Client> all_clients = Client.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_clients.forEach(database_client -> {
            if(database_client.idClient == clientId) {
                if (client_to_be_modified.idClient == clientId ) {
                    Client.db().update(client_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given client was not found");
        else if (test.get() == 119)
            return badRequest("The given client's id and the client to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(client_to_be_modified)));
    }
}