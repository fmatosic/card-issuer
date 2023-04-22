package hr.erste.cardissuer.services;

import hr.erste.cardissuer.models.Osoba;

import java.util.List;

public interface OsobaService {
    List<Osoba> getOsobe();
    Osoba getOsoba(String oib);
    boolean createOsoba(Osoba osoba);
    boolean deleteOsoba(String oib);
}
