/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * UserCredFacade.java
 *
 * Created on Oct 16, 2017, 9:59:37 AM
 */

package sunwell.pos.entity.bus;

import aegwyn.core.web.util.Util;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.OutcomingGood;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;
import sunwell.pos.entity.UserGroup;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.Warehouse;
import sunwell.pos.entity.dao.CredDAO;
import sunwell.pos.entity.dao.GenericDAO;
import sunwell.pos.entity.dao.InventoryDAO;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InventoryFacade 
{    
    @Inject
    InventoryDAO stockDAO;
    
    @Inject
    GenericFacade genericFacade;
    
    public List<OnHandStock> find(Tenant _tenant, Map<String, Object> _params) {
        return stockDAO.find (_tenant, _params);
    }

    
    public List<OnHandStock> findAllByTenant(Tenant _t) {
        return stockDAO.findAllOnHandByTenant (_t);
    }
        
    public OnHandStock findByProductAndWarehouse(Tenant _t, Product _prod, Warehouse _warehouse) {
        return stockDAO.findByProductAndWarehouse (_t, _prod, _warehouse);
    }
    
    public List<OnHandStock> findByProduct(Tenant _t, Product _prod) {
        return stockDAO.findByProduct (_t, _prod);
    }
    
    public List<OnHandStock> findByWarehouse(Tenant _t, Warehouse _warehouse) {
        return stockDAO.findByWarehouse (_t, _warehouse);
    }
    
    public Double getQtyByProduct(Tenant _t, Product _prod) {
        return stockDAO.getQtyByProduct (_t, _prod);
    }
    
    public Double getQtyByProductAndWarehouse(Tenant _t, Product _prod, Warehouse _warehouse) {
        return stockDAO.getQtyByProductAndWarehouse (_t, _prod, _warehouse);
    }
    
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public OnHandStock addOnHand(Incoming _oh) {
//        OnHandStock oh = stockDAO.findByProductAndWarehouse (_oh.getProduct (), _oh.getWarehouse ());
//        if(oh == null)
//           oh = stockDAO.create (_oh);
//        else
//            oh.setQty (oh.getQty () + _oh.getQty ());
//        
//        return oh;
//    } 
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public OnHandStock addOnHand(OnHandStock _oh) {
        OnHandStock oh = stockDAO.findByProductAndWarehouse (_oh.getTenant (), _oh.getProduct (), _oh.getWarehouse ());
        if(oh == null)
           oh = stockDAO.create (_oh);
        else {
            oh.setQty (oh.getQty () + _oh.getQty ());
            oh.setLastInputDate (_oh.getLastInputDate ());
        }
        
        return oh;
    } 
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public OnHandStock addOnHand(OnHandStock _oh, double _price, String _memo) {
        System.out.println ("ADDING OH");
        OnHandStock oh = stockDAO.findByProductAndWarehouse (_oh.getTenant (), _oh.getProduct (), _oh.getWarehouse ());
        if(oh == null)
           oh = stockDAO.create (_oh);
        else {
            oh.setQty (oh.getQty () + _oh.getQty ());
            oh.setLastInputDate (_oh.getLastInputDate ());
        }
        
        IncomingGood ic = new IncomingGood ();
        ic.setProduct (_oh.getProduct ());
        ic.setWarehouse (_oh.getWarehouse ());
        ic.setRefType (IncomingGood.REF_REGISTER_ITEM);
        ic.setQty (_oh.getQty ());
        ic.setMemo (_memo);
        ic.setUnitPrice (_price);
        ic.setIncomingDate (_oh.getLastInputDate ());
        genericFacade.create (ic);
        
        System.out.println ("FINISHED ADDING ON HAND");
        
        return oh;
    } 
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public IncomingGood addOnHandForIncomingGood(OnHandStock _oh, double _price, String _memo) {
        System.out.println ("ADDING OH");
        OnHandStock oh = stockDAO.findByProductAndWarehouse (_oh.getTenant (), _oh.getProduct (), _oh.getWarehouse ());
        if(oh == null)
           oh = stockDAO.create (_oh);
        else {
            oh.setQty (oh.getQty () + _oh.getQty ());
            oh.setLastInputDate (_oh.getLastInputDate ());
        }
        
        IncomingGood ic = new IncomingGood ();
        ic.setProduct (_oh.getProduct ());
        ic.setWarehouse (_oh.getWarehouse ());
        ic.setRefType (IncomingGood.REF_REGISTER_ITEM);
        ic.setQty (_oh.getQty ());
        ic.setMemo (_memo);
        ic.setUnitPrice (_price);
        ic.setIncomingDate (_oh.getLastInputDate ());
        genericFacade.create (ic);
        
        genericFacade.refresh (ic);
        
        System.out.println ("FINISHED ADDING ON HAND");
        
        return ic;
    } 
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public OnHandStock removeOnHand(OnHandStock _oh, String _refId) {
        if(_oh == null)
            return null;
        
        OnHandStock oh = stockDAO.findByProductAndWarehouse (_oh.getTenant (), _oh.getProduct (), _oh.getWarehouse ());
        if(oh == null || oh.getQty () < _oh.getQty ())
           return null;
        else {
            if(oh.getQty () == _oh.getQty ()) {
                genericFacade.remove (oh);
            }
            else {
                oh.setQty (oh.getQty () - _oh.getQty ());
                oh.setLastInputDate (_oh.getLastInputDate ());
            }
            
            OutcomingGood oc = new OutcomingGood ();
            oc.setProduct (_oh.getProduct ());
            oc.setWarehouse (_oh.getWarehouse ());
            oc.setRefType (OutcomingGood.REF_SALES);
            oc.setRefId (_refId);
            oc.setQty (_oh.getQty ());
            oc.setOutcomingDate (_oh.getLastInputDate ());
            genericFacade.create (oc);
            
            return oh;
        }        
    } 
    
    public List<Warehouse> findAllWarehouseByTenant(Tenant _t) {
        return stockDAO.findAllWarehouseByTenant (_t);
    }
    
    public List<IncomingGood> findIncomingGoodWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate, boolean _withprevSum) {
        Calendar cal = Calendar.getInstance ();
        cal.setTime (_endDate);
        cal.set (Calendar.HOUR_OF_DAY, 23);
        cal.set (Calendar.MINUTE, 59);
        cal.set (Calendar.SECOND, 59);
//        cal.add (Calendar.DATE, 1);
        
        List<IncomingGood> listGoods = stockDAO.findIncomingGoodWithinDate (_t, _prod, _w, _startDate, cal.getTime ());
        if(_withprevSum) {
            System.out.println ("DAO: " + stockDAO);
            double qty = stockDAO.sumIncomingQtyBeforeDate (_t, _prod, _w, _startDate);
            cal.setTime (_startDate);
            cal.add (Calendar.DATE, -1);
            IncomingGood icg = new IncomingGood ();
            icg.setIncomingDate (cal.getTime ());
            icg.setQty (qty);
            if(listGoods == null)
                listGoods = new LinkedList<>();
            listGoods.add (0, icg);
        }
        return listGoods;
    }
    
    public List<OutcomingGood> findOutcomingGoodWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate, boolean _withprevSum) {
        Calendar cal = Calendar.getInstance ();
        cal.setTime (_endDate);
        cal.set (Calendar.HOUR_OF_DAY, 23);
        cal.set (Calendar.MINUTE, 59);
        cal.set (Calendar.SECOND, 59);
//        cal.add (Calendar.DATE, 1);
        
        List<OutcomingGood> listGoods = stockDAO.findOutcomingGoodWithinDate (_t, _prod, _w, _startDate, cal.getTime ());
        if(_withprevSum) {
            System.out.println ("DAO: " + stockDAO);
            double qty = stockDAO.sumOutcomingQtyBeforeDate (_t, _prod, _w, _startDate);
            cal.setTime (_startDate);
            cal.add (Calendar.DATE, -1);
            OutcomingGood ocg = new OutcomingGood ();
            ocg.setOutcomingDate (cal.getTime ());
            ocg.setQty (qty);
            if(listGoods == null)
                listGoods = new LinkedList<>();
            listGoods.add (0, ocg);
        }
        
        return listGoods;
    }
    
    public double sumIncomingQtyBeforeDate(Tenant _t, Product _prod, Warehouse _w, Date _incDate) {
        return stockDAO.sumIncomingQtyBeforeDate (_t, _prod, _w, _incDate);
    }
    
    public double sumIncomingQtyWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate ) {
        Calendar cal = Calendar.getInstance ();
        cal.setTime (_endDate);
        cal.set (Calendar.HOUR_OF_DAY, 23);
        cal.set (Calendar.MINUTE, 59);
        cal.set (Calendar.SECOND, 59);
//        cal.add (Calendar.DATE, 1);
        
        return stockDAO.sumIncomingQtyWithinDate (_t, _prod, _w, _startDate, cal.getTime ());
    }
    
    public double sumOutcomingQtyBeforeDate(Tenant _t, Product _prod, Warehouse _w, Date _outDate) {
        return stockDAO.sumOutcomingQtyBeforeDate (_t, _prod, _w, _outDate);
    }
    
    public double sumOutcomingQtyWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate ) {
        Calendar cal = Calendar.getInstance ();
        cal.setTime (_endDate);
        cal.set (Calendar.HOUR_OF_DAY, 23);
        cal.set (Calendar.MINUTE, 59);
        cal.set (Calendar.SECOND, 59);
        
//        cal.add (Calendar.DATE, 1);
        
        return stockDAO.sumOutcomingQtyWithinDate (_t, _prod, _w, _startDate, cal.getTime ());
    }
    
    public double sumTotalBeforeDate(Tenant _t, Product _prod, Warehouse _w, Date _date ) {
        double inc = stockDAO.sumIncomingQtyBeforeDate (_t, _prod, _w, _date);
        double out = stockDAO.sumOutcomingQtyBeforeDate (_t, _prod, _w, _date);
        return inc - out ;
    }
    
    public double sumTotalWithinDate(Tenant _t, Product _prod, Warehouse _w, Date _startDate, Date _endDate ) {
//        Calendar cal = Calendar.getInstance ();
//        cal.setTime (_endDate);
//        cal.set (Calendar.HOUR_OF_DAY, 23);
//        cal.set (Calendar.MINUTE, 59);
//        cal.set (Calendar.SECOND, 59);
//        cal.add (Calendar.DATE, 1);
        
        double prev = sumTotalBeforeDate (_t, _prod, _w, _startDate);
        double in = stockDAO.sumIncomingQtyWithinDate (_t, _prod, _w, _startDate, _endDate);;
        double out = stockDAO.sumOutcomingQtyWithinDate (_t, _prod, _w, _startDate, _endDate);
        
        return prev + (in - out);
    }
}
