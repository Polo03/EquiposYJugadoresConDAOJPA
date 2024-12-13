package Modelo;

import jakarta.persistence.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DAOGenerico<T> {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EquiposYJugadoresConDAO");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    Class<T> clase;

    public DAOGenerico(Class<T> clase) {
        this.clase = clase;
    }

    //ADD
    public void add(T object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        tx.begin();
        if(clase.getSimpleName().toLowerCase().equals("equipo")){
            // Obtener los valores de los campos del objeto usando reflexion o getters
            String nombreEquipo = (String) clase.getMethod("getNombre").invoke(object);
            String estadioEquipo = (String) clase.getMethod("getEstadio").invoke(object);

            Query query = em.createNativeQuery("INSERT INTO equipo (nombre, estadio) VALUES (?, ?)", Equipo.class);
            query.setParameter(1, nombreEquipo);
            query.setParameter(2, estadioEquipo);
            query.executeUpdate();
        }else if(clase.getSimpleName().toLowerCase().equals("jugador")){
            // Obtener los valores de los campos del objeto usando reflexion o getters
            String nombreEquipo = (String) clase.getMethod("getNombre").invoke(object);
            Float estatura = (Float) clase.getMethod("getEstatura").invoke(object);
            Float peso = (Float) clase.getMethod("getPeso").invoke(object);
            Integer idEquipo = (Integer) clase.getMethod("getIdEquipo").invoke(object);

            Query query = em.createNativeQuery("INSERT INTO jugador (nombre, estatura, peso, idEquipo) VALUES (?, ?, ?, ?)", Equipo.class);
            query.setParameter(1, nombreEquipo);
            query.setParameter(2, estatura);
            query.setParameter(3, peso);
            query.setParameter(4, idEquipo);
            query.executeUpdate();
        }

        tx.commit();
    }

    //SELECT BY ID
    public T getById(int id){
        return em.find(clase, id);
    }

    //SELECT ALL
    public ArrayList<T> getAll(){
        String nombreClase = clase.getSimpleName();
        return (ArrayList<T>) em.createQuery("SELECT e FROM "+nombreClase+" e", clase).getResultList();
    }

    //UPDATE
    public void update(T object){
        tx.begin();
        em.merge(object);
        tx.commit();
    }

    //DELETE BY OBJECT
    public void deleteByObject(T object){
        tx.begin();
        em.remove(object);
        tx.commit();
    }

    //DELETE BY ID
    public void deleteById(int id){
        tx.begin();
        em.remove(em.find(clase, id));
        tx.commit();
    }

    //DELETE ALL
    public int deleteAll(){
        return em.createQuery("DELETE FROM Equipo").executeUpdate();
    }


    /*public void dameClase(){
        System.out.println(clase);
    }*/
}
