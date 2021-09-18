package br.com.diolabs.springrestmvc.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.diolabs.springrestmvc.model.Jedi;
import br.com.diolabs.springrestmvc.repository.JediRepository;

@RestController
public class JediResource {
    
    @Autowired
    private JediRepository jediRepository;

    @GetMapping("/jedi")
    public ResponseEntity<List<Jedi>> getAllJedi(){
        List<Jedi> listaJedi = jediRepository.findAll();

        if (listaJedi.isEmpty()){           
            
            return ResponseEntity.notFound().build();
        }
        return  new ResponseEntity<>(listaJedi, HttpStatus.FOUND);
       
    }

    @PostMapping("/jedi")
    public ResponseEntity<Jedi> addJedi(@Valid @RequestBody Jedi jediDto){
        try{
            Jedi jedi = jediRepository.saveAndFlush(jediDto);
            return new ResponseEntity<>(jedi, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
       
    }

    @GetMapping("/jedi/{id}")
    public ResponseEntity<Jedi> findById(@PathVariable("id") Long id){
        Optional<Jedi> opJedi = jediRepository.findById(id);

        if (opJedi.isPresent()){            
            return  new ResponseEntity<>(opJedi.get(), HttpStatus.FOUND);
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/jedi/{id}")
    public ResponseEntity<Jedi> atualizaById(@PathVariable("id") Long id, @Valid @RequestBody Jedi jediDto){
        Optional<Jedi> opJedi = jediRepository.findById(id);

        if (opJedi.isPresent()){            
            opJedi.get().setNome(jediDto.getNome());
            opJedi.get().setSobrenome(jediDto.getSobrenome());
        }else{
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(jediRepository.saveAndFlush(opJedi.get()));
    }



    @DeleteMapping("/jedi/{id}")
    public ResponseEntity<Jedi> deleteById(@PathVariable("id") Long id){
        Optional<Jedi> opJedi = jediRepository.findById(id);

        if (opJedi.isPresent()){            
           jediRepository.delete(opJedi.get());
        }else{
            return  ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.noContent().build();
    }




}
