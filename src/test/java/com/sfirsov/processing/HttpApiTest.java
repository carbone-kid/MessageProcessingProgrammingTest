package com.sfirsov.processing;

import akka.actor.ActorSystem;
import akka.http.javadsl.model.ContentTypes;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.testkit.JUnitRouteTest;
import akka.http.javadsl.testkit.TestRoute;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfirsov.processing.model.AdjustPrice;
import com.sfirsov.processing.model.MultipleSale;
import com.sfirsov.processing.model.Message;
import com.sfirsov.processing.model.SingleSale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class HttpApiTest extends JUnitRouteTest {
    private TestRoute appRoute;
    private Application server;
    private ActorSystem system;

    @Before
    public void initClass() {
        system = ActorSystem.create("SalesProcessingTest");
        server = new Application(system);
        appRoute = testRoute(server.routes());
    }

    @After
    public void freeResources() {
        system.terminate();
    }

    @Test
    public void testGetTopSlash() {
        appRoute.run(HttpRequest.GET("/"))
                .assertStatusCode(StatusCodes.OK)
                .assertEntity("Server up and running");
    }

    @Test
    public void testSaleMessages() throws JsonProcessingException {
        // Generating random messages with random values and feeding to the REST API
        ObjectMapper mapper = new ObjectMapper();

        String[] productTypes = {"apples", "oranges", "carrots", "melons", "potatoes"};
        AdjustPrice.Operation[] operations = {AdjustPrice.Operation.Add, AdjustPrice.Operation.Subtract, AdjustPrice.Operation.Multiply};

        for(int i=0; i<60; i++) {
            double cost = ThreadLocalRandom.current().nextDouble(100, 1000);
            int messageTypeIndex = ThreadLocalRandom.current().nextInt(0, 3);
            int itemsCountInOneSale = ThreadLocalRandom.current().nextInt(10, 100);
            int productTypeIndex = ThreadLocalRandom.current().nextInt(0, 5);
            int operationIndex = ThreadLocalRandom.current().nextInt(0, 3);
            double adjustValue = ThreadLocalRandom.current().nextDouble(1, 5);

            Message message = null;
            String route = "";

            switch(messageTypeIndex) {
                case 0: message = new SingleSale(productTypes[productTypeIndex], cost);
                    route = "/single-sale";
                    break;
                case 1: message = new MultipleSale(productTypes[productTypeIndex], cost, itemsCountInOneSale);
                    route = "/multiple-sale";
                    break;
                case 2: message = new AdjustPrice(productTypes[productTypeIndex], operations[operationIndex], adjustValue);
                    route = "/adjust-price";
                    break;
                default: break;
            }

            String saleJson = mapper.writeValueAsString(message);
            appRoute.run(HttpRequest.PUT(route).withEntity(ContentTypes.APPLICATION_JSON, saleJson))
                    .assertStatusCode(StatusCodes.OK)
                    .assertEntity(message.toString());
        }

        appRoute.run(HttpRequest.GET("/report"))
                .assertStatusCode(StatusCodes.OK)
                .assertEntity("report made");
    }
}
