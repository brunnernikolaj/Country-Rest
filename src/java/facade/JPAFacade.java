/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.City;
import entity.Country;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nikolaj
 */
public class JPAFacade {

    EntityManager manager;

    public JPAFacade() {
        manager = Persistence.createEntityManagerFactory("PU").createEntityManager();
    }

//    public Person findPerson(long id) {
//        return manager.find(Person.class, id);
//    }
    public List<Country> getAllCountries() {
        return manager.createNamedQuery("Country.findAll").getResultList();
    }

    public List<Country> getCountriesGreaterThan(long population) {
        Query query = manager.createQuery("SELECT c FROM Country c WHERE c.population > :pop");
        query.setParameter("pop", population);
        return query.getResultList();
    }
    
    public List<City> getAllCities(String countryCode) {
        Query query = manager.createNamedQuery("City.findByCountryCode");
        query.setParameter("countryCode", countryCode);
        return query.getResultList();
    }
    
    public <T> void create(T entity) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.persist(entity);
        transaction.commit();
    }

    public <T> void update(T entity) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.merge(entity);
        transaction.commit();
    }

    public <T> void updateMany(T... entities) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        for (T entity : entities) {
            manager.merge(entity);
        }
        transaction.commit();
    }

    public <T> void delete(T entity) {
        EntityTransaction transaction = manager.getTransaction();

        transaction.begin();
        manager.remove(entity);
        transaction.commit();
    }
}
