package entities;

import jakarta.persistence.Entity;


@Entity
public class Libri extends Catalogo{
    private String autore;
    private String genere;

    public Libri(String cosiceISBN, String titolo, Integer annoPubblicazione, Integer numeroPagine, String autore, String genere) {
        super(cosiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libri() {
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "codiceISBN='" + getCosiceISBN() + '\'' +
                "titolo='" + getTitolo() + '\'' +
                "anno pubblicazione='" + getAnnoPubblicazione() + '\'' +
                "numero pagine='" + getNumeroPagine() + '\'' +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }


}
