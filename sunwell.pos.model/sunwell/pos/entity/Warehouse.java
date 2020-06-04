/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Warehouse.java
 *
 * Created on Oct 13, 2017, 3:43:01 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "warehouses")
@NamedQueries({
    @NamedQuery(name = "Warehouse.findAllByTenant", query = "SELECT w FROM Warehouse w WHERE w.tenant = :tenant")
    , @NamedQuery(name = "Warehouse.findBySystemId", query = "SELECT w FROM Warehouse w WHERE w.systemId = :systemId")
    , @NamedQuery(name = "Warehouse.findByName", query = "SELECT w FROM Warehouse w WHERE w.name = :name")
    , @NamedQuery(name = "Warehouse.findByMemo", query = "SELECT w FROM Warehouse w WHERE w.memo = :memo")
    , @NamedQuery(name = "Warehouse.findByCreatedAt", query = "SELECT w FROM Warehouse w WHERE w.createdAt = :createdAt")
    , @NamedQuery(name = "Warehouse.findByUpdatedAt", query = "SELECT w FROM Warehouse w WHERE w.updatedAt = :updatedAt")
    , @NamedQuery(name = "Warehouse.findBySysDeleted", query = "SELECT w FROM Warehouse w WHERE w.sysDeleted = :sysDeleted")})
public class Warehouse implements Serializable 
{
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "memo")
    private String memo;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private boolean sysDeleted;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;
    @JoinColumn(name = "tenantId", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant tenant;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
//    private List<IncomingGood> incomingGoodsList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouses")
//    private List<OnHandStocks> onHandStocksList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouseId")
//    private List<OutcomingGood> outcomingGoodsList;

    public Warehouse ()
    {
    }

    public Warehouse (String systemId)
    {
        this.systemId = systemId;
    }

    public Warehouse (String systemId, String name, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.name = name;
        this.sysDeleted = sysdeleted;
    }

    public String getSystemId ()
    {
        return systemId;
    }

    public void setSystemId (String systemId)
    {
        this.systemId = systemId;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
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
        return sysDeleted;
    }

    public void setSysDeleted (boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

    public User getSysCreator ()
    {
        return sysCreator;
    }

    public void setSysCreator (User syscreator)
    {
        this.sysCreator = syscreator;
    }

    public User getSysLastUpdater ()
    {
        return sysLastUpdater;
    }

    public void setSysLastUpdater (User syslastupdater)
    {
        this.sysLastUpdater = syslastupdater;
    }

    public Tenant getTenant ()
    {
        return tenant;
    }

    public void setTenant (Tenant tenantId)
    {
        this.tenant = tenantId;
    }

//    public List<IncomingGood> getIncomingGoodsList ()
//    {
//        return incomingGoodsList;
//    }
//
//    public void setIncomingGoodsList (List<IncomingGood> incomingGoodsList)
//    {
//        this.incomingGoodsList = incomingGoodsList;
//    }
//
//    public List<OnHandStocks> getOnHandStocksList ()
//    {
//        return onHandStocksList;
//    }
//
//    public void setOnHandStocksList (List<OnHandStocks> onHandStocksList)
//    {
//        this.onHandStocksList = onHandStocksList;
//    }
//
//    public List<OutcomingGood> getOutcomingGoodsList ()
//    {
//        return outcomingGoodsList;
//    }
//
//    public void setOutcomingGoodsList (List<OutcomingGood> outcomingGoodsList)
//    {
//        this.outcomingGoodsList = outcomingGoodsList;
//    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (systemId != null ? systemId.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Warehouse)) {
            return false;
        }
        Warehouse other = (Warehouse) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.Warehouse[ systemId=" + systemId + " ]";
    }

}
