package dao;
import entities.Catalogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CatalogoDao {
    private EntityManager em;

    //Costruttore
    public CatalogoDao(){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("postgres");
        em=emf.createEntityManager();
    }

    //Metodo per aggiungere un elemento del catalogo
    public void aggiungiElemento (Catalogo catalogo){
        em.getTransaction().begin();
        em.persist(catalogo);
        em.getTransaction().commit();
    }

    //Metodo per ricercare un elemento tramite l'ISBN
    public Catalogo getByISBN(String isbn){
        return em.find(Catalogo.class, isbn);
    }

    //Metodo per rimuovere un elemento del catalogo dato un codice ISBN
    public void rimuoviElemento (String isbn){
        Catalogo c = getByISBN(isbn);
        if(c!=null){
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        } else{
            System.out.println("Nessun elemento con ISBN "+isbn);
        }
    }

    //Metodo per ricercare elementi dato l'anno di pubblicazione
    public List<Catalogo> ricercaPerAnno (int anno){
        TypedQuery<Catalogo> query = em.createQuery("select c from Catalogo c where c.annoPubblicazione = :anno", Catalogo.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    //Metodo per ricercare elementi dato il titolo o una sua parte
    public List<Catalogo> ricercaPerTitolo(String titolo){
        TypedQuery<Catalogo> query = em.createQuery("select c from Catalogo c where lower(c.titolo) like lower (:titolo)", Catalogo.class);
        query.setParameter("titolo", "%"+titolo+"%");
        return query.getResultList();
    }

}

