package hr.erste.cardissuer.controllers;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DatotekaController {
    ResponseEntity<byte[]>getDatoteka(String oib) throws IOException;
}
