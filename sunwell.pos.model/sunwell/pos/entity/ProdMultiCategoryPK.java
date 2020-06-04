///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
///**
// * ProdMultiCategoryPK.java
// *
// * Created on Oct 13, 2017, 3:43:02 PM
// */
//
//package sunwell.pos.entity;
//
//import java.io.Serializable;
//import javax.persistence.Basic;
//import javax.persistence.Column;
//import javax.persistence.Embeddable;
//
///**
// *
// * @author Benny
// */
//public class ProdMultiCategoryPK implements Serializable 
//{
//
//    @Basic(optional = false)
//    @Column(name = "prodCategoryId")
//    private String prodCategory;
//    @Basic(optional = false)
//    @Column(name = "productId")
//    private String product;
//
//    public ProdMultiCategoryPK ()
//    {
//    }
//
//    public ProdMultiCategoryPK (String prodCategoryId, String productId)
//    {
//        this.prodCategory = prodCategoryId;
//        this.product = productId;
//    }
//
//    public String getProdCategory ()
//    {
//        return prodCategory;
//    }
//
//    public void setProdCategory (String prodCategoryId)
//    {
//        this.prodCategory = prodCategoryId;
//    }
//
//    public String getProduct ()
//    {
//        return product;
//    }
//
//    public void setProduct (String productId)
//    {
//        this.product = productId;
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
//        if (!(object instanceof ProdMultiCategoryPK)) {
//            return false;
//        }
//        ProdMultiCategoryPK other = (ProdMultiCategoryPK) object;
//        if ((this.prodCategory == null && other.prodCategory != null) || (this.prodCategory != null && !this.prodCategory.equals (other.prodCategory)))
//            return false;
//        if ((this.product == null && other.product != null) || (this.product != null && !this.product.equals (other.product)))
//            return false;
//        return true;
//    }
//
//    @Override
//    public String toString ()
//    {
//        return "sunwell.pos.entity.ProdMultiCategoryPK[ prodCategoryId=" + prodCategory + ", productId=" + product + " ]";
//    }
//
//}
