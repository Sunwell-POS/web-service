/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Customer.java
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
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.UuidGenerator;

/**
 *
 * @author Benny
 */
@Entity 
@UuidGenerator(name="UUID_customer")
@Table(name = "customers")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
    , @NamedQuery(name = "Customer.findBySystemId", query = "SELECT c FROM Customer c WHERE c.systemId = :systemId")
    , @NamedQuery(name = "Customer.findByTenant", 
            query = "SELECT c FROM Customer c WHERE c IN (SELECT T.customer FROM TenantCustomerSetting T WHERE T.tenant = :tenant)")
    , @NamedQuery(name = "Customer.findByName", query = "SELECT c FROM Customer c WHERE c.name = :name")
    , @NamedQuery(name = "Customer.findByIsMale", query = "SELECT c FROM Customer c WHERE c.isMale = :isMale")
    , @NamedQuery(name = "Customer.findByBirthdate", query = "SELECT c FROM Customer c WHERE c.birthdate = :birthdate")
    , @NamedQuery(name = "Customer.findByMemberNo", query = "SELECT c FROM Customer c WHERE c.memberNo = :memberNo")
    , @NamedQuery(name = "Customer.findByAddress", query = "SELECT c FROM Customer c WHERE c.address = :address")
    , @NamedQuery(name = "Customer.findByProvince", query = "SELECT c FROM Customer c WHERE c.province = :province")
    , @NamedQuery(name = "Customer.findByNation", query = "SELECT c FROM Customer c WHERE c.nation = :nation")
    , @NamedQuery(name = "Customer.findByPhone", query = "SELECT c FROM Customer c WHERE c.phone = :phone")
    , @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Customer c WHERE c.email = :email")
    , @NamedQuery(name = "Customer.findByDisc", query = "SELECT c FROM Customer c WHERE c.disc = :disc")
    , @NamedQuery(name = "Customer.findByMemo", query = "SELECT c FROM Customer c WHERE c.memo = :memo")
    , @NamedQuery(name = "Customer.findBySysbuiltin", query = "SELECT c FROM Customer c WHERE c.sysbuiltin = :sysbuiltin")
    , @NamedQuery(name = "Customer.findByCreatedAt", query = "SELECT c FROM Customer c WHERE c.createdAt = :createdAt")
    , @NamedQuery(name = "Customer.findByUpdatedAt", query = "SELECT c FROM Customer c WHERE c.updatedAt = :updatedAt")
    , @NamedQuery(name = "Customer.findBySysDeleted", query = "SELECT c FROM Customer c WHERE c.sysDeleted = :sysdeleted")})
public class Customer implements Serializable 
{

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "systemId")
    @GeneratedValue(generator="UUID_tenant")
    private String systemId;
    
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "isMale")
    private Boolean isMale;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Column(name = "memberNo")
    private String memberNo;
    @Column(name = "address")
    private String address;
    @Column(name = "province")
    private String province;
    @Column(name = "nation")
    private String nation;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "disc")
    private Double disc;
    @Column(name = "memo")
    private String memo;
    @Basic(optional = false)
    @Column(name = "sysbuiltin")
    private Boolean sysbuiltin = true;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private Boolean sysDeleted = false ;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customers")
//    private List<TenantCustomerSettings> tenantCustomerSettingsList;
//    @OneToMany(mappedBy = "customerId")
//    private List<SalesInvoices> salesInvoicesList;
    @JoinColumn(name = "syscreator", referencedColumnName = "systemId")
    @ManyToOne
    private User sysCreator;
    @JoinColumn(name = "syslastupdater", referencedColumnName = "systemId")
    @ManyToOne
    private User sysLastUpdater;

    public Customer ()
    {
    }

    public Customer (String systemId)
    {
        this.systemId = systemId;
    }

    public Customer (String systemId, String name, Boolean sysbuiltin, Boolean sysdeleted)
    {
        this.systemId = systemId;
        this.name = name;
        this.sysbuiltin = sysbuiltin;
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

    public Boolean getIsMale ()
    {
        return isMale;
    }

    public void setIsMale (Boolean isMale)
    {
        this.isMale = isMale;
    }

    public Date getBirthdate ()
    {
        return birthdate;
    }

    public void setBirthdate (Date birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getMemberNo ()
    {
        return memberNo;
    }

    public void setMemberNo (String memberNo)
    {
        this.memberNo = memberNo;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getProvince ()
    {
        return province;
    }

    public void setProvince (String province)
    {
        this.province = province;
    }

    public String getNation ()
    {
        return nation;
    }

    public void setNation (String nation)
    {
        this.nation = nation;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public Double getDisc ()
    {
        return disc;
    }

    public void setDisc (Double disc)
    {
        this.disc = disc;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    public Boolean getSysbuiltin ()
    {
        return sysbuiltin;
    }

    public void setSysbuiltin (Boolean sysbuiltin)
    {
        this.sysbuiltin = sysbuiltin;
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

    public Boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (Boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

//    public List<TenantCustomerSettings> getTenantCustomerSettingsList ()
//    {
//        return tenantCustomerSettingsList;
//    }
//
//    public void setTenantCustomerSettingsList (List<TenantCustomerSettings> tenantCustomerSettingsList)
//    {
//        this.tenantCustomerSettingsList = tenantCustomerSettingsList;
//    }
//
//    public List<SalesInvoices> getSalesInvoicesList ()
//    {
//        return salesInvoicesList;
//    }
//
//    public void setSalesInvoicesList (List<SalesInvoices> salesInvoicesList)
//    {
//        this.salesInvoicesList = salesInvoicesList;
//    }

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
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.Customer[ systemId=" + systemId + " ]";
    }

}
