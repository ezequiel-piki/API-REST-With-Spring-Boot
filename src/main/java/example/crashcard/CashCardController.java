/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.crashcard;

import java.net.URI;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/cashcards")
public class CashCardController {
    
    private CrashCardRepository cashCardRepository;
    
    public CashCardController(CrashCardRepository cashCardRepository){
    this.cashCardRepository = cashCardRepository;
    }
    
    @GetMapping("/{requestId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestId){
        
        Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestId);
        
        if(cashCardOptional.isPresent()){
        
            return ResponseEntity.ok(cashCardOptional.get());
        }
        else{
        
            return ResponseEntity.notFound().build();
        }
    
    
    }
    
    @PostMapping
    private ResponseEntity createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb){
        CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
        URI locationOfNewCashCard = ucb
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @GetMapping()
    public ResponseEntity<Iterable<CashCard>> findAll(){
        return ResponseEntity.ok(cashCardRepository.findAll());
    }
}
