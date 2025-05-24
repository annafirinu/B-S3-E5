package entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Catalogo {
    @Id
    @Column(name = "codice_isbn")
    private String cosiceISBN;
    private String titolo;
    @Column(name = "anno_pubblicazione")
    private Integer annoPubblicazione;
    @Column(name = "numero_pagine")
    private Integer numeroPagine;

    @OneToMany(mappedBy = "catalogo")
    private List<Prestito> prestito;

    public Catalogo(String cosiceISBN, String titolo, Integer annoPubblicazione, Integer numeroPagine) {
        this.cosiceISBN = cosiceISBN;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    public Catalogo() {
    }

    public String getCosiceISBN() {
        return cosiceISBN;
    }

    public void setCosiceISBN(String cosiceISBN) {
        this.cosiceISBN = cosiceISBN;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Integer getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public void setAnnoPubblicazione(Integer annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public Integer getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(Integer numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public List<Prestito> getPrestito() {
        return prestito;
    }

    public void setPrestito(List<Prestito> prestito) {
        this.prestito = prestito;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "cosiceISBN='" + cosiceISBN + '\'' +
                ", titolo='" + titolo + '\'' +
                ", annoPubblicazione=" + annoPubblicazione +
                ", numeroPagine=" + numeroPagine +
                ", prestito=" + prestito +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalogo)) return false;
        Catalogo that = (Catalogo) o;
        return Objects.equals(cosiceISBN, that.cosiceISBN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cosiceISBN);
    }
}

