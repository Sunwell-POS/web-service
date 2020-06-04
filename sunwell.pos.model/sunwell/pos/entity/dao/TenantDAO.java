/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantDAO.java
 *
 * Created on Oct 17, 2017, 10:29:20 AM
 */

package sunwell.pos.entity.dao;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@Dependent
public class TenantDAO 
{
    @PersistenceContext
    EntityManager em;
    
    public Tenant findByEmail(String _email) {
        TypedQuery<Tenant> query = em.createNamedQuery ("Tenant.findByEmail", Tenant.class);
        query.setParameter ("email", _email);
        List<Tenant> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList.get (0);
        
        return null;
    }
}
