/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Util.java
 *
 * Created on Nov 8, 2017, 3:38:25 PM
 */

package sunwell.pos.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import sunwell.pos.entity.dto.OnHandStockDTO;
import sunwell.pos.entity.dto.ProductDTO;

/**
 *
 * @author Benny
 */

public class Util 
{
    public static Map<String, Object> objToMap(Object _obj) {
        ObjectMapper oMapper = new ObjectMapper();
        oMapper.setSerializationInclusion (JsonInclude.Include.NON_NULL);
        Map<String, Object> map = oMapper.convertValue(_obj, Map.class);
        System.out.println(map);
        return map;
    }
    
    public static String getHomePath() {
        String homePath = System.getProperty("user.home") + "/";
        return homePath;
    }
    
    
}
