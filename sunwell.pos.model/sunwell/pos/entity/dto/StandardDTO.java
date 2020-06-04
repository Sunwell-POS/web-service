/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * StandardDTO.java
 *
 * Created on Jul 20, 2017, 11:11:14 AM
 */

package sunwell.pos.entity.dto;

import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benny
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StandardDTO 
{
    
    public static final int ERROR_INTERNAL_SERVER = 0;
    public static final int ERROR_NO_LOGIN_SESSION = 1;
    public static final int ERROR_CANT_FIND_TENANT = 2;
    public static final int ERROR_CANT_FIND_USER = 3;
    public static final int ERROR_NO_EMAIL = 4;
    public static final int ERROR_NO_PASSWORD = 5;
    public static final int ERROR_NO_TENANT = 6;
    public static final int ERROR_CANT_FIND_SPECIFIED_OBJECT = 7;
    public static final int ERROR_NO_OBJECT = 8;
    public static final int ERROR_NOT_ENOUGH_STOCK = 9;
    public static final int ERROR_NO_PRODUCT_SPECIFIED = 10;
    public static final int ERROR_NO_PRODUCT_CATEGORY_SPECIFIED = 11;
    public static final int ERROR_NO_WAREHOUSE_SPECIFIED = 12;
    public static final int ERROR_NO_START_DATE_SPECIFIED = 13;
    public static final int ERROR_NO_END_DATE_SPECIFIED = 14;

    private Integer errorCode ;
    
    @QueryParam("sessionString")
    private String sessionString;
    
//    @QueryParam("tenantId")
//    private String tenantId;
    
    private String errorMessage;
    private String companyName;
    
    /**
     * @return the session
     */
    public String getSessionString ()
    {
        return sessionString;
    }

    /**
     * @param session the session to set
     */
    public void setSessionString (String session)
    {
        this.sessionString = session;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage ()
    {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
    
    /**
     * @return the companyName
     */
    public String getCompanyName ()
    {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName (String companyName)
    {
        this.companyName = companyName;
    }
    
    /**
     * @return the errorCode
     */
    public Integer getErrorCode ()
    {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode (Integer errorCode)
    {
        this.errorCode = errorCode;
    }
    
   
}
