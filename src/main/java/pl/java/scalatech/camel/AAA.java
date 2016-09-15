package pl.java.scalatech.camel;

import java.math.BigDecimal;

import org.apache.camel.Header;

public class AAA {

    public BigDecimal salary(@Header("counter")String coutner){
        return new BigDecimal(coutner);
    }
}
