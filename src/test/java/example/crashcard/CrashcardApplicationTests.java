package example.crashcard;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrashcardApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;
	
	@Test
	public void shouldReturnACashCardWhenDataIsSaved(){
	    
            ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/99",String.class);
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            
            DocumentContext documentContext = JsonPath.parse(response.getBody());
            Number id = documentContext.read("$.id");
            assertThat(id).isEqualTo(99);
            
            Double amount = documentContext.read("$.amount");
            assertThat(amount).isEqualTo(123.45);
	}
        
        @Test
        public void shouldNotReturnACashCardWithAnUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/cashcards/1000", String.class);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            assertThat(response.getBody()).isBlank();
        }
        
        @Test
        public void shouldCreateANewCashCard(){
        CrashCard newCashCard = new CrashCard(null, 250.00 );
        ResponseEntity<Void> createResponse = restTemplate.postForEntity("/cashcards", newCashCard, Void.class);
            assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
  
	@Test
	void contextLoads() {
	}
        
        

}