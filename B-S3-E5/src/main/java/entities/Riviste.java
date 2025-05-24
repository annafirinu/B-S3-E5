package entities;

import enumeration.Periodicita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity

public class Riviste extends Catalogo{
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Riviste(String cosiceISBN, String titolo, Integer annoPubblicazione, Integer numeroPagine, Periodicita periodicita) {
        super(cosiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Riviste() {
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "codiceISBN='" + getCosiceISBN() + '\'' +
                "titolo='" + getTitolo() + '\'' +
                "anno pubblicazione='" + getAnnoPubblicazione() + '\'' +
                "numero pagine='" + getNumeroPagine() + '\'' +
                "periodicita=" + periodicita +
                '}';
    }


}
