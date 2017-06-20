package com.sfirsov.processing.actors;

import akka.actor.AbstractActor;
import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.sfirsov.processing.model.*;

import java.util.ArrayList;
import java.util.List;

public class MessageSystem extends AbstractLoggingActor {
    private final int transactionsToProcess;
    private final int reportRate;
    private final ActorRef reportActor;
    List<Message> messages = new ArrayList<Message>();
    private int salesCount = 0;
    private final AbstractActor.Receive enabled;
    private final AbstractActor.Receive disabled;

    static public Props props(int transactionsToProcess, int reportRate, ActorRef reportActor) {
        return Props.create(MessageSystem.class, () -> new MessageSystem(transactionsToProcess, reportRate, reportActor));
    }

    MessageSystem(int transactionsNumberToProcess, int reportRate, ActorRef reportActor) {
        this.transactionsToProcess = transactionsNumberToProcess;
        this.reportRate = reportRate;
        this.reportActor = reportActor;

        // Actor can be in 2 states:
        // When the actor enabled it processes messages messages
        enabled = receiveBuilder()
                .match(SingleSale.class, (sale) -> addMessage(sale))
                .match(MultipleSale.class, (sale) -> addMessage(sale))
                .match(AdjustPrice.class, this::adjustPrice)
                .build();

        // When the actor disabled it can write final report and shutdown itself
        disabled = receiveBuilder()
                .matchEquals("report", (ignore) -> reportActor.tell(new ReportGenerator.FinalReport(messages), getSelf()))
                .matchEquals("stop", (ignore) -> getContext().stop(getSelf()))
                .build();
    }

    @Override
    public AbstractActor.Receive createReceive() {
        return enabled;
    }

    private void disableSalesIfNeeded() {
        if(messages.size() >= transactionsToProcess) {
            log().info("Stop accepting new orders");
            getContext().become(disabled);
        }
    }

    private void makeReportIfNeeded() {
        if(messages.size() % reportRate == 0 && !messages.isEmpty()) {
            reportActor.tell(new ReportGenerator.ProductsReport(messages.subList(messages.size()-(reportRate), messages.size())), getSelf());
        }
    }

    private void addMessage(Message message) {
        messages.add(message);
        log().info(message.toString());

        makeReportIfNeeded();
        disableSalesIfNeeded();
    }

    private void adjustPrice(AdjustPrice adjustPrice) {
        for(Message message : messages) {
            if(message.getProductType().equals(adjustPrice.getProductType()) && (message instanceof SingleSale || message instanceof MultipleSale)) {
                SingleSale forAdjust = (SingleSale) message;
                switch (adjustPrice.getOperation()) {
                    case Add: forAdjust.setCost(forAdjust.getCost() + adjustPrice.getValue()); break;
                    case Subtract: forAdjust.setCost(forAdjust.getCost() - adjustPrice.getValue()); break;
                    case Multiply: forAdjust.setCost(forAdjust.getCost() * adjustPrice.getValue()); break;
                    default: break;
                }
            }
        };

        addMessage(adjustPrice);
    }
}
