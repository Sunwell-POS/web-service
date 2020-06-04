/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * LoginService.java
 *
 * Created on Oct 16, 2017, 10:46:29 AM
 */

package sunwell.pos.service.service;

import aegwyn.core.web.model.UserSession;
import aegwyn.core.web.model.UserSessionContainer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;
import sunwell.pos.entity.bus.GenericFacade;
import sunwell.pos.entity.bus.ProdCategoryFacade;
import sunwell.pos.entity.bus.ProductFacade;
import sunwell.pos.entity.bus.TenantFacade;
import sunwell.pos.entity.bus.UserCredFacade;
import sunwell.pos.entity.dto.ProdCategoryDTO;
import sunwell.pos.entity.dto.ProdCategoryListDTO;
import sunwell.pos.entity.dto.UserListDTO;
import sunwell.pos.entity.dto.ProdListDTO;
import sunwell.pos.entity.dto.ProductDTO;
import sunwell.pos.entity.dto.StandardDTO;
import sunwell.pos.entity.dto.TenantDTO;
import sunwell.pos.entity.dto.UserCredDTO;
import sunwell.pos.util.Util;

/**
 *
 * @author Benny
 */
@Stateless
@Path("")
public class ProductService 
{
    @EJB
    TenantFacade tenantFacade;
    
    @EJB
    ProductFacade productFacade;
    
    @EJB
    ProdCategoryFacade prodCtgrFacade;
    
    @EJB
    UserCredFacade userFacade;
    
    @EJB
    GenericFacade genericFacade;
    
    @Inject
    ServiceUtil svcUtil;
    
