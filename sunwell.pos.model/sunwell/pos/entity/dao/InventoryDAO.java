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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.OutcomingGood;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;
import sunwell.pos.entity.UserGroup;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.Warehouse;
/**
 *
 * @author Benny
 */
@Dependent
public class InventoryDAO implements Serializable
{
    @PersistenceContext
    EntityManager em;
    
    @Transactional(Transactional.TxType.REQUIRED)
    public OnHandStock create (OnHandStock _oh)
    {
        em.persist (_oh);
        return _oh;
    }
    
    public OnHandStock findByProductAndWarehouse(Tenant _t, Product _prod, Warehouse _warehouse) {
        try {
            TypedQuery<OnHandStock> query = em.createNamedQuery ("OnHandStock.findByProductAndWarehouse", OnHandStock.class);
            query.setParameter ("tenant", _t);
            query.setParameter ("product", _prod);
            query.setParameter ("warehouse", _warehouse);
            return query.getSingleResult ();
        }
        catch(NoResultException _e) {
            return null;
        }
    }
    
    public List<OnHandStock> findByProduct(Tenant _t, Product _prod) {
        TypedQuery<OnHandStock> query = em.createNamedQuery ("OnHandStock.findByProduct", OnHandStock.class);
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        List<OnHandStock> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
    
    public List<IncomingGood> findIncomingGoodWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate) {
        System.out.println ("INCOMING GOOD, start date: " + _startDate.toString () + " end date: " + _endDate.toString ());
        TypedQuery<IncomingGood> query = em.createNamedQuery ("IncomingGood.findWithinDate", IncomingGood.class);
//        Calendar cal = Calendar.getInstance ();
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        query.setParameter ("warehouse", _w);
        query.setParameter ("startDate", _startDate, TemporalType.TIMESTAMP);
        query.setParameter ("endDate", _endDate, TemporalType.TIMESTAMP);
        
        List<IncomingGood> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
    
    public List<OutcomingGood> findOutcomingGoodWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate) {
        System.out.println ("OUTCOMING GOOD, start date: " + _startDate.toString () + " end date: " + _endDate.toString ());
        TypedQuery<OutcomingGood> query = em.createNamedQuery ("OutcomingGood.findWithinDate", OutcomingGood.class);
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        query.setParameter ("warehouse", _w);
        query.setParameter ("startDate", _startDate, TemporalType.TIMESTAMP);
        query.setParameter ("endDate", _endDate, TemporalType.TIMESTAMP);
        
        List<OutcomingGood> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
    
    public double sumIncomingQtyBeforeDate(Tenant _t, Product _prod, Warehouse _w, Date _incDate) {
        Query query = em.createNamedQuery ("IncomingGood.sumQtyBeforeDate");
        
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        query.setParameter ("warehouse", _w);
        query.setParameter ("incomingDate", _incDate, TemporalType.TIMESTAMP);
        Double result = (Double)query.getSingleResult ();
        System.out.println ("RESULT: " + result);        
        return result != null ? result : 0;
    }
    
    public double sumIncomingQtyWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate ) {
        Query query = em.createNamedQuery ("IncomingGood.sumWithinDate");
        
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        query.setParameter ("warehouse", _w);
        query.setParameter ("startDate", _startDate, TemporalType.TIMESTAMP);
        query.setParameter ("endDate", _endDate, TemporalType.TIMESTAMP);
        Double result = (Double)query.getSingleResult ();
        return result != null ? result : 0;
    }
    
    public double sumOutcomingQtyBeforeDate(Tenant _t, Product _prod, Warehouse _w, Date _outDate) {
        Query query = em.createNamedQuery ("OutcomingGood.sumQtyBeforeDate");
        
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        query.setParameter ("warehouse", _w);
        query.setParameter ("outcomingDate", _outDate, TemporalType.TIMESTAMP);
        Double result = (Double)query.getSingleResult ();  
        return result != null ? result : 0;
    }
    
    public double sumOutcomingQtyWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate ) {
        Query query = em.createNamedQuery ("OutcomingGood.sumWithinDate");
        
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        query.setParameter ("warehouse", _w);
        query.setParameter ("startDate", _startDate, TemporalType.TIMESTAMP);
        query.setParameter ("endDate", _endDate, TemporalType.TIMESTAMP);
        Double result = (Double)query.getSingleResult ();
        return result != null ? result : 0;
    }
    
    public List<OnHandStock> findByWarehouse(Tenant _t, Warehouse _warehouse) {
        TypedQuery<OnHandStock> query = em.createNamedQuery ("OnHandStock.findByWarehouse", OnHandStock.class);
        query.setParameter ("tenant", _t);
        query.setParameter ("warehouse", _warehouse);
        List<OnHandStock> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
        
    public List<OnHandStock> findAllOnHandByTenant(Tenant _tenant) {
        TypedQuery<OnHandStock> query = em.createNamedQuery ("OnHandStock.findAllByTenant", OnHandStock.class);
        query.setParameter ("tenant", _tenant);
        List<OnHandStock> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
    
    public Double getQtyByProduct(Tenant _t, Product _prod) {
        Query query = em.createNamedQuery ("OnHandStock.getQtyByProduct");
            query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        Double result = (Double)query.getSingleResult ();        
        return result;
    }
    
    public Double getQtyByProductAndWarehouse(Tenant _t, Product _prod, Warehouse _warehouse) {
        Query query = em.createNamedQuery ("OnHandStock.getQtyByProductAndWarehouse");
        query.setParameter ("tenant", _t);
        query.setParameter ("product", _prod);
        query.setParameter ("warehouse", _warehouse);
        Double result = (Double)query.getSingleResult ();        
        return result;
    } 
    
    public List<Warehouse> findAllWarehouseByTenant(Tenant _tenant) {
        TypedQuery<Warehouse> query = em.createNamedQuery ("Warehouse.findAllByTenant", Warehouse.class);
        query.setParameter ("tenant", _tenant);
        List<Warehouse> resultList = query.getResultList ();
        if(resultList != null && resultList.size () > 0) 
            return resultList;
        
        return null;
    }
    
    public List<OnHandStock> find(Tenant _tenant, Map<String, Object> _params) {
//        TypedQuery<SalesInvoice> query = em.createNamedQuery ("SalesInvoice.findByTenant", SalesInvoice.class);
//        query.setParameter ("tenant", _tenant);
//        List<SalesInvoice> resultList = query.getResultList ();
//        if(resultList != null && resultList.size () > 0) 
//            return resultList;
            String jpql = "SELECT O FROM OnHandStock O WHERE O.tenant = :tenant";
            Map<String, Object> params = new HashMap<>();
            params.put ("tenant", _tenant);
            if(_params != null && (!_params.isEmpty ())) {
                Product prod = (Product)_params.get ("product") ;
                if(prod != null) {
                    jpql += " AND O.product = :prod" ;
                    params.put ("prod", prod);
                }
                
                Warehouse wr = (Warehouse)_params.get ("warehouse") ;
                if(wr != null) {
                    jpql += " AND O.warehouse = :wr" ;
                    params.put ("wr", wr);
                }
            }
            
            TypedQuery<OnHandStock> query = em.createQuery (jpql, OnHandStock.class);
            for (String key : params.keySet ()) {
                query.setParameter (key, params.get (key));
            }
//            query.setParameter ("tenant", _tenant);
            List<OnHandStock> resultList = query.getResultList ();
            if(resultList != null && resultList.size () > 0) 
                return resultList;

            return null;
    }
}
