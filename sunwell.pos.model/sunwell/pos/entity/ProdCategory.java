/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategory.java
 *
 * Created on Oct 13, 2017, 3:43:02 PM
 */

package sunwell.pos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity 
@Table(name = "prod_categories")
@UuidGenerator(name="UUID_category")
@NamedQueries({
    @NamedQuery(name = "ProdCategory.findAll", query = "SELECT p FROM ProdCategory p")
    , @NamedQuery(name = "ProdCategory.findBySystemId", query = "SELECT p FROM ProdCategory p WHERE p.systemId = :systemId")
    , @NamedQuery(name = "ProdCategory.findByName", query = "SELECT p FROM ProdCategory p WHERE p.name = :name")
    , @NamedQuery(name = "ProdCategory.findByDefault1", query = "SELECT p FROM ProdCategory p WHERE p.default1 = :default1")
    , @NamedQuery(name = "ProdCategory.findByTenant", query = "SELECT p FROM ProdCategory p WHERE p.recOwner = :tenant")
    , @NamedQuery(name = "ProdCategory.findByBgColor", query = "SELECT p FROM ProdCategory p WHERE p.bgColor = :bgColor")
    , @NamedQuery(name = "ProdCategory.findByCreatedAt", query = "SELECT p FROM ProdCategory p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "ProdCategory.findByUpdatedAt", query = "SELECT p FROM ProdCategory p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "ProdCategory.findBySysDeleted", query = "SELECT p FROM ProdCategory p WHERE p.sysDeleted = :sysDeleted")})
public class ProdCategory implements Serializable 
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator="UUID_tenant")
    @Column(name = "systemId")
    private String systemId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "default1")
    private boolean default1 ;
    @Column(name = "bgColor")
    private String bgColor;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "sysDeleted")
    private boolean sysDeleted;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodCategories")
//    private List<ProdMultiCategories> prodMultiCategoriesList;
    @JoinColumn(name = "recOwner", referencedColumnName = "systemId")
    @ManyToOne(optional = false)
    private Tenant recOwner;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;

    public ProdCategory ()
    {
    }

    public ProdCategory (String systemId)
    {
        this.systemId = systemId;
    }

    public ProdCategory (String systemId, String name, boolean default1, boolean sysDeleted)
    {
        this.systemId = systemId;
        this.name = name;
        this.default1 = default1;
        this.sysDeleted = sysDeleted;
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

    public boolean getDefault1 ()
    {
        return default1;
    }

    public void setDefault1 (boolean default1)
    {
        this.default1 = default1;
    }

    public String getBgColor ()
    {
        return bgColor;
    }

    public void setBgColor (String bgColor)
    {
        this.bgColor = bgColor;
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

    public void setSysDeleted (boolean sysDeleted)
    {
        this.sysDeleted = sysDeleted;
    }

//    public List<ProdMultiCategories> getProdMultiCategoriesList ()
//    {
//        return prodMultiCategoriesList;
//    }
//
//    public void setProdMultiCategoriesList (List<ProdMultiCategories> prodMultiCategoriesList)
//    {
//        this.prodMultiCategoriesList = prodMultiCategoriesList;
//    }

    public Tenant getRecOwner ()
    {
        return recOwner;
    }

    public void setRecOwner (Tenant recOwner)
    {
        this.recOwner = recOwner;
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
        if (!(object instanceof ProdCategory)) {
            return false;
        }
        ProdCategory other = (ProdCategory) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.ProdCategory[ systemId=" + systemId + " ]";
    }

}