    @Context
    ServletContext sCtx;
    
//    @Inject
//    UserSessionContainer usc;
    
    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategories(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        Thread.sleep (2000);
        ProdCategoryListDTO retval = new ProdCategoryListDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<ProdCategory> categories = prodCtgrFacade.findByTenant (t);
            retval.setData (categories);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/category")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addProdCategory(ProdCategoryDTO _dto) {
        
        ProdCategoryDTO retval = new ProdCategoryDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            ProdCategory ctgr = new ProdCategory ();
            ctgr.setName (_dto.getName ());
            ctgr.setBgColor (_dto.getBgColor ());
            ctgr.setDefault1 (_dto.isDefault1());
            ctgr.setRecOwner (user.getTenant ());
            ProdCategory entity = genericFacade.create (ctgr);
            retval.setData (entity);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/category")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editProdCategory(ProdCategoryDTO _dto) {
        System.out.println ("DFLT: " + _dto.isDefault1 ());
        ProdCategoryDTO retval = new ProdCategoryDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            ProdCategory ctgr = genericFacade.findById (_dto.getSystemId (), ProdCategory.class);
            if(ctgr == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                ctgr.setName (_dto.getName ());
                ctgr.setBgColor (_dto.getBgColor ());
                ctgr.setDefault1 (_dto.isDefault1 ());
                ctgr.setRecOwner (user.getTenant ());
                ProdCategory entity = genericFacade.edit (ctgr);
                retval.setData (entity);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @DELETE
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteProdcategory(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        ProdCategoryDTO retval = new ProdCategoryDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            ProdCategory ctgr = genericFacade.findById (_systemId, ProdCategory.class);
            if(ctgr == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (ctgr);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/category")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addProdCategoryForm(
        @FormDataParam("name") String _name,
        @FormDataParam("default1") boolean _def,
        @FormDataParam("sessionString") String _sessionString ) {
        
        ProdCategoryDTO retval = new ProdCategoryDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            ProdCategory ctgr = new ProdCategory ();
            ctgr.setName (_name);
            ctgr.setDefault1 (_def);
            ctgr.setRecOwner (user.getTenant ());
            ProdCategory entity = genericFacade.create (ctgr);
            retval.setData (entity);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
//    
    @PUT
    @Path("/category")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editProdCategoryForm(
        @FormDataParam("systemId") String _systemId,
        @FormDataParam("name") String _name,
        @FormDataParam("default1") boolean _def,
        @FormDataParam("sessionString") String _sessionString ) {
        
        ProdCategoryDTO retval = new ProdCategoryDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            ProdCategory ctgr = genericFacade.findById (_systemId, ProdCategory.class);
            if(ctgr == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                ctgr.setName (_name);
                ctgr.setDefault1 (_def);
                ctgr.setRecOwner (user.getTenant ());
                ProdCategory entity = genericFacade.edit (ctgr);
                retval.setData (entity);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        ProdListDTO retval = new ProdListDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<Product> products = productFacade.findByTenant (t);
            retval.setData (products);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addProduct(ProductDTO _dto) throws Exception  {
        ProductDTO retval = new ProductDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Product prod = new Product ();
            prod.setName (_dto.getName ());
            prod.setBarcode (_dto.getBarCode ());
            prod.setDescription (_dto.getDescription ());
            prod.setPrice (_dto.getPrice ());
            prod.setMetric (_dto.getMetric ());
            prod.setStatus (_dto.getStatus ());
            prod.setHasDiscount (_dto.getHasDiscount ());
            prod.setHasStock (_dto.getHasStock ());
            prod.setTenant (user.getTenant ());
            String ctgrStr = _dto.getCategoriesString ();
            List<ProdCategory> categories = new LinkedList<>();
            if(ctgrStr != null) {
                String[] ctgrId = ctgrStr.split (";");
                for (String id : ctgrId) {
                    categories.add (genericFacade.findById (id, ProdCategory.class));
                }
            }
            else
                categories = null;
            
            if(categories != null)
                prod.setCategories (categories);
            
            System.out.println ("IMAGE NAME: " + _dto.getImg () + " D SIZE: " + _dto.getImgData ().length ());
            if(_dto.getImgData () != null) {
                prod.setImg (_dto.getImg());
//                String uploadedFileLocation = "/pos-image/products/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
//                File dir = new File("/pos-image/products/" + user.getTenant ().getSystemId ());
                String uploadedFileLocation = sCtx.getInitParameter ("imagePath") + "products/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
                File dir = new File(sCtx.getInitParameter ("imagePath") + "products/" + user.getTenant ().getSystemId ());
                if(!dir.exists ()) {
                    dir.mkdir ();
                }
                svcUtil.writeToFile(Base64.getDecoder ().decode (_dto.getImgData ()), uploadedFileLocation);
            }
            
            
            Product entity = genericFacade.create (prod);
            retval.setData (entity);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/products")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editProduct(ProductDTO _dto) {
        
        System.out.println ("IMAGE: " + _dto.getImg ());
        ProductDTO retval = new ProductDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Product prod = genericFacade.findById (_dto.getSystemId (), Product.class);
            
            if(prod == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FINd THE SPECIFIED PRODUCT");
            }
            else {
                prod.setName (_dto.getName () != null ? _dto.getName () : prod.getName ());
                prod.setBarcode (_dto.getBarCode () != null ? _dto.getBarCode () : prod.getBarcode ());
                prod.setDescription (_dto.getDescription () != null ? _dto.getDescription () : prod.getDescription ());
                prod.setPrice (_dto.getPrice () != null ? _dto.getPrice () : prod.getPrice ());
                prod.setMetric (_dto.getMetric () != null ? _dto.getMetric () : prod.getMetric ());
                prod.setStatus (_dto.getStatus () != null ? _dto.getStatus () : prod.getStatus ());
                prod.setHasDiscount (_dto.getHasDiscount () != null ? _dto.getStatus () : prod.getHasDiscount ());
                prod.setHasStock (_dto.getHasStock () != null ? _dto.getHasStock () : prod.getHasStock ());
//                prod.setTenant (user.getTenant ());
                String ctgrStr = _dto.getCategoriesString ();
                List<ProdCategory> categories = new LinkedList<>();
                if(ctgrStr != null) {
                    String[] ctgrId = ctgrStr.split (";");
                    for (String id : ctgrId) {
                        categories.add (genericFacade.findById (id, ProdCategory.class));
                    }
                }
                else
                    categories = null;
                
                
                if(categories != null) {
                    prod.setCategories (null);
                    genericFacade.flush ();
                    prod.setCategories (categories);
                }
                
//                System.out.println ("IMAGE NAME: " + _dto.getImg () + " D SIZE: " + _dto.getImgData ().length ());
                if(_dto.getImgData () != null) {
                    prod.setImg (_dto.getImg());
//                    String uploadedFileLocation = "/pos-image/products/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
//                    URL url = this.getClass().getClassLoader().getResource("pos-image/products");
//                    String uploadedFileLocation = url.getPath () + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
//                    File dir = new File("/pos-image/products/" + user.getTenant ().getSystemId ());
                    String uploadedFileLocation = sCtx.getInitParameter ("imagePath") + "products/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
                    File dir = new File(sCtx.getInitParameter ("imagePath") + "products/" + user.getTenant ().getSystemId ());
                    if(!dir.exists ()) {
                        dir.mkdir ();
                    }
                    svcUtil.writeToFile(Base64.getDecoder ().decode (_dto.getImgData ()), uploadedFileLocation);
                }


                Product entity = genericFacade.edit (prod);
                retval.setData (entity);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @DELETE
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteProduct(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        ProductDTO retval = new ProductDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Product prod = genericFacade.findById (_systemId, Product.class);
            if(prod == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (prod);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/products/images")
    @Produces({"image/png", "image/jpg"})
    public Response getImage(@QueryParam("sessionString")String _sessionString,
                              @QueryParam("tenantId")String _tenantId,
                              @QueryParam("image") String _image) throws Exception
    {
        
        Tenant tenant = null;
        if(_tenantId != null) {
            tenant = genericFacade.findById (_tenantId, Tenant.class);
        }
        else if(_sessionString != null) {
            UserSession us = svcUtil.getSession (_sessionString, false);
            if(us != null) {
                User user = null;
                user = (User)us.getObject ("user");
                tenant = user.getTenant ();
            }
        }
        
        if(tenant == null) {
            System.out.println ("Tenant can't be found tid: " + _tenantId);
            return Response.noContent ().build ();
        }
        
//        String path = Util.getHomePath () + "pos-image/products/" + tenant.getSystemId () + "/" + _image;
//        String path = "/pos-image/products/" + tenant.getSystemId () + "/" + _image;
//        URL url = this.getClass().getClassLoader().getResource("pos-image/products");
//        String path = url.getPath () + "/" + tenant.getSystemId () + "/" + _image;;
        String path = sCtx.getInitParameter ("imagePath") + "products/" + tenant.getSystemId () + "/" + _image;
        System.out.println ("PATH: " + path);
        File file = new File(path);
        if(file.exists ()) { 
            FileInputStream fis = new FileInputStream(file);
            long length = file.length ();
            byte[] filecontent = new byte[(int)length];
            fis.read(filecontent,0,(int)length); 
            
            return Response.ok().entity(new StreamingOutput(){
                @Override
                public void write(OutputStream output) throws IOException, WebApplicationException {
                   output.write(filecontent);
                   output.flush();
                }
                }).build();
        }
        else {
            System.out.println ("File doesn't exist, tid: " + tenant.getSystemId () + " image: " + _image);
            return Response.noContent ().build();
        }
    }
        
//    private boolean validateLogin(String _sessionString) {
//        return getUser (_sessionString) != null ? true : false;
//    }
//    
//    private User getUser(String _sessionString) {
//        UserSession session = usc.getSession (_sessionString, false);
//        if(session == null)
//            return null;
//        
//        User usr = (User)session.getObject ("user");
//        return usr;
//    }
//    
//    
//    
//    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
//
//        try {
//            OutputStream out = new FileOutputStream(new File(
//                            uploadedFileLocation));
//            int read = 0;
//            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            while ((read = uploadedInputStream.read(bytes)) != -1) {
//                    out.write(bytes, 0, read);
//            }
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private void writeToFile(byte[] _bytes, String uploadedFileLocation) {
//
//        try {
//            OutputStream out = new FileOutputStream(new File(
//                            uploadedFileLocation));
//            int read = 0;
////            byte[] bytes = new byte[1024];
//
//            out = new FileOutputStream(new File(uploadedFileLocation));
//            out.write (_bytes);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
  
}

