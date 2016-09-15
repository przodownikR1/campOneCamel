package pl.java.scalatech.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class GenerateDataRoute extends RouteBuilder{

   @Override
    public void configure() throws Exception {
        from("timer://foo?fixedRate=true&period=2000")
         .bean(UserGenerate.class).to("seda:throttle?size=10");
        
         from("seda:throttle").throttle(2).timePeriodMillis(20000).to("seda:nextQueue");
         
         from("seda:nextQueue").to("activemq:randomUser");
    }

    
}
