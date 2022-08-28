package org.KennethSun;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class App
{
    public static void main( String[] args ) {

        BankManageSystem bankManageSystem = new BankManageSystem();

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(() -> new ServiceVerticle(bankManageSystem),
                new DeploymentOptions().setWorker(true).setInstances(1),
                event -> {

                });
    }
}
