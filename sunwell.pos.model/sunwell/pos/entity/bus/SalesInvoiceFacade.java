/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantFacade.java
 *
 * Created on Oct 17, 2017, 10:32:12 AM
 */

package sunwell.pos.entity.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.dao.ProdCategoryDAO;
import sunwell.pos.entity.dao.ProductDAO;
import sunwell.pos.entity.dao.SalesInvoiceDAO;
import sunwell.pos.entity.dao.TenantDAO;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SalesInvoiceFacade 
{
    @Inject
    SalesInvoiceDAO siDAO;
    
    public List<SalesInvoice> findByTenant(Tenant _tenant) {
        return siDAO.findByTenant (_tenant);
    }
    
    public List<SalesInvoice> find(Tenant _tenant, Map<String, Object> _params) {
        return siDAO.find (_tenant, _params);
    }
    
    public List<SalesInvoice> findAllUnpaidInvoices(Tenant _tenant) {
        Map<String, Object> params = new HashMap<> ();
        params.put ("paid", false);
        return siDAO.find (_tenant, params);
    }
    
    public List<SalesInvoice> findAllPaidInvoices(Tenant _tenant) {
        Map<String, Object> params = new HashMap<> ();
        params.put ("paid", true);
        return siDAO.find (_tenant, params);
    }
}
