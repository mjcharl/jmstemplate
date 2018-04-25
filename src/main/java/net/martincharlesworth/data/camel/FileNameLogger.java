package net.martincharlesworth.data.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class FileNameLogger implements Processor {
  public void process(Exchange exchange) throws Exception {
    System.out.println("Just copied " + exchange.getIn().getHeader("CamelFileName"));
  }
}
