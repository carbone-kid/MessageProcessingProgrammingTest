package com.sfirsov.processing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.HttpApp;
import akka.http.javadsl.server.Route;
import com.sfirsov.processing.actors.ReportGenerator;
import com.sfirsov.processing.actors.MessageSystem;
import com.sfirsov.processing.model.AdjustPrice;
import com.sfirsov.processing.model.MultipleSale;
import com.sfirsov.processing.model.SingleSale;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Application extends HttpApp {
    static ActorSystem system;
    final ActorRef salesActor;

    public static void main(String[] args) {
        try {
            system = ActorSystem.create("SalesProcessing");
            final Application server = new Application(system);

            server.startServer("localhost", 8080);

            System.in.read();

        } catch (IOException ioe) {
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            system.terminate();
        }
    }

    Application(ActorSystem system) {
        ActorRef reportActor = system.actorOf(ReportGenerator.props(), "reportActor");
        salesActor = system.actorOf(MessageSystem.props(50, 10, reportActor), "saleActor");
    }

    @Override
    protected Route routes() {
        return route(
                // Listens to the top `/`
                pathEndOrSingleSlash(() ->
                        complete("Server up and running") // To test that the app is running
                ),
                // Listens to PUT request `/single-sale`, expects json SingleSale object
                path("single-sale", () ->
                        put(() ->
                                entity(Jackson.unmarshaller(SingleSale.class), sale -> {
                                    salesActor.tell(sale, ActorRef.noSender());
                                    return complete(sale.toString());
                                })
                        )
                ),
                // Listens to PUT request `/bunch-sale`, expects json MultipleSale object
                path("multiple-sale", () ->
                        put(() ->
                                entity(Jackson.unmarshaller(MultipleSale.class), sale -> {
                                    salesActor.tell(sale, ActorRef.noSender());
                                    return complete(sale.toString());
                                })
                        )
                ),
                // Listens to PUT request `/adjust-price`, expects json AdjustPrice object
                path("adjust-price", () ->
                        put(() ->
                                entity(Jackson.unmarshaller(AdjustPrice.class), adjustPrice -> {
                                    salesActor.tell(adjustPrice, ActorRef.noSender());
                                    return complete(adjustPrice.toString());
                                })
                        )
                ),
                // Listen to GET request `/report`
                path("report", () ->
                        get(() -> {
                            salesActor.tell("report", ActorRef.noSender());
                            return complete("report made");
                        })
                )
        );
    }
}
