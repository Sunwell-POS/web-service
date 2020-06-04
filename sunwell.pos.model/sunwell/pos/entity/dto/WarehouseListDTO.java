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

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.Warehouse;
/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WarehouseListDTO extends StandardDTO
{
    
    private List<WarehouseDTO> listWarehouse;
    
    public WarehouseListDTO() {
        
    }
    
    public WarehouseListDTO(List<Warehouse> _warehouses) {
        setData (_warehouses);
    }
    
    public void setData(List<Warehouse> _warehouses) {
        if(_warehouses != null && _warehouses.size () > 0) {
            listWarehouse = new LinkedList<> ();
            for (Warehouse wrh : _warehouses) {
                listWarehouse.add (new WarehouseDTO (wrh));
            }
        }
        else
            listWarehouse = null;
    }

    public List<WarehouseDTO> getListWarehouse ()
    {
        return listWarehouse;
    }

   
    public void setListProduct (List<WarehouseDTO> listWarehouse)
    {
        this.listWarehouse = listWarehouse;
    }
}
