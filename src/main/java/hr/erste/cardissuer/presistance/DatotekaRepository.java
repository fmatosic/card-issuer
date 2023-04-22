package hr.erste.cardissuer.presistance;

import hr.erste.cardissuer.models.Datoteka;

public interface DatotekaRepository {
    Datoteka getDatoteka(String oib);
    boolean createDatoteka(Datoteka datoteka);
}
