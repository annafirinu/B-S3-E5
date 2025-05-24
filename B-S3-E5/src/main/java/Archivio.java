import dao.CatalogoDao;
import dao.LibriDao;
import dao.PrestitoDao;
import dao.UtenteDao;
import entities.Catalogo;
import entities.Libri;
import entities.Prestito;
import entities.Utente;

import java.util.List;

public class Archivio {

    private CatalogoDao catalogoDao;
    private LibriDao libriDao;
    private PrestitoDao prestitoDao;
    private UtenteDao utenteDao;

    public Archivio() {
       catalogoDao = new CatalogoDao();
       libriDao = new LibriDao();
       prestitoDao = new PrestitoDao();
       utenteDao = new UtenteDao();

    }

    //1
    public void aggiungiElementoCatalogo(Catalogo catalogo) {
        catalogoDao.aggiungiElemento(catalogo);
    }

    public void aggiungiNuovoUtente(Utente utente) {
        utenteDao.aggiungiUtente(utente);
    }

    public void aggiungiNuovoPrestito(Prestito prestito) {
        prestitoDao.aggiungiPrestito(prestito);
    }

    //2
    public Catalogo cercaPerISBN(String isbn) {
        return catalogoDao.getByISBN(isbn);
    }

    public Utente cercaPerNumeroTessera(int numero) {
        return utenteDao.getByNTessera(numero);
    }

    //3
    public void rimuoviElemento(String isbn) {
        catalogoDao.rimuoviElemento(isbn);
    }

    //4
    public List<Catalogo> cercaPerAnno(int anno) {
        return catalogoDao.ricercaPerAnno(anno);
    }

    //5
    public List<Catalogo> cercaPerTitolo(String titolo) {
        return catalogoDao.ricercaPerTitolo(titolo);
    }

    //6
    public List<Libri> cercaLibriPerAutore(String autore) {
        return libriDao.ricercaPerAutore(autore);
    }

    //7
    public List<Catalogo> elementiInPrestitoPerUtente(int numeroTessera) {
        return prestitoDao.elementiInPrestitoPerUtente(numeroTessera);
    }

    //8
    public List<Prestito> getPrestitiScaduti() {
        return prestitoDao.prestitiScaduti();
    }
}