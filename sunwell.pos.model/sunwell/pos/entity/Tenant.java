/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Tenant.java
 *
 * Created on Oct 13, 2017, 3:43:00 PM
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
import javax.persistence.Lob;
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
@Table(name = "tenants")
@UuidGenerator(name="UUID_tenant")
@NamedQueries({
    @NamedQuery(name = "Tenant.findAll", query = "SELECT t FROM Tenant t")
    , @NamedQuery(name = "Tenant.findBySystemId", query = "SELECT t FROM Tenant t WHERE t.systemId = :systemId")
    , @NamedQuery(name = "Tenant.findByName", query = "SELECT t FROM Tenant t WHERE t.name = :name")
    , @NamedQuery(name = "Tenant.findByEmail", query = "SELECT t FROM Tenant t WHERE t.email = :email")
    , @NamedQuery(name = "Tenant.findByAddress", query = "SELECT t FROM Tenant t WHERE t.address = :address")
    , @NamedQuery(name = "Tenant.findByPhone", query = "SELECT t FROM Tenant t WHERE t.phone = :phone")
    , @NamedQuery(name = "Tenant.findByCity", query = "SELECT t FROM Tenant t WHERE t.city = :city")
    , @NamedQuery(name = "Tenant.findByCountry", query = "SELECT t FROM Tenant t WHERE t.country = :country")
    , @NamedQuery(name = "Tenant.findByProvince", query = "SELECT t FROM Tenant t WHERE t.province = :province")
    , @NamedQuery(name = "Tenant.findByImg", query = "SELECT t FROM Tenant t WHERE t.img = :img")
    , @NamedQuery(name = "Tenant.findByLogo", query = "SELECT t FROM Tenant t WHERE t.logo = :logo")
    , @NamedQuery(name = "Tenant.findByMemo", query = "SELECT t FROM Tenant t WHERE t.memo = :memo")
    , @NamedQuery(name = "Tenant.findByNews", query = "SELECT t FROM Tenant t WHERE t.news = :news")
    , @NamedQuery(name = "Tenant.findByCreatedAt", query = "SELECT t FROM Tenant t WHERE t.createdAt = :createdAt")
    , @NamedQuery(name = "Tenant.findByUpdatedAt", query = "SELECT t FROM Tenant t WHERE t.updatedAt = :updatedAt")
    , @NamedQuery(name = "Tenant.findBySysCreator", query = "SELECT t FROM Tenant t WHERE t.sysCreator = :sysCreator")
    , @NamedQuery(name = "Tenant.findBySysLastUpdater", query = "SELECT t FROM Tenant t WHERE t.sysLastUpdater = :sysLastUpdater")
    , @NamedQuery(name = "Tenant.findBySysDeleted", query = "SELECT t FROM Tenant t WHERE t.sysDeleted = :sysDeleted")})
@XmlRootElement
public class Tenant implements Serializable 
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
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "province")
    private String province;
    @Column(name = "img")
    private String img;
    @Column(name = "logo")
    private String logo;
    @Column(name = "memo")
    private String memo;
    @Column(name = "news")
    private String news;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "sysCreator")
    private String sysCreator;
    @Column(name = "sysLastUpdater")
    private String sysLastUpdater;
    @Basic(optional = false)
    @Column(name = "sysdeleted")
    private boolean sysDeleted;
    @JoinColumn(name = "tenant_categoryId", referencedColumnName = "systemId")
    @ManyToOne
    private TenantCategory tenantcategoryId;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenants")
