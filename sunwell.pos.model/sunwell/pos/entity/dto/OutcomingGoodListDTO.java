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
import sunwell.pos.entity.OutcomingGood;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OutcomingGoodListDTO extends StandardDTO
{
    
    private List<OutcomingGoodDTO> listOutcomingGood;
    
    public OutcomingGoodListDTO() {
        
    }
    
    public OutcomingGoodListDTO(List<OutcomingGood> _goods) {
        setData (_goods);
    }
    
    public void setData(List<OutcomingGood> _goods) {
        if(_goods != null && _goods.size () > 0) {
            listOutcomingGood = new LinkedList<> ();
            for (OutcomingGood oc : _goods) {
                listOutcomingGood.add (new OutcomingGoodDTO (oc));
            }
        }
        else
            listOutcomingGood = null;
    }

    /**
     * @return the listCategory
     */
    public List<OutcomingGoodDTO> getListOutcomingGood ()
    {
        return listOutcomingGood;
    }

    /**
     * @param listCategory the listCategory to set
     */
    public void setListOutcomingGood (List<OutcomingGoodDTO> _listOC)
    {
        this.listOutcomingGood = _listOC;
    }
}

//Dicari programmer:
//Menguasai pemrograman Java, javascript, HTML, CSS, JEE, JQuery
//Menguasai framework Spring, Laravel, Angular point plus
//Menguasai OOP, design pattern
//Menguasai version control dan git
//Menguasai database Oracle, MySQL, PostgreSQL, MariaDB, MongoDB
//Menguasai teknologi web server Apache, Nginx, Glassfish, Tomcat
//Siap bekerja dalam tekanan dan lembur


