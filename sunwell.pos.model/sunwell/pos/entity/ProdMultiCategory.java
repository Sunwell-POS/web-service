///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// * ProdMultiCategory.java
// *
// * Created on Oct 13, 2017, 3:43:02 PM
// */
//
//package sunwell.pos.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.EmbeddedId;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.IdClass;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
///**
// *
// * @author Benny
// */
//@Entity 
//@IdClass(ProdMultiCategoryPK.class)
//@Table(name = "prod_multi_categories")
////@NamedQueries({
////    @NamedQuery(name = "ProdMultiCategory.findAll", query = "SELECT p FROM ProdMultiCategory p")
////    , @NamedQuery(name = "ProdMultiCategory.findByProdCategoryId", query = "SELECT p FROM ProdMultiCategory p WHERE p.prodCategory = :prodCategory")
////    , @NamedQuery(name = "ProdMultiCategory.findByProductId", query = "SELECT p FROM ProdMultiCategory p WHERE p.prodMultiCategoriesPK.productId = :productId")
////    , @NamedQuery(name = "ProdMultiCategory.findByStatus", query = "SELECT p FROM ProdMultiCategory p WHERE p.status = :status")
////    , @NamedQuery(name = "ProdMultiCategory.findByCreatedAt", query = "SELECT p FROM ProdMultiCategory p WHERE p.createdAt = :createdAt")
////    , @NamedQuery(name = "ProdMultiCategory.findByUpdatedAt", query = "SELECT p FROM ProdMultiCategory p WHERE p.updatedAt = :updatedAt")
////    , @NamedQuery(name = "ProdMultiCategory.findBySyscreator", query = "SELECT p FROM ProdMultiCategory p WHERE p.sysCreator = :sysCreator")
////    , @NamedQuery(name = "ProdMultiCategory.findBySyslastupdater", query = "SELECT p FROM ProdMultiCategory p WHERE p.sysLastUpdater = :sysLastUpdater")
////    , @NamedQuery(name = "ProdMultiCategory.findBySysDeleted", query = "SELECT p FROM ProdMultiCategory p WHERE p.sysDeleted = :sysDeleted")})
//public class ProdMultiCategory implements Serializable 
//{
//    private static final long serialVersionUID = 1L;
////    @EmbeddedId
////    protected ProdMultiCategoryPK prodMultiCategoriesPK;
//    
//    @Id
//    @ManyToOne()
//    @JoinColumn(name = "prodCategoryId")
//    private ProdCategory prodCategory;
//    
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "productId")
//    private Product product;
//    
//    @Basic(optional = false)
//    @Column(name = "status")
//    private boolean status;
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//    @Column(name = "updated_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date updatedAt;
//    @Column(name = "syscreator")
//    private String sysCreator;
//    @Column(name = "syslastupdater")
//    private String sysLastUpdater;
//    @Basic(optional = false)
//    @Column(name = "sysdeleted")
//    private boolean sysDeleted;
////    @JoinColumn(name = "prodCategoryId", referencedColumnName = "systemId", insertable = false, updatable = false)
////    @ManyToOne(optional = false)
////    private ProdCategory prodCategory;
////    @JoinColumn(name = "productId", referencedColumnName = "systemId", insertable = false, updatable = false)
////    @ManyToOne(optional = false)
////    private Product product;
//
//    public ProdMultiCategory ()
//    {
//    }
//
////    public ProdMultiCategory (ProdMultiCategoryPK prodMultiCategoriesPK)
////    {
////        this.prodMultiCategoriesPK = prodMultiCategoriesPK;
////    }
////
////    public ProdMultiCategory (ProdMultiCategoryPK prodMultiCategoriesPK, boolean status, boolean sysdeleted)
////    {
////        this.prodMultiCategoriesPK = prodMultiCategoriesPK;
////        this.status = status;
////        this.sysDeleted = sysdeleted;
////    }
////
////    public ProdMultiCategory (String prodCategoryId, String productId)
////    {
////        this.prodMultiCategoriesPK = new ProdMultiCategoryPK (prodCategoryId, productId);
////    }
////
////    public ProdMultiCategoryPK getProdMultiCategoryPK ()
////    {
////        return prodMultiCategoriesPK;
////    }
////
////    public void setProdMultiCategoryPK (ProdMultiCategoryPK prodMultiCategoriesPK)
////    {
////        this.prodMultiCategoriesPK = prodMultiCategoriesPK;
////    }
//
//    public boolean getStatus ()
//    {
//        return status;
//    }
//
//    public void setStatus (boolean status)
//    {
//        this.status = status;
//    }
//
//    public Date getCreatedAt ()
//    {
//        return createdAt;
//    }
//
//    public void setCreatedAt (Date createdAt)
//    {
//        this.createdAt = createdAt;
//    }
//
//    public Date getUpdatedAt ()
//    {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt (Date updatedAt)
//    {
//        this.updatedAt = updatedAt;
//    }
//
//    public String getSysCreator ()
//    {
//        return sysCreator;
//    }
//
//    public void setSysCreator (String syscreator)
//    {
//        this.sysCreator = syscreator;
//    }
//
//    public String getSysLastUpdater ()
//    {
//        return sysLastUpdater;
//    }
//
//    public void setSysLastUpdater (String syslastupdater)
//    {
//        this.sysLastUpdater = syslastupdater;
//    }
//
//    public boolean getSysdeleted ()
//    {
//        return sysDeleted;
//    }
//
//    public void setSysDeleted (boolean sysdeleted)
//    {
//        this.sysDeleted = sysdeleted;
//    }
//
//    public ProdCategory getProdCategory ()
//    {
//        return prodCategory;
//    }
//
//    public void setProdCategory (ProdCategory prodCategories)
//    {
//        this.setProdCategory (prodCategories);
//    }
//
//    public Product getProduct ()
//    {
//        return product;
//    }
//
//    public void setProduct (Product products)
//    {
//        this.product = products;
//    }
//
//    @Override
//    public int hashCode ()
//    {
//        int hash = 0;
//        hash += (prodCategory != null ? prodCategory.hashCode () : 0);
//        hash += (product != null ? product.hashCode () : 0);
//        return hash;
//    }
//
//    @Override
//    public boolean equals (Object object)
//    {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof ProdMultiCategory)) {
//            return false;
//        }
//        ProdMultiCategory other = (ProdMultiCategory) object;
//        if ((this.prodCategory == null && other.prodCategory != null) || (this.prodCategory != null && !this.prodCategory.equals (other.prodCategory)))
//            return false;
//        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals (other.product)))
//            return false;
//        return true;
//    }
//
////    @Override
////    public String toString ()
////    {
////        return "sunwell.pos.entity.ProdMultiCategory[ prodMultiCategoriesPK=" + prodMultiCategoriesPK + " ]";
////    }
//
//}