//    private List<TenantCustomerSettings> tenantCustomerSettingsList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantId")
//    private List<Warehouse> warehousesList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantId")
//    private List<TenantNews> tenantNewsList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantId")
//    private List<OnHandStocks> onHandStocksList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenants")
//    private List<User> usersList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantId")
//    private List<Product> productsList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantId")
//    private List<TenantContacts> tenantContactsList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantId")
//    private List<SalesInvoice> salesInvoicesList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentId")
//    private List<TenantConfigurations> tenantConfigurationsList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recOwner")
//    private List<ProdCategory> prodCategoriesList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantId")
//    private List<PaymentMethodObj> paymentMethodObjsList;
//    @OneToMany(mappedBy = "recOwner")
//    private List<UserGroups> userGroupsList;

    public Tenant ()
    {
    }

    public Tenant (String systemId)
    {
        this.systemId = systemId;
    }

    public Tenant (String systemId, String name, String email, boolean sysdeleted)
    {
        this.systemId = systemId;
        this.name = name;
        this.email = email;
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

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getCountry ()
    {
        return country;
    }

    public void setCountry (String country)
    {
        this.country = country;
    }

    public String getProvince ()
    {
        return province;
    }

    public void setProvince (String province)
    {
        this.province = province;
    }

    public String getImg ()
    {
        return img;
    }

    public void setImg (String img)
    {
        this.img = img;
    }

    public String getLogo ()
    {
        return logo;
    }

    public void setLogo (String logo)
    {
        this.logo = logo;
    }

    public String getMemo ()
    {
        return memo;
    }

    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    public String getNews ()
    {
        return news;
    }

    public void setNews (String news)
    {
        this.news = news;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
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

    public String getSyscreator ()
    {
        return sysCreator;
    }

    public void setSyscreator (String sysCreator)
    {
        this.sysCreator = sysCreator;
    }

    public String getSyslastupdater ()
    {
        return sysLastUpdater;
    }

    public void setSyslastupdater (String sysLastUpdater)
    {
        this.sysLastUpdater = sysLastUpdater;
    }

    public boolean getSysdeleted ()
    {
        return sysDeleted;
    }

    public void setSysDeleted (boolean sysdeleted)
    {
        this.sysDeleted = sysdeleted;
    }

    public TenantCategory getTenantcategoryId ()
    {
        return tenantcategoryId;
    }

    public void setTenantcategoryId (TenantCategory tenantcategoryId)
    {
        this.tenantcategoryId = tenantcategoryId;
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
//    public List<Warehouse> getWarehousesList ()
//    {
//        return warehousesList;
//    }
//
//    public void setWarehousesList (List<Warehouse> warehousesList)
//    {
//        this.warehousesList = warehousesList;
//    }
//
//    public List<TenantNews> getTenantNewsList ()
//    {
//        return tenantNewsList;
//    }
//
//    public void setTenantNewsList (List<TenantNews> tenantNewsList)
//    {
//        this.tenantNewsList = tenantNewsList;
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
//    public List<User> getUsersList ()
//    {
//        return usersList;
//    }
//
//    public void setUsersList (List<User> usersList)
//    {
//        this.usersList = usersList;
//    }
//
//    public List<Product> getProductsList ()
//    {
//        return productsList;
//    }
//
//    public void setProductsList (List<Product> productsList)
//    {
//        this.productsList = productsList;
//    }
//
//    public List<TenantContacts> getTenantContactsList ()
//    {
//        return tenantContactsList;
//    }
//
//    public void setTenantContactsList (List<TenantContacts> tenantContactsList)
//    {
//        this.tenantContactsList = tenantContactsList;
//    }
//
//    public List<SalesInvoice> getSalesInvoicesList ()
//    {
//        return salesInvoicesList;
//    }
//
//    public void setSalesInvoicesList (List<SalesInvoice> salesInvoicesList)
//    {
//        this.salesInvoicesList = salesInvoicesList;
//    }
//
//    public List<TenantConfigurations> getTenantConfigurationsList ()
//    {
//        return tenantConfigurationsList;
//    }
//
//    public void setTenantConfigurationsList (List<TenantConfigurations> tenantConfigurationsList)
//    {
//        this.tenantConfigurationsList = tenantConfigurationsList;
//    }
//
//    public List<ProdCategory> getProdCategoriesList ()
//    {
//        return prodCategoriesList;
//    }
//
//    public void setProdCategoriesList (List<ProdCategory> prodCategoriesList)
//    {
//        this.prodCategoriesList = prodCategoriesList;
//    }
//
//    public List<PaymentMethodObj> getPaymentMethodObjsList ()
//    {
//        return paymentMethodObjsList;
//    }
//
//    public void setPaymentMethodObjsList (List<PaymentMethodObj> paymentMethodObjsList)
//    {
//        this.paymentMethodObjsList = paymentMethodObjsList;
//    }
//
//    public List<UserGroups> getUserGroupsList ()
//    {
//        return userGroupsList;
//    }
//
//    public void setUserGroupsList (List<UserGroups> userGroupsList)
//    {
//        this.userGroupsList = userGroupsList;
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
        if (!(object instanceof Tenant)) {
            return false;
        }
        Tenant other = (Tenant) object;
        if ((this.systemId == null && other.systemId != null) || (this.systemId != null && !this.systemId.equals (other.systemId)))
            return false;
        return true;
    }

    @Override
    public String toString ()
    {
        return "sunwell.pos.entity.Tenant[ systemId=" + systemId + " ]";
    }

}
