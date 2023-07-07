/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.crashcard;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


@JsonTest
public class CashcardJsonTest {
         @Autowired
    private JacksonTester<CashCard> json;
    @Test
        public void myFirstTest(){
        assertThat(42).isEqualTo(42);
        }
        
    

    @Test
    public void crashCardSerializationTest() throws IOException {
        CashCard cashCard = new CashCard(99L, 123.45);
        assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
             .isEqualTo(123.45);
    }
    
    @Test
public void crashCardDeserializationTest() throws IOException {
   String expected = """
           {
               "id":1000,
               "amount":123.45
           }
           """;
   assertThat(json.parse(expected))
           .isEqualTo(new CashCard(1000L, 123.45));
   assertThat(json.parseObject(expected).id()).isEqualTo(1000);
   assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
}
}