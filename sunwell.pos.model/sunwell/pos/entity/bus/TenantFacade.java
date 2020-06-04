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

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.dao.TenantDAO;

/**
 *
 * @author Benny
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TenantFacade 
{
    @Inject
    TenantDAO tenantDAO;
    
    public Tenant findByEmail(String _email) {
        return tenantDAO.findByEmail (_email);
    }
}
