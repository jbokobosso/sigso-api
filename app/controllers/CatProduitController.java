package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.CatProduit;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CatProduitController extends Controller {

    public Result getAll() {
        Object liste = CatProduit.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result getById(Long catProduitId) {
        CatProduit catProduit = CatProduit.find.query().select("*").where().eq("id_cat_prod", catProduitId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object catProduitTest = CatProduit.find.query().select("*").where().eq("id_cat_prod", catProduitId).findOne();

        if(catProduitTest != null) {
            return ok(Json.toJson(catProduit));
        } else {
            return badRequest("Product category of id : " + catProduitId + " does not exist");
        }

    }





    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_cat_produit = request.body().asJson();
        CatProduit catProduit = new ObjectMapper().treeToValue(json_node_cat_produit, CatProduit.class);
        CatProduit.db().save(catProduit);
        return ok(Json.toJson(catProduit));
    }




    public Result update(Http.Request request, Long idAchatcatProduitId) throws JsonProcessingException {
        JsonNode cat_to_be_modified_temp = request.body().asJson();
        CatProduit cat_to_be_modified = new ObjectMapper().treeToValue(cat_to_be_modified_temp, CatProduit.class);

        List<CatProduit> all_achat = CatProduit.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_achat.forEach(database_achat -> {
            if(database_achat.idCatProd == idAchatcatProduitId) {
                if (cat_to_be_modified.idCatProd == idAchatcatProduitId ) {
                    CatProduit.db().update(cat_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given product category was not found");
        else if (test.get() == 119)
            return badRequest("The given category's id and the category to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(cat_to_be_modified)));
    }
}
