package hr.erste.cardissuer.presistance;

import hr.erste.cardissuer.models.Osoba;

import java.util.List;

public interface OsobaRepository {

    Osoba getOsoba(String oib);
    boolean createOsoba(Osoba osoba);
    boolean deleteOsoba(String oib);

    List<Osoba> getOsobe();
}
