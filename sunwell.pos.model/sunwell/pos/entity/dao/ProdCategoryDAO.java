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
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@Dependent
public class ProdCategoryDAO 
{
    @PersistenceContext
    EntityManager em;
    
    public List<ProdCategory> findByTenant(Tenant _tenant) {
        TypedQuery<ProdCategory> query = em.createNamedQuery ("ProdCategory.findByTenant", ProdCategory.class);
        query.setParameter ("tenant", _tenant);
        List<ProdCategory> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
}
