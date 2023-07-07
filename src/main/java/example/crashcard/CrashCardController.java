/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package example.crashcard;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ezequ
 */
@RestController
@RequestMapping("/cashcards")
public class CrashCardController {
    
    private CrashCardRepository cashCardRepository;
    
    public CrashCardController (CrashCardRepository cashCardRepository){
    this.cashCardRepository = cashCardRepository;
    }
    
    @GetMapping("/{requestId}")
    public ResponseEntity<CrashCard> findById(@PathVariable Long requestId){
        
        Optional<CrashCard> cashCardOptional = cashCardRepository.findById(requestId);
        
        if(cashCardOptional.isPresent()){
        
            return ResponseEntity.ok(cashCardOptional.get());
        }
        else{
        
            return ResponseEntity.notFound().build();
        }
    
    
    }
    
    @PostMapping
    private ResponseEntity createCashCard(){
    return null;
    }
}
