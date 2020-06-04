/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredDAO.java
 *
 * Created on Oct 16, 2017, 9:57:23 AM
 */

package sunwell.pos.entity.dao;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;
import sunwell.pos.entity.UserGroup;

/**
 *
 * @author Benny
 */
@Dependent
public class CredDAO implements Serializable
{
    @PersistenceContext
    EntityManager em;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public void create (User _uc)
    {
        em.persist (_uc);
    }
        
    public List<User> findByEmail(String _email) {
        TypedQuery<User> query = em.createNamedQuery ("User.findByEmail", User.class);
        query.setParameter ("email", _email);
        List<User> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
    
    public User findByTenantAndEmail(Tenant _tenant, String _email) {
        TypedQuery<User> query = em.createNamedQuery ("User.findByTenantAndEmail", User.class);
        query.setParameter ("email", _email);
        query.setParameter ("tenant", _tenant);
        List<User> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList.get (0);
        
        return null;
    }
    
    public List<User> findByTenant(Tenant _t) {
        TypedQuery<User> query = em.createNamedQuery ("User.findByTenant", User.class);
        query.setParameter ("tenant", _t);
        List<User> resultList = query.getResultList ();
        return resultList;
    }
    
    public List<UserGroup> findUserGroupByTenant(Tenant _t) {
        TypedQuery<UserGroup> query = em.createNamedQuery ("UserGroup.findByTenant", UserGroup.class);
        query.setParameter ("tenant", _t);
        List<UserGroup> resultList = query.getResultList ();
        return resultList;
    }
}
