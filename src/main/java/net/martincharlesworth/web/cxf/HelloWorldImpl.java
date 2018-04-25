package net.martincharlesworth.web.cxf;
 
import javax.jws.WebService;
 
@WebService(endpointInterface = "net.martincharlesworth.web.cxf.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
 
    public String sayHi(String text) {
        return "Hello " + text;
    }
}