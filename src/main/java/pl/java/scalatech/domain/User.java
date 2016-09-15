package pl.java.scalatech.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@CsvRecord(separator = ",", crlf = "UNIX", skipFirstLine = true)
public class User {
    @DataField(pos = 1)
    @Id @GeneratedValue
    private Long id;
    @DataField(pos = 2)
    private String email;
    @DataField(pos = 3)
    private String name;
    @DataField(pos = 10)
    private String creditCard;
    @DataField(pos = 4)
    private String creditCardType;
    private String login;
    @DataField(pos = 5)
    private BigDecimal salary;
    @DataField(pos = 6)
    private Currency currency;
    @DataField(pos = 7)
    private Position position;
    @DataField(pos = 8)
    private String city;
    @DataField(pos = 9)
    private String country;
    
    
}