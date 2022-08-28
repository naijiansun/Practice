package org.KennethSun;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;

public class ServiceVerticle extends AbstractVerticle{

    private HttpServer server;
    private BankManageSystem bankManageSystem;

    public ServiceVerticle(BankManageSystem bankManageSystem) {
        this.bankManageSystem = bankManageSystem;
    }

    @Override
    public void start(Promise<Void> startPromise) {
        Router router = Router.router(this.vertx);

        router.route().handler(CorsHandler.create("http://localhost:10000")
                .allowedMethod(io.vertx.core.http.HttpMethod.GET)
                .allowedMethod(io.vertx.core.http.HttpMethod.POST)
                .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
                .allowCredentials(true)
                .allowedHeader("Access-Control-Allow-Headers")
                .allowedHeader("Authorization")
                .allowedHeader("Access-Control-Allow-Method")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowedHeader("Content-Type"));

        router.route(HttpMethod.GET, "/GetBalance")
                .handler(bankManageSystem::GetBalance);

        router.route(HttpMethod.POST, "/MakeDeposit")
                .handler(bankManageSystem::MakeDeposit);

        router.route(HttpMethod.POST, "/WithDraw")
                .handler(bankManageSystem::WithDraw);
/*
        router.route(HttpMethod.OPTIONS, "/MakeDeposit")
                .handler(bankManageSystem::MakeDepositOptions);
*/


        int port = 10000;
        final HttpServerOptions options = new HttpServerOptions();

        this.server = this.vertx.createHttpServer(options)
                .requestHandler(router)
                .listen(port, res -> {
                    if(res.succeeded()) {

                    } else {

                    }
                });
    }
}
