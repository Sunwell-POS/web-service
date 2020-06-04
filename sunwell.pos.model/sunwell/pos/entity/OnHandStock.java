/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * OnHandStocks.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benny
 */
@Entity 
@IdClass(OnHandStockPK.class)
@Table(name = "on_hand_stocks")
@NamedQueries({
    @NamedQuery(name = "OnHandStock.findAllByTenant", query = "SELECT o FROM OnHandStock o WHERE o.product.tenant = :tenant")
    , @NamedQuery(name = "OnHandStock.findByProduct", query = "SELECT o FROM OnHandStock o WHERE o.product.tenant = :tenant "
            + "AND o.product = :product")
    , @NamedQuery(name = "OnHandStock.findByWarehouse", query = "SELECT o FROM OnHandStock o WHERE o.product.tenant = :tenant "
            + "AND o.warehouse = :warehouse")
    , @NamedQuery(name = "OnHandStock.findByProductAndWarehouse", query = "SELECT o FROM OnHandStock o WHERE o.product.tenant = :tenant "
            + " AND o.product = :product AND o.warehouse = :warehouse")
    , @NamedQuery(name = "OnHandStock.findByQty", query = "SELECT o FROM OnHandStock o WHERE o.qty = :qty")
    , @NamedQuery(name = "OnHandStock.findByCreatedAt", query = "SELECT o FROM OnHandStock o WHERE o.createdAt = :createdAt")
    , @NamedQuery(name = "OnHandStock.findByUpdatedAt", query = "SELECT o FROM OnHandStock o WHERE o.updatedAt = :updatedAt")
    , @NamedQuery(name = "OnHandStock.getQtyByProduct", query = "SELECT SUM(o.qty) FROM OnHandStock o WHERE o.product.tenant = :tenant "
            + "AND o.product = :product  ")   
    , @NamedQuery(name = "OnHandStock.getQtyByProductAndWarehouse", query = "SELECT SUM(o.qty) FROM OnHandStock o WHERE o.product.tenant = :tenant "
            + " AND o.product = :product AND o.warehouse = :warehouse")
    , @NamedQuery(name = "OnHandStock.findBySysdeleted", query = "SELECT o FROM OnHandStock o WHERE o.sysdeleted = :sysdeleted")})
public class OnHandStock implements Serializable 
{
    @Id
    @ManyToOne()
    @JoinColumn(name = "productId")
    private Product product;
    @Id
    @ManyToOne()
    @JoinColumn(name = "warehouseId")
    private Warehouse warehouse;
    @Basic(optional = false)
    @Column(name = "qty")
    private double qty;
    @Column(name = "last_input_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastInputDate;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    
    @Basic(optional=false)
    @Column(name = "sysdeleted")
    private boolean sysdeleted = false;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User syscreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User syslastupdater;
    @JoinColumn(name = "tenantId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant tenant;

    /**
     * @return the product
     */
    public Product getProduct ()
    {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct (Product product)
    {
        this.product = product;
    }

//    /**
//     * @return the warehouseId
//     */
//    public Warehouse getWarehouseId ()
//    {
//        return warehouse;
//    }
//
//    /**
//     * @param warehouseId the warehouseId to set
//     */
//    public void setWarehouseId (Warehouse warehouse)
//    {
//        this.warehouse = warehouse;
//    }

    public OnHandStock ()
    {
    }

//    public OnHandStock (OnHandStockPK onHandStocksPK)
//    {
//        this.onHandStocksPK = onHandStocksPK;
//    }
//
//    public OnHandStock (OnHandStockPK onHandStocksPK, double qty, boolean sysdeleted)
//    {
//        this.onHandStocksPK = onHandStocksPK;
//        this.qty = qty;
//        this.sysdeleted = sysdeleted;
//    }
//
//    public OnHandStock (String productId, String warehouseId)
//    {
//        this.onHandStocksPK = new OnHandStockPK (productId, warehouseId);
//    }
//
//    public OnHandStockPK getOnHandStocksPK ()
//    {
//        return onHandStocksPK;
//    }
//
//    public void setOnHandStocksPK (OnHandStockPK onHandStocksPK)
//    {
//        this.onHandStocksPK = onHandStocksPK;
//    }

    public double getQty ()
    {
        return qty;
    }

    public void setQty (double qty)
    {
        this.qty = qty;
    }

    public Date getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt ()
    {
        return updatedAt;
    }

    public void setUpdatedAt (Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public boolean getSysdeleted ()
    {
        return sysdeleted;
    }

    public void setSysdeleted (boolean sysdeleted)
    {
        this.sysdeleted = sysdeleted;
    }

//    public Product getProducts ()
//    {
//        return product;
//    }
//
//    public void setProducts (Product product)
//    {
//        this.product = product;
//    }

    public User getSyscreator ()
    {
        return syscreator;
    }

    public void setSyscreator (User syscreator)
    {
        this.syscreator = syscreator;
    }

    public User getSyslastupdater ()
    {
        return syslastupdater;
    }

    public void setSyslastupdater (User syslastupdater)
    {
        this.syslastupdater = syslastupdater;
    }

    public Tenant getTenant ()
    {
        return tenant;
    }

    public void setTenant (Tenant tenantId)
    {
        this.tenant = tenantId;
    }

    public Warehouse getWarehouse ()
    {
        return warehouse;
    }

    public void setWarehouse (Warehouse warehouse)
    {
        this.warehouse = warehouse;
    }

    /**
     * @return the lastInputDate
     */
    public Date getLastInputDate ()
    {
        return lastInputDate;
    }

    /**
     * @param lastInputedDate the lastInputDate to set
     */
    public void setLastInputDate (Date lastInputedDate)
    {
        this.lastInputDate = lastInputedDate;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (product != null ? product.hashCode () : 0);
        hash += (warehouse != null ? product.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OnHandStock)) {
            return false;
        }
        OnHandStock other = (OnHandStock) object;
        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals (other.product)))
            return false;
        if ((this.warehouse == null && other.warehouse != null) || (this.warehouse != null && !this.warehouse.equals (other.warehouse)))
            return false;        

        return true;
    }

//    @Override
//    public String toString ()
//    {
//        return "sunwell.pos.entity.OnHandStocks[ onHandStocksPK=" + onHandStocksPK + " ]";
//    }

}
