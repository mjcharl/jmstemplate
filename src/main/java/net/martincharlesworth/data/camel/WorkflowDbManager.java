package net.martincharlesworth.data.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class WorkflowDbManager implements Processor {
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader("CamelFileName", "soap.txt");
        System.out.println("Just received message " + exchange.getIn().getBody());
        //exchange.getIn().setBody(exchange.getIn().getBody().toString());
    }
}
