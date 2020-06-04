/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * DateTimeAdapter.java
 *
 * Created on Jul 3, 2017, 10:23:47 AM
 */

package sunwell.pos.util;

import java.text.SimpleDateFormat;
import sunwell.pos.entity.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Benny
 */
public class DateTimeAdapter extends XmlAdapter<String, Date>
{
//    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss"); 
    
    @Override 
    public Date unmarshal(String str) throws Exception 
    { 
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(str != null && str.length () > 0)
                return dateFormat.parse(str);
            
            return null;
        }
        catch(Exception e) {
            System.out.println ("Error: " + e.getMessage ());
            e.printStackTrace ();
            throw e;
        }
    } 
    
    @Override 
    public String marshal(Date date) throws Exception 
    { 
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if(date != null)
                return dateFormat.format(date); 
            
            return null;
        }
        catch(Exception e) {
            System.out.println ("Error: " + e.getMessage ());
            e.printStackTrace ();
            throw e;
        }
         
    }

}
