package hr.erste.cardissuer.controllers.impl;

import hr.erste.cardissuer.services.impl.DatotekaServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/datoteka")
public class DatotekaControllerImpl  {
    DatotekaServiceImpl datotekaService;

    @GetMapping(value = "/{oib}",  produces = "application/octet-stream")
    public ResponseEntity<Resource> getDatoteka(@PathVariable @NotBlank String oib) throws IOException {
        try {
            File datoteka = datotekaService.getDatoteka(oib);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(datoteka));
            MediaType mediaType = MediaType.parseMediaType("application/octet-stream");
            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header("Content-Disposition", "attachment; filename=" + datoteka.getName())
                    .body(resource);
        }
        catch(EmptyResultDataAccessException ex){
            return ResponseEntity.badRequest().build();
        }
    }
}
