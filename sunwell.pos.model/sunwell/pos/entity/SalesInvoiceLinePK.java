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
public class SalesInvoiceLinePK implements Serializable 
{

    private String parent;
    private String product;

    public SalesInvoiceLinePK ()
    {
    }

    public SalesInvoiceLinePK (String customerId, String tenantId)
    {
        this.parent = customerId;
        this.product = tenantId;
    }

    public String getParent ()
    {
        return parent;
    }

    public void setParent (String customerId)
    {
        this.parent = customerId;
    }

    public String getProduct ()
    {
        return product;
    }

    public void setProduct (String tenantId)
    {
        this.product = tenantId;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (parent != null ? parent.hashCode () : 0);
        hash += (product != null ? product.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesInvoiceLinePK)) {
            return false;
        }
        SalesInvoiceLinePK other = (SalesInvoiceLinePK) object;
        if ((this.parent == null && other.parent != null) || (this.parent != null && !this.parent.equals (other.parent)))
            return false;
        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals (other.product)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.TenantCustomerSettingsPK[ customerId=" + parent + ", tenantId=" + product + " ]";
    }

}
