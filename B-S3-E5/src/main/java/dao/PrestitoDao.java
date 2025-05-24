package dao;

import entities.Catalogo;
import entities.Prestito;
import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class PrestitoDao {
    private EntityManager em;
    private UtenteDao utenteDao;

    public PrestitoDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
        utenteDao = new UtenteDao();
    }


    //Metodo per registrare un nuovo Prestito
    public void aggiungiPrestito(Prestito prestito) {
        em.getTransaction().begin();
        em.persist(prestito);
        em.getTransaction().commit();
    }


    //Metodo per ricercare gli elementi attualmente in prestito dato un numero di tessera utente
    public List<Catalogo> elementiInPrestitoPerUtente(int numeroTessera) {
        Utente utente = utenteDao.getByNTessera(numeroTessera);
        if (utente == null) {
            return Collections.emptyList();
        }

        TypedQuery<Catalogo> query = em.createQuery(
                "SELECT p.catalogo FROM Prestito p " +
                        "WHERE p.utente = :utente " +
                        "AND p.dataRestituzioneEffettiva IS NULL", Catalogo.class);
        query.setParameter("utente", utente);
        return query.getResultList();
    }

    //Metodo per ricercare tutti i metodi scaduti e non ancora restituiti
    public List<Prestito> prestitiScaduti() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL",
                Prestito.class);
        query.setParameter("oggi", LocalDate.now());
        return query.getResultList();

    }

}





