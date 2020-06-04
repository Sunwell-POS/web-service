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
import sunwell.pos.entity.PaymentMethodObj;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.SalesPayment;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@Dependent
public class SalesPaymentDAO 
{
    @PersistenceContext
    EntityManager em;
    
//    public List<SalesPayment> findByTenant(Tenant _tenant) {
//        TypedQuery<SalesPayment> query = em.createNamedQuery ("PaymentMethodObj.findByTenant", SalesPayment.class);
//        query.setParameter ("tenant", _tenant);
//        List<SalesPayment> resultList = query.getResultList ();
//        if(resultList != null && resultList.size () > 0) 
//            return resultList;
//        
//        return null;
//    }
    
    public List<SalesPayment> findByTenant(Tenant _tenant) {
        TypedQuery<SalesPayment> query = em.createNamedQuery ("SalesPayment.findByTenant", SalesPayment.class);
        query.setParameter ("tenant", _tenant);
        List<SalesPayment> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
}
