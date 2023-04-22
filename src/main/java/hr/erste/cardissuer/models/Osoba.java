package hr.erste.cardissuer.models;

import hr.erste.cardissuer.annotations.ValidOib;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
public class Osoba {
    @NotNull
    @NotEmpty(message = "{field.not.empty}")
    String ime;
    @NotNull
    @NotEmpty(message = "{field.not.empty}")
    String prezime;
    @NotNull
    @NotEmpty(message = "{field.not.empty}")
    @ValidOib
    String oib;
    Timestamp created;
    Timestamp inactive;
    Datoteka datoteka;
    DatotekaStatusType datotekaStatusType;
    public DatotekaStatusType getDatotekaStatusType() {
        if(datoteka != null){
            if (datoteka.inactive != null){return DatotekaStatusType.NEAKTIVNA;}
            else{ return DatotekaStatusType.IZDANA;}
        }else{
            return DatotekaStatusType.NEMA;
        }
    }
}
