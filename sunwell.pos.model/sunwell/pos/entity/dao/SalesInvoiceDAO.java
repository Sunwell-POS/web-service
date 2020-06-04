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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sunwell.pos.entity.PaymentMethod;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.SalesPayment;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;

/**
 *
 * @author Benny
 */
@Dependent
public class SalesInvoiceDAO 
{
    @PersistenceContext
    EntityManager em;
    
    public List<SalesInvoice> findByTenant(Tenant _tenant) {
        TypedQuery<SalesInvoice> query = em.createNamedQuery ("SalesInvoice.findByTenant", SalesInvoice.class);
        query.setParameter ("tenant", _tenant);
        List<SalesInvoice> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
    
    public List<SalesInvoice> find(Tenant _tenant, Map<String, Object> _params) {
//        TypedQuery<SalesInvoice> query = em.createNamedQuery ("SalesInvoice.findByTenant", SalesInvoice.class);
//        query.setParameter ("tenant", _tenant);
//        List<SalesInvoice> resultList = query.getResultList ();
//        if(resultList != null && resultList.size () > 0) 
//            return resultList;
            String jpql = "SELECT S FROM SalesInvoice S WHERE S.systemId IS NOT null";
            Map<String, Object> params = new HashMap<>();
                       
            if(_params != null && (!_params.isEmpty ())) {
                Object paid = _params.get ("paid") ;
                if(paid != null) {
                    System.out.println ("NOt NULL PAID: " + paid);
                    jpql += " AND S.paid = :paid" ;
                    params.put ("paid", paid);
//                    query.setParameter ("paid", paid);
                }
            }
            
            TypedQuery<SalesInvoice> query = em.createQuery (jpql, SalesInvoice.class);
            for (String key : params.keySet ()) {
                query.setParameter (key, params.get (key));
            }
//            query.setParameter ("tenant", _tenant);
            List<SalesInvoice> resultList = query.getResultList ();
            if(resultList != null && resultList.size () > 0) 
                return resultList;

            return null;
    }
    
//    public List<SalesPayment> findSalesPaymentByTenant(Tenant _tenant) {
//        TypedQuery<PaymentMethod> query = em.createNamedQuery ("SalesPayment.findByTenant", PaymentMethod.class);
//        query.setParameter ("tenant", _tenant);
//        List<PaymentMethod> resultList = query.getResultList ();
//        if(resultList != null && resultList.size () > 0) 
//            return resultList;
//        
//        return null;
//    }
//    
//    public List<PaymentMethod> findPaymentMethodByTenant(Tenant _tenant) {
//        TypedQuery<PaymentMethod> query = em.createNamedQuery ("PaymentMethod.findByTenant", PaymentMethod.class);
//        query.setParameter ("tenant", _tenant);
//        List<PaymentMethod> resultList = query.getResultList ();
//        if(resultList != null && resultList.size () > 0) 
//            return resultList;
//        return null;
//    }
}
