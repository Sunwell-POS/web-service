/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Util.java
 *
 * Created on Dec 6, 2017, 2:39:45 PM
 */

package sunwell.pos.service.service;

import aegwyn.core.web.model.UserSession;
import aegwyn.core.web.model.UserSessionContainer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.User;
import sunwell.pos.entity.Warehouse;
import sunwell.pos.entity.bus.GenericFacade;
import sunwell.pos.entity.bus.ProductFacade;
import sunwell.pos.entity.bus.InventoryFacade;
import sunwell.pos.entity.dto.OnHandStockDTO;
import sunwell.pos.entity.dto.ProductDTO;
import sunwell.pos.entity.dto.WarehouseDTO;

/**
 *
 * @author Benny
 */
@ApplicationScoped
public class ServiceUtil 
{
    @Inject
    UserSessionContainer usc;
    
    @EJB
    InventoryFacade stockFacade;
    
    @EJB
    ProductFacade productFacade;
    
    @EJB
    GenericFacade genericFacade;
    
    public boolean validateLogin(String _sessionString) {
        return getUser (_sessionString) != null ? true : false;
    }
    
    public UserSession getSession(String _sessionString, boolean _createNew) {
        return usc.getSession (_sessionString, _createNew);
    }
    
    public UserSessionContainer getSessionContainer() {
        return usc;
    }
    
    public User getUser(String _sessionString) {
        UserSession session = usc.getSession (_sessionString, false);
        if(session == null)
            return null;
        
        User usr = (User)session.getObject ("user");
        return usr;
    }
    
    public static void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                            uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void writeToFile(byte[] _bytes, String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(
                            uploadedFileLocation));
            int read = 0;
//            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            out.write (_bytes);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, Object> makeOnHandStockFilter(OnHandStockDTO _dto) {
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.setSerializationInclusion (JsonInclude.Include.NON_NULL);
        Map<String, Object> map = oMapper.convertValue(_dto, Map.class);
        System.out.println("MAP: " + map);
        
        Map<String, Object> prodDTO = (Map<String, Object>)map.get ("product");
        if(prodDTO != null && prodDTO.get ("systemId") != null) {
            Product prod = genericFacade.findById (prodDTO.get ("systemId"), Product.class);
            map.put ("product", prod);
        }
        else
            map.remove ("product");
        
        Map<String, Object> wDTO = (Map<String, Object>)map.get ("warehouse");
        if(wDTO != null && wDTO.get ("systemId") != null) {
            Warehouse wrh = genericFacade.findById (wDTO.get ("systemId"), Warehouse.class);
            map.put ("warehouse", wrh);
        }
        else
            map.remove ("warehouse");
        
        System.out.println("MAP: " + map);
        
        return map;
    }
}
