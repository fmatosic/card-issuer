package hr.erste.cardissuer.controllers.impl;

import hr.erste.cardissuer.controllers.ErsteRestController;
import hr.erste.cardissuer.controllers.OsobaController;
import hr.erste.cardissuer.models.Osoba;
import hr.erste.cardissuer.services.impl.OsobaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/osoba")
public class OsobaControllerImpl extends ErsteRestController implements OsobaController  {
    OsobaServiceImpl osobaService;

    @Override
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Osoba>> getOsoba() {
        List<Osoba> osobe = osobaService.getOsobe();
        return new  ResponseEntity<>(osobe,osobe.isEmpty() ?  HttpStatus.NO_CONTENT : HttpStatus.OK );
    }

    @Override
    @GetMapping(value = "/{oib}",produces = "application/json")
    public ResponseEntity<Osoba> getOsoba(@PathVariable @NotBlank  String oib) {
        try {
            Osoba osoba = osobaService.getOsoba(oib);
            return new ResponseEntity<>(osoba, HttpStatus.OK);
        }catch(EmptyResultDataAccessException ex){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * TODO: NEDOSTAJE REAKTIVACIJA
     * @param osoba
     * @return
     */
    @Override
    @PutMapping("/")
    public ResponseEntity<Osoba> putOsoba(@RequestBody @Validated Osoba osoba) {
        try {
            if (osobaService.createOsoba(osoba)) {
                return new ResponseEntity<>(osobaService.getOsoba(osoba.getOib()), HttpStatus.CREATED);
            }
        }catch (DuplicateKeyException ex){
           return  ResponseEntity.badRequest().build();
        }
      return new ResponseEntity<>(osoba,  HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @DeleteMapping("/{oib}")
    public ResponseEntity deleteOsoba(@PathVariable String oib) {
        if(osobaService.deleteOsoba(oib)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
