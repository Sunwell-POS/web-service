/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * TenantCustomerSettingsPK.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Benny
 */
public class TenantCustomerSettingPK implements Serializable 
{

    private String customer;
    private String tenant;

    public TenantCustomerSettingPK ()
    {
    }

    public TenantCustomerSettingPK (String customerId, String tenantId)
    {
        this.customer = customerId;
        this.tenant = tenantId;
    }

    public String getCustomer ()
    {
        return customer;
    }

    public void setCustomer (String customerId)
    {
        this.customer = customerId;
    }

    public String getTenant ()
    {
        return tenant;
    }

    public void setTenant (String tenantId)
    {
        this.tenant = tenantId;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (customer != null ? customer.hashCode () : 0);
        hash += (tenant != null ? tenant.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenantCustomerSettingPK)) {
            return false;
        }
        TenantCustomerSettingPK other = (TenantCustomerSettingPK) object;
        if ((this.customer == null && other.customer != null) || (this.customer != null && !this.customer.equals (other.customer)))
            return false;
        if ((this.tenant == null && other.tenant != null) || (this.tenant != null && !this.tenant.equals (other.tenant)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.TenantCustomerSettingsPK[ customerId=" + customer + ", tenantId=" + tenant + " ]";
    }

}
