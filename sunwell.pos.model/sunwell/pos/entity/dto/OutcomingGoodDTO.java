/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * OnHandDTO.java
 *
 * Created on Dec 11, 2017, 4:40:01 PM
 */

package sunwell.pos.entity.dto;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.User;
import sunwell.pos.entity.Warehouse;
import sunwell.pos.entity.OutcomingGood;
import sunwell.pos.util.DateTimeAdapter;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OutcomingGoodDTO extends StandardDTO
{
    private String systemId;
    private ProductDTO product;
    private WarehouseDTO warehouse;
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date outcomingDate;
    private String refId;
    private String memo;
    private Integer refType;
    private Double qty;
    
    public OutcomingGoodDTO() {
        
    }
    
    public OutcomingGoodDTO(OutcomingGood _oc) {
        setData (_oc);
    }
    
    public void setData(OutcomingGood _oc) {
        systemId = _oc.getSystemId ();
        
        // bisa null product dan warehousenya karena ada incoming good summary yanghanya punya qty saja
        
        if(_oc.getProduct () != null)
            product = new ProductDTO(_oc.getProduct ());
        
        if(_oc.getWarehouse () != null)
            warehouse = new WarehouseDTO(_oc.getWarehouse ());
        
        outcomingDate = _oc.getOutcomingDate ();
        refType = _oc.getRefType ();
        refId = _oc.getRefId ();
        memo = _oc.getMemo ();
        qty = _oc.getQty ();
    }
    
     /**
     * @return the product
     */
    public ProductDTO getProduct ()
    {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct (ProductDTO product)
    {
        this.product = product;
    }

    /**
     * @return the warehouse
     */
    public WarehouseDTO getWarehouse ()
    {
        return warehouse;
    }

    /**
     * @param warehouse the warehouse to set
     */
    public void setWarehouse (WarehouseDTO warehouse)
    {
        this.warehouse = warehouse;
    }

    /**
     * @return the outcomingDate
     */
    public Date getOutcomingDate ()
    {
        return outcomingDate;
    }

    /**
     * @param incomingDate the outcomingDate to set
     */
    public void setOutcomingDate (Date incomingDate)
    {
        this.outcomingDate = incomingDate;
    }

    /**
     * @return the systemId
     */
    public String getSystemId ()
    {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId (String systemId)
    {
        this.systemId = systemId;
    }

    /**
     * @return the refId
     */
    public String getRefId ()
    {
        return refId;
    }

    /**
     * @param refId the refId to set
     */
    public void setRefId (String refId)
    {
        this.refId = refId;
    }

    /**
     * @return the memo
     */
    public String getMemo ()
    {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo (String memo)
    {
        this.memo = memo;
    }

    /**
     * @return the refType
     */
    public Integer getRefType ()
    {
        return refType;
    }

    /**
     * @param refType the refType to set
     */
    public void setRefType (Integer refType)
    {
        this.refType = refType;
    }

    /**
     * @return the qty
     */
    public Double getQty ()
    {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty (Double qty)
    {
        this.qty = qty;
    }

   
}
