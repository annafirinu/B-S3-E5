package dao;

import entities.Catalogo;
import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class UtenteDao {
    private EntityManager em;

    //Costruttore
    public UtenteDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    //Metodo per aggiungere un nuovo Utente
    public void aggiungiUtente(Utente utente) {
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    //Metodo per ricercare un utente tramite numero tessera
    public Utente getByNTessera(int numeroTessera){
        return em.find(Utente.class, numeroTessera);
    }
}
