package org.KennethSun;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class BankManageSystem {

    public void GetBalance(RoutingContext routingContext) {
        final HttpServerRequest request = routingContext.request();

        Buffer responseBuffer = Buffer.buffer("10000");
        int size = responseBuffer.length();

        request.bodyHandler(buffer -> {
            HttpServerResponse response = request.response();
            response.setStatusCode(200);
            response.headers()
                    .add("content-type", HttpHeaders.createOptimized(String.valueOf(size)))
                    .add("Access-Control-Allow-Origin", "*");
            response.end(responseBuffer);
        });

    }

    public void MakeDeposit(RoutingContext routingContext) {

        final HttpServerRequest request = routingContext.request();
        //Buffer responseBuffer = Buffer.buffer(request.body().toString());
        //int size = responseBuffer.length();

        request.bodyHandler(buffer -> {
            JsonObject jsonObject = buffer.toJsonObject();
            String modifiedBody = jsonObject.getString("body") + "Modified";
            String modifiedTile = jsonObject.getString("title") + "Modified";
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
            objectNode.put("title", modifiedTile);
            objectNode.put("body", modifiedBody);

            Buffer responseBuffer = Buffer.buffer(objectNode.toString());
            int size = responseBuffer.length();
            HttpServerResponse response = request.response();
            response.setStatusCode(200);
            response.headers()
                    .add("content-type", HttpHeaders.createOptimized(String.valueOf(size)))
                    .add("Access-Control-Allow-Origin", "*");
            response.end(responseBuffer);
        });
    }

    public void MakeDepositOptions(RoutingContext routingContext) {

        final HttpServerRequest request = routingContext.request();
        HttpServerResponse response = request.response();
        response.setStatusCode(200);
        response.headers()
                //.add("content-type", HttpHeaders.createOptimized(String.valueOf(size)))
                .add("Access-Control-Allow-Origin", "*");
        response.end();
    }

    public void WithDraw(RoutingContext routingContext) {

    }
}
