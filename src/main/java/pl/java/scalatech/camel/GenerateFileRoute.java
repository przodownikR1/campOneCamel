package pl.java.scalatech.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.java.scalatech.domain.User;
@Component
public class GenerateFileRoute extends RouteBuilder{
    private static final String USER_SERVICE = "userService";

    private static final String DIRECT_GENERATE = "direct:generate";

    private static final String FILE_USER_FILE_EXIST_APPEND = "file://user?fileExist=Append";

    private static final String ACTIVEMQ_USER = "activemq:user";

    private static final String SEDA_TO_FILE_AND_QUEUE = "seda:toFileAndQueue";

    private static final String DIRECT_SAVE_TO_DB = "direct:saveToDB";

    private static final String CAMEL_FILE_NAME = "CamelFileName";

    DataFormat bindy = new BindyCsvDataFormat(User.class);
    
    @Value("${fileName}")
    private String fileName;
    
    @Override
    public void configure() throws Exception {
        
       from(DIRECT_GENERATE)
         .loop(10)
         .bean(UserGenerate.class)
         .setHeader(CAMEL_FILE_NAME).simple(fileName)
         .multicast()
         .parallelProcessing().to(DIRECT_SAVE_TO_DB,SEDA_TO_FILE_AND_QUEUE,ACTIVEMQ_USER);
       
         from(SEDA_TO_FILE_AND_QUEUE).marshal(bindy).to(FILE_USER_FILE_EXIST_APPEND);
       
         from(DIRECT_SAVE_TO_DB).removeHeader(CAMEL_FILE_NAME).beanRef(USER_SERVICE);
    }
    
   

}
