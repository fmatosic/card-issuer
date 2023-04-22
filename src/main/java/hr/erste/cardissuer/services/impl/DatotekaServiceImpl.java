package hr.erste.cardissuer.services.impl;

import hr.erste.cardissuer.models.Datoteka;
import hr.erste.cardissuer.models.Osoba;
import hr.erste.cardissuer.presistance.OsobaRepository;
import hr.erste.cardissuer.presistance.impl.DatotekaRepositoryImpl;
import hr.erste.cardissuer.services.DatotekaService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@AllArgsConstructor
public class DatotekaServiceImpl implements DatotekaService {
DatotekaRepositoryImpl datotekaRepository;
OsobaRepository osobaRepository;
    @Override
    public File getDatoteka(String oib) throws IOException {
        Osoba osoba = osobaRepository.getOsoba(oib);
        Datoteka datoteka = null;
        try {
            datoteka  = datotekaRepository.getDatoteka(oib);
        }catch(EmptyResultDataAccessException ex){
            datoteka = new Datoteka();
            datoteka.setOib(oib);
            datoteka.setFilename(oib+System.currentTimeMillis());
            if(datotekaRepository.createDatoteka(datoteka)){
               datoteka = datotekaRepository.getDatoteka(oib);
            }
        }

        osoba.setDatoteka(datoteka);
        String fileName = datoteka.getFilename();
        File file = File.createTempFile(fileName, ".txt");
        StringBuilder datotekaContent = new StringBuilder();
        datotekaContent.append(osoba.getIme()).append(";")
                .append(osoba.getPrezime()).append(";")
                .append(osoba.getOib()).append(";")
                .append(osoba.getDatotekaStatusType().label).append(";")
                .append(System.lineSeparator());
        // Write text to the file
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(datotekaContent.toString());
            return file;
        }
    }
}
