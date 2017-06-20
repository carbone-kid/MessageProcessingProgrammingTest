package com.sfirsov.processing.actors;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import com.sfirsov.processing.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReportGenerator extends AbstractLoggingActor {
    static public Props props() {
        return Props.create(ReportGenerator.class, () -> new ReportGenerator());
    }

    static class ProductsReport {
        List<Message> messages;
        ProductsReport(List<Message> messages) {
            this.messages = messages;
        }
    }
    static class FinalReport {
        List<Message> messages;
        FinalReport(List<Message> messages) {
            this.messages = messages;
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ProductsReport.class, this::makeReport)
                .match(FinalReport.class, this::makeFinalReport)
                .build();
    }

    private TreeMap<String, List<Message>> messagesAsMap(List<Message> messages) {
        TreeMap<String, List<Message>> messageMap = new TreeMap<String, List<Message>>();
        for(Message message : messages) {
            if(messageMap.containsKey(message.getProductType())) {
                messageMap.get(message.getProductType()).add(message);
            }
            else {
                List<Message> kindOfMessages = new ArrayList<Message>();
                kindOfMessages.add(message);
                messageMap.put(message.getProductType(), kindOfMessages);
            }
        }

        return messageMap;
    }

    private void makeReport(ProductsReport report) {
        log().info(String.format("---------------------------------"));
        log().info(String.format("Begin report for the last %d messages:", report.messages.size()));

        TreeMap<String, List<Message>> messagesMap = messagesAsMap(report.messages);
        for(Map.Entry<String, List<Message>> messages : messagesMap.entrySet()) {
            String productName = messages.getKey();
            int numberOfIndividualSales = 0;
            double totalValue = 0;

            for(Message m : messages.getValue()) {
                if (m instanceof MultipleSale) {
                    numberOfIndividualSales += ((MultipleSale) m).getItemsCount();
                    totalValue += ((MultipleSale) m).getCost() * numberOfIndividualSales;
                }
                else if (m instanceof SingleSale) {
                    numberOfIndividualSales += 1;
                    totalValue += ((SingleSale) m).getCost();
                }
            }

            if(numberOfIndividualSales != 0) {
                log().info(String.format("Product: %s; number of transactions: %d; number of sails: %d; total value: %.2f",
                        productName, messages.getValue().size(), numberOfIndividualSales, totalValue));
            }
        }

        log().info("end of report");
        log().info(String.format("---------------------------------"));
    }

    private void makeFinalReport(FinalReport report) {
        log().info(String.format("---------------------------------"));
        log().info(String.format("Begin final report:"));
        TreeMap<String, List<Message>> messageMap = messagesAsMap(report.messages);

        for(Map.Entry<String, List<Message>> messages : messageMap.entrySet()) {
            for(Message m : messages.getValue()) {
                if (m instanceof AdjustPrice) {
                    AdjustPrice adjustPrice = (AdjustPrice) m;
                    log().info(String.format("Product type: %s; price was %s at %.2f",
                            adjustPrice.getProductType(), adjustPrice.getOperation(), adjustPrice.getValue()));
                }
            }
        }

        log().info("end of report");
        log().info(String.format("---------------------------------"));

        // Sending a message to stop the messages processing actor
        getSender().tell("stop", getSelf());
    }
}