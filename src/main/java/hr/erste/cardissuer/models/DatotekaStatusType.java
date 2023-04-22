package hr.erste.cardissuer.models;

public enum DatotekaStatusType {

IZDANA("Izdana"), NEAKTIVNA("Neaktivna"), NEMA("Nema");


    public final String label;
    private DatotekaStatusType(String label) {
        this.label = label;
    }
}
