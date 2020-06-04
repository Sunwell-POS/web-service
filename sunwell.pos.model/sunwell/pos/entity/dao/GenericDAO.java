/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * GenericDAO.java
 *
 * Created on Oct 16, 2017, 9:55:01 AM
 */

package sunwell.pos.entity.dao;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

/**
 *
 * @author Benny
 */
@Dependent
public class GenericDAO 
{
    @PersistenceContext
    EntityManager em;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public <T> void create (T _entity)
    {
        em.persist (_entity);
        em.flush ();
        em.refresh (_entity);
//        System.out.println ("ID MM: " + _sp.getId ());
    }
    
    public <T> T findById(Object _id, Class<T> _class) {
        return em.find (_class, _id);
    }
    
    public <T> T edit(T _entity) {
        em.merge (_entity);
        em.flush ();
        return _entity;
    }
    
    public <T> T delete(T _enity) {
        em.merge (_enity);
        em.remove (_enity);
        em.flush ();
        return _enity;
    }
 
    
    public <T> List<T> findAll(Class<T> _class) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(_class);
        Root<T> c = q.from(_class);
        q.select(c);
        TypedQuery<T> query = em.createQuery(q);
        List<T> results = query.getResultList();
        if(results.size () <= 0)
            return null;
        
        return results;
    }
    
    
    public void flush() {
        em.flush ();
    }
    
    public void remove(Object _entity) {
        em.remove (_entity);
    }
    
    public void refresh(Object _ent) {
        em.refresh (_ent);
    }
    
    public void rollback() {
        em.getTransaction ().rollback ();
    }
}
