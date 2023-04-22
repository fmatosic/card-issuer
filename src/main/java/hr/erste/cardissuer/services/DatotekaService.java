package hr.erste.cardissuer.services;

import java.io.File;
import java.io.IOException;

public interface DatotekaService {
    File getDatoteka(String oib) throws IOException;
}
