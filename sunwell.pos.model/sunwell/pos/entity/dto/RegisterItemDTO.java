/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ProdCategoryListDTO.java
 *
 * Created on Oct 18, 2017, 3:27:27 PM
 */


package sunwell.pos.entity.dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.util.DateTimeAdapter;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegisterItemDTO extends StandardDTO
{
    private WarehouseDTO warehouse;
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    private Date incomingDate;
    
    private List<IncomingGoodDTO> listIncomingGood;
    private String memo;
    
    public RegisterItemDTO() {
//        System.out.println ("Cons called");
    }
    
    public RegisterItemDTO(List<IncomingGood> _goods) {
        setData (_goods);
    }
    
    public void setData(List<IncomingGood> _goods) {
        if(_goods != null && _goods.size () > 0) {
            listIncomingGood = new LinkedList<> ();
            for (IncomingGood ic : _goods) {
                listIncomingGood.add (new IncomingGoodDTO (ic));
            }
        }
        else
            listIncomingGood = null;
    }

    /**
     * @return the listCategory
     */
    public List<IncomingGoodDTO> getListIncomingGood ()
    {
        return listIncomingGood;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListIncomingGood (List<IncomingGoodDTO> _listIC)
    {
        this.listIncomingGood = _listIC;
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
     * @param _incDate the incomingDate to set
     */
    public void setIncomingDate (Date _incDate)
    {
        this.incomingDate = _incDate;
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
}
