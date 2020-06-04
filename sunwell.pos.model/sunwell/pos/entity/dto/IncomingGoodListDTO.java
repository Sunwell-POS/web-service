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
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IncomingGoodListDTO extends StandardDTO
{
    
    private List<IncomingGoodDTO> listIncomingGood;
    
    public IncomingGoodListDTO() {
        
    }
    
    public IncomingGoodListDTO(List<IncomingGood> _goods) {
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
}

//Dicari programmer:
//Menguasai pemrograman Java, javascript, HTML, CSS, JEE, JQuery
//Menguasai framework Spring, Laravel, Angular point plus
//Menguasai OOP, design pattern
//Menguasai version control dan git
//Menguasai database Oracle, MySQL, PostgreSQL, MariaDB, MongoDB
//Menguasai teknologi web server Apache, Nginx, Glassfish, Tomcat
//Siap bekerja dalam tekanan dan lembur


