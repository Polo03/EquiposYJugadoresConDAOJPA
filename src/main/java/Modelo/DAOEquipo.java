package Modelo;

import jakarta.persistence.*;

import java.util.ArrayList;

public class DAOEquipo {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EquiposYJugadoresConDAO");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public DAOEquipo() {

    }

    //add
    public void addEquipo(Equipo equipo) {
        tx.begin();
        Query query = em.createNativeQuery("INSERT INTO equipo (nombre, estadio) VALUES (?, ?)", Equipo.class);
        query.setParameter(1, equipo.getNombre());
        query.setParameter(2, equipo.getEstadio());
        query.executeUpdate();
        tx.commit();
    }

    //getById
    public Equipo getEquipo(int id) {
        return em.find(Equipo.class, id);
    }

    //getAll
    public ArrayList<Equipo> getAllEquipos() {
        return (ArrayList<Equipo>) em.createQuery("SELECT e FROM Equipo e", Equipo.class).getResultList();
    }

    //update
    public void updateEquipo(Equipo equipo) {
        tx.begin();
        em.merge(equipo);
        tx.commit();
    }

    //deleteAll
    public int deleteAllEquipos() {
        return em.createQuery("DELETE FROM Equipo").executeUpdate();
    }

    //deleteById
    public void deleteEquipoById(int id) {
        tx.begin();
        em.remove(em.find(Equipo.class, id));
        tx.commit();
    }

    //deleteByObjectEquipo
    public void deleteEquipo(Equipo equipo) {
        tx.begin();
        em.remove(equipo);
        tx.commit();
    }
}
