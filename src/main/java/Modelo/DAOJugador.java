package Modelo;

import jakarta.persistence.*;

import java.util.ArrayList;

public class DAOJugador {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EquiposYJugadoresConDAO");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public DAOJugador() {

    }

    //add
    public void addJugador(Jugador jugador) {
        tx.begin();
        Query query = em.createNativeQuery("INSERT INTO jugador (nombre, estatura, peso, idEquipo) VALUES (?, ?, ?, ?)", Jugador.class);
        query.setParameter(1, jugador.getNombre());
        query.setParameter(2, jugador.getEstatura());
        query.setParameter(3, jugador.getPeso());
        query.setParameter(4, jugador.getIdEquipo());
        query.executeUpdate();
        tx.commit();
    }

    //getById
    public Jugador getJugador(int id) {
        return em.find(Jugador.class, id);
    }

    //getAll
    public ArrayList<Jugador> getAllJugador() {
        return (ArrayList<Jugador>) em.createQuery("SELECT j FROM Jugador j", Jugador.class).getResultList();
    }

    //update
    public void updateJugador(Jugador jugador) {
        tx.begin();
        em.merge(jugador);
        tx.commit();
    }

    //deleteAll
    public int deleteAllJugadores() {
        return em.createQuery("DELETE FROM Jugador").executeUpdate();
    }

    //deleteById
    public void deleteJugador(int id) {
        tx.begin();
        em.remove(em.find(Jugador.class, id));
        tx.commit();
    }

    //deleteByObjectEquipo
    public void deleteJugador(Jugador jugador) {
        tx.begin();
        em.remove(jugador);
        tx.commit();
    }
}
