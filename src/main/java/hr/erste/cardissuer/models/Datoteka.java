package hr.erste.cardissuer.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class Datoteka {

    String oib;
    String filename;
    Timestamp created;
    Timestamp inactive;
}
