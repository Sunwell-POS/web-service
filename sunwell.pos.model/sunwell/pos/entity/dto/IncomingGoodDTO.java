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
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.util.DateTimeAdapter;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IncomingGoodDTO extends StandardDTO
{
    private String systemId;
    private ProductDTO product;
    private WarehouseDTO warehouse;
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date incomingDate;
    private String refId;
    private String memo;
    private Integer refType;
    private Double qty;
    private Double unitPrice;
    
    public IncomingGoodDTO() {
        
    }
    
    public IncomingGoodDTO(IncomingGood _ic) {
        setData (_ic);
    }
    
    public void setData(IncomingGood _ic) {
        systemId = _ic.getSystemId ();
        
        // bisa null product dan warehousenya karena ada incoming good summary yanghanya punya qty saja
        
        if(_ic.getProduct () != null)
            product = new ProductDTO(_ic.getProduct ());
        
        if(_ic.getWarehouse () != null)
            warehouse = new WarehouseDTO(_ic.getWarehouse ());
        incomingDate = _ic.getIncomingDate ();
        refType = _ic.getRefType ();
        refId = _ic.getRefId ();
        memo = _ic.getMemo ();
        qty = _ic.getQty ();
        unitPrice = _ic.getUnitPrice ();
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
     * @return the incomingDate
     */
    public Date getIncomingDate ()
    {
        return incomingDate;
    }

    /**
     * @param incomingDate the incomingDate to set
     */
    public void setIncomingDate (Date incomingDate)
    {
        this.incomingDate = incomingDate;
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

    /**
     * @return the unitPrice
     */
    public Double getUnitPrice ()
    {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice (Double unitPrice)
    {
        this.unitPrice = unitPrice;
    }
    
}
