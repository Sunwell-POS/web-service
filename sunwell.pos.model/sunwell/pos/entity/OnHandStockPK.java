/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * OnHandStocksPK.java
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
public class OnHandStockPK implements Serializable 
{

    private String product;
    private String warehouse;

    public OnHandStockPK ()
    {
    }

    public OnHandStockPK (String productId, String warehouseId)
    {
        this.product = productId;
        this.warehouse = warehouseId;
    }

    public String getProduct ()
    {
        return product;
    }

    public void setProduct (String productId)
    {
        this.product = productId;
    }

    public String getWarehouse ()
    {
        return warehouse;
    }

    public void setWarehouse (String warehouseId)
    {
        this.warehouse = warehouseId;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (product != null ? product.hashCode () : 0);
        hash += (warehouse != null ? warehouse.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OnHandStockPK)) {
            return false;
        }
        OnHandStockPK other = (OnHandStockPK) object;
        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals (other.product)))
            return false;
        if ((this.warehouse == null && other.warehouse != null) || (this.warehouse != null && !this.warehouse.equals (other.warehouse)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.OnHandStocksPK[ productId=" + product + ", warehouseId=" + warehouse + " ]";
    }

}
