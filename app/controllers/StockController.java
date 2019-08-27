package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Stock;
import models.Client;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class StockController extends Controller {

    public Result getAll() {
        Object liste = Stock.find.query().select("*").findList();
        return ok(Json.toJson(liste));
    }



    public Result save(Http.Request request) throws JsonProcessingException {
        JsonNode json_node_stock = request.body().asJson();
        Stock stock = new ObjectMapper().treeToValue(json_node_stock, Stock.class);
        Client.db().save(stock);
        return ok(Json.toJson(stock));
    }



    public Result getById(Long stockId) {
        Stock stock = Stock.find.query().select("*").where().eq("id_stock", stockId).findOne();

        // This line below is to test if the client's id is null; if then, surely the request did not find any entry in database for the passed id
        Object stockTest = Stock.find.query().select("*").where().eq("id_stock", stockId).findOne();

        if(stockTest != null) {
            return ok(Json.toJson(stock));
        } else {
            return badRequest("Stock of id: " + stockId+ " not found");
        }

    }



    public Result update(Http.Request request, Long stockId) throws JsonProcessingException {
        JsonNode stock_to_be_modified_temp = request.body().asJson();
        Stock stock_to_be_modified = new ObjectMapper().treeToValue(stock_to_be_modified_temp, Stock.class);

        List<Stock> all_stock = Stock.find.query().select("*").findList();
        AtomicInteger test = new AtomicInteger(117);
        all_stock.forEach(database_stock -> {
            if(database_stock.idStock == stockId) {
                if (stock_to_be_modified.idStock == stockId ) {
                    Stock.db().update(stock_to_be_modified);
                    test.set(118);
                } else {
                    test.set(119);
                }
            }
        });
        if (test.get() == 117)
            return badRequest("The given stock was not found");
        else if (test.get() == 119)
            return badRequest("The given stock's id and the stock to be updated's id do not match");
        else
            return ok(Json.toJson(Json.toJson(stock_to_be_modified)));
    }
    
}
