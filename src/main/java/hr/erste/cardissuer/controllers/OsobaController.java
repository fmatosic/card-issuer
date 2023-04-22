package hr.erste.cardissuer.controllers;

import hr.erste.cardissuer.models.Osoba;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
import java.util.List;

public interface OsobaController extends Api {

    ResponseEntity<List<Osoba>> getOsoba();
    ResponseEntity<Osoba> getOsoba(@PathVariable @NotBlank  String oib);

    ResponseEntity<Osoba> putOsoba(Osoba osoba);

    ResponseEntity<Object> deleteOsoba(@PathParam("oib")String oib);





}
