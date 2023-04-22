package hr.erste.cardissuer.services.impl;

import hr.erste.cardissuer.models.Osoba;
import hr.erste.cardissuer.presistance.impl.DatotekaRepositoryImpl;
import hr.erste.cardissuer.presistance.impl.OsobaRepositoryImpl;
import hr.erste.cardissuer.services.OsobaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class OsobaServiceImpl implements OsobaService {
    OsobaRepositoryImpl osobaRepository;
    DatotekaRepositoryImpl datotekaRepository;
    @Override
    public List<Osoba> getOsobe() {
        return osobaRepository.getOsobe();
    }

    @Override
    public Osoba getOsoba(String oib) {
        Osoba osoba = osobaRepository.getOsoba(oib);
        try {
            osoba.setDatoteka(datotekaRepository.getDatoteka(oib));
        }catch(EmptyResultDataAccessException ex){
            log.info("Ne postoji datoteka !!!");
        }
        return osoba;

    }
    @Override
    public boolean createOsoba(Osoba osoba) {
        return osobaRepository.createOsoba(osoba);
    }

    @Override
    public boolean deleteOsoba(String oib) {
        return osobaRepository.deleteOsoba(oib);
    }
}
