package dao;


import entities.Libri;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LibriDao {
     private EntityManager em;

    public LibriDao(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("postgres");
        em = emf.createEntityManager();
    }

    //Metodo per ricercare elementi(in questo caso solo libri) dato l'autore
    public List<Libri> ricercaPerAutore(String autore){
        TypedQuery<Libri> query = em.createQuery("select l from Libri l where l.autore = :autore", Libri.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }





}

