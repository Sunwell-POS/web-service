/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * ApplicationConfig.java
 *
 * Created on Oct 16, 2017, 10:42:12 AM
 */

package sunwell.pos.service.service;

import java.util.Set;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author Benny
 */
@javax.ws.rs.ApplicationPath("resources") 
public class ApplicationConfig extends Application 
{
    @Override
    public Set<Class<?>> getClasses ()
    {
        Set<Class<?>> resources = new java.util.HashSet<> ();
        addRestResourceClasses (resources);
        resources.add (MultiPartFeature.class);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses (Set<Class<?>> resources)
    {
        resources.add (sunwell.pos.service.service.CrossOriginResourceSharingFilter.class);
        resources.add (sunwell.pos.service.service.CustomExceptionMapper.class);
        resources.add (sunwell.pos.service.service.InventoryService.class);
        resources.add (sunwell.pos.service.service.InvoiceService.class);
        resources.add (sunwell.pos.service.service.LoginService.class);
        resources.add (sunwell.pos.service.service.NotSupportedExceptionMapper.class);
        resources.add (sunwell.pos.service.service.ProductService.class);
    }
}
