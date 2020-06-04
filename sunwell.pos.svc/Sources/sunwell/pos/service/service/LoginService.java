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
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.text.SimpleDateFormat;
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
import org.mindrot.jbcrypt.BCrypt;
import sunwell.pos.entity.Customer;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.TenantCustomerSetting;
import sunwell.pos.entity.UGAccessRight;
import sunwell.pos.entity.UGTaskType;
import sunwell.pos.entity.User;
import sunwell.pos.entity.UserGroup;
import sunwell.pos.entity.bus.CustomerFacade;
import sunwell.pos.entity.bus.GenericFacade;
import sunwell.pos.entity.bus.TenantFacade;
import sunwell.pos.entity.bus.UserCredFacade;
import sunwell.pos.entity.dto.AccessRightDTO;
import sunwell.pos.entity.dto.CustomerDTO;
import sunwell.pos.entity.dto.CustomerListDTO;
import sunwell.pos.entity.dto.ProdCategoryDTO;
import sunwell.pos.entity.dto.UserListDTO;
import sunwell.pos.entity.dto.StandardDTO;
import sunwell.pos.entity.dto.TenantDTO;
import sunwell.pos.entity.dto.UserCredDTO;
import sunwell.pos.entity.dto.UserGroupDTO;
import sunwell.pos.entity.dto.UserGroupListDTO;
import sunwell.pos.util.Util;

/**
 *
 * @author Benny
 */
@Stateless
@Path("")
public class LoginService 
{
    @EJB
    TenantFacade tenantFacade;
    
    @EJB
    CustomerFacade custFacade;
    
    @EJB
    UserCredFacade userFacade;
    
    @EJB
    GenericFacade genericFacade;
    
    @Inject
    UserSessionContainer usc;
    
    @Context
    ServletContext sCtx;
    
    @GET
    @Path("/tenantinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTenantInfo(@QueryParam("email") String _email) throws Exception {
        
        System.out.println (FileSystems.getDefault().getPath(".").toAbsolutePath ());
        TenantDTO retval = new TenantDTO();
        if(_email == null || _email.length () <= 0)
            retval.setErrorMessage("ERROR, NO COMPANY EMAIL IS SPECIFIED");
        else {
            Tenant tenant = tenantFacade.findByEmail (_email);
            if(tenant == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_TENANT);
                retval.setErrorMessage ("ERROR CAN'T FIND TENANT");
            }
            else 
                retval.setData (tenant);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredDTO _dto) throws Exception
    {
//        System.out.println ("PASSWORD: " + _dto.getPassword ());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println ("Example: " + dateFormat.format (new Date()));
//        Date example = dateFormat.parse ("2017-11-22 12:00:00");
//        System.out.println ("message: " + example.toString ());
        Thread.sleep (2000);
//        List<User> findAll = genericFacade.findAll (User.class);
//        for (User user : findAll) {
//            System.out.println ("Name: " + user.getName ());
//        }
        UserCredDTO retval = new UserCredDTO();
        if(_dto.getEmail () == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_EMAIL);
            retval.setErrorMessage ("ERROR, NO EMAIL IS SPECIFIED");
        }
        else if(_dto.getPassword () == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_PASSWORD);
            retval.setErrorMessage ("ERROR, NO PASSWORD IS SPECIFIED");
        }
        else if(_dto.getTenant ()== null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_TENANT);
            retval.setErrorMessage ("ERROR, NO TENANT IS SPECIFIED");
        }
        else {
            Tenant tenant = genericFacade.findById (_dto.getTenant ().getSystemId (), Tenant.class);
            User usr = userFacade.validate (tenant, _dto.getEmail (), _dto.getPassword ());
            if(usr == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_USER);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED USER");
            }
            else {
                UserSession us = usc.newSession ();
                us.setSessionName ("Login");
                us.addObject ("user", usr);
                us.addObject ("realPassword", _dto.getPassword ());
                us.setLastActivity (Calendar.getInstance ());
                retval.setData (usr);
                retval.setSessionString (us.getSessionId ());
            }
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(UserCredDTO _dto) throws Exception
    {
        UserCredDTO retval = new UserCredDTO();
        if(!validateLogin (_dto.getSessionString ())) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            usc.removeSession (_dto.getSessionString ());
        }
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        UserListDTO retval = new UserListDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<User> users = userFacade.findByTenant (t);
            retval.setData (users);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addUser(UserCredDTO _dto) {
        
        UserCredDTO retval = new UserCredDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            User newUser = new User ();
            newUser.setName (_dto.getName ());
            newUser.setPassword (BCrypt.hashpw ((String)getObject (_dto.getSessionString (), "realPassword"), BCrypt.gensalt ()));
            newUser.setPhone (_dto.getPhone ());
            newUser.setStatus (true);
            newUser.setEmail (_dto.getEmail());
            if(_dto.getUserGroup () != null) {
                newUser.setUserGroup (genericFacade.findById (_dto.getUserGroup ().getSystemId (), UserGroup.class));
            }
            
//            System.out.println ("IMAGE NAME: " + _dto.getImg () + " D SIZE: " + _dto.getImgData ().length ());
            if(_dto.getImgData () != null) {
                newUser.setImg (_dto.getImg());
//                String uploadedFileLocation = "./pos-image/products/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
//                File dir = new File("./pos-image/products/" + user.getTenant ().getSystemId ());
                String uploadedFileLocation = sCtx.getInitParameter ("imagePath") + "users/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
                File dir = new File(sCtx.getInitParameter ("imagePath") + "users/" + user.getTenant ().getSystemId ());
                if(!dir.exists ()) {
                    dir.mkdir ();
                }
                ServiceUtil.writeToFile(Base64.getDecoder ().decode (_dto.getImgData ()), uploadedFileLocation);
            }
            
            newUser.setTenant (user.getTenant ());
            newUser = genericFacade.create (newUser);
            retval.setData (newUser);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editUser(UserCredDTO _dto) {
        UserCredDTO retval = new UserCredDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            User extUser = genericFacade.findById (_dto.getSystemId (), User.class);
            if(extUser == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED USER");
            }
            extUser.setName (_dto.getName () != null ? _dto.getName () : extUser.getName ());
            extUser.setPassword (_dto.getPassword () != null ? _dto.getPassword () : extUser.getPassword ());
            extUser.setPhone (_dto.getPhone () != null ? _dto.getPhone () : extUser.getPhone ());
            extUser.setStatus (_dto.getStatus () != null ? _dto.getStatus () : extUser.getStatus ());
            extUser.setEmail (_dto.getEmail() != null ? _dto.getEmail () : extUser.getEmail ());
            if(_dto.getUserGroup () != null) {
                extUser.setUserGroup (genericFacade.findById (_dto.getUserGroup ().getSystemId (), UserGroup.class));
            }
            
            if(_dto.getImgData () != null) {
                extUser.setImg (_dto.getImg());
//                String uploadedFileLocation = "./pos-image/products/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
//                File dir = new File("./pos-image/products/" + user.getTenant ().getSystemId ());
                String uploadedFileLocation = sCtx.getInitParameter ("imagePath") + "users/" + user.getTenant ().getSystemId () + "/" + _dto.getImg ();
                File dir = new File(sCtx.getInitParameter ("imagePath") + "users/" + user.getTenant ().getSystemId ());
                if(!dir.exists ()) {
                    dir.mkdir ();
                }
                ServiceUtil.writeToFile(Base64.getDecoder ().decode (_dto.getImgData ()), uploadedFileLocation);
            }
            
            extUser = genericFacade.edit (extUser);
            retval.setData (extUser);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }    
    
    
    @DELETE
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteUser(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        UserCredDTO retval = new UserCredDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            User extUser = genericFacade.findById (_systemId, User.class);
            if(extUser == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (extUser);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/usergroups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserGroups(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        UserGroupListDTO retval = new UserGroupListDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<UserGroup> groups = userFacade.findUserGroupByTenant (t);
            retval.setData (groups);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/usergroups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addUserGroup(UserGroupDTO _dto) {
        
        UserGroupDTO retval = new UserGroupDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            UserGroup group = new UserGroup ();
            List<UGAccessRight> accessRights ;
            group.setName (_dto.getName ());
            group.setMemo (_dto.getMemo ());
            if(_dto.getAccessRights () != null && _dto.getAccessRights ().size () > 0) {
                accessRights = new LinkedList<>();
                for (AccessRightDTO accessRight : _dto.getAccessRights ()) {
                    UGTaskType task = genericFacade.findById (accessRight.getSystemId (), UGTaskType.class);
                    if(task != null) {
                        UGAccessRight uga = new UGAccessRight ();
                        uga.setUserGroup (group);
                        uga.setUGTaskTypes (task);
                        uga.setStatus (true);
                        accessRights.add (uga);
                    }
                }
                if(accessRights.size () > 0)
                    group.setUGAccessRights (accessRights);
            }
            group.setTenant (user.getTenant ());
            group = genericFacade.create (group);
            retval.setData (group);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/usergroups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editUserGroup(UserGroupDTO _dto) {
        UserGroupDTO retval = new UserGroupDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            UserGroup group = genericFacade.findById (_dto.getSystemId (), UserGroup.class);
            if(group == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            
            List<UGAccessRight> accessRights ;
            group.setName (_dto.getName () != null ? _dto.getName () : group.getName ());
            group.setMemo (_dto.getMemo () != null ? _dto.getMemo () : group.getMemo ());
            if(_dto.getAccessRights () != null && _dto.getAccessRights ().size () > 0) {
                group.setUGAccessRights (null);
                genericFacade.flush ();
                
                accessRights = new LinkedList<>();
                for (AccessRightDTO accessRight : _dto.getAccessRights ()) {
                    UGTaskType task = genericFacade.findById (accessRight.getSystemId (), UGTaskType.class);
                    if(task != null) {
                        UGAccessRight uga = new UGAccessRight ();
                        uga.setUserGroup (group);
                        uga.setUGTaskTypes (task);
                        uga.setStatus (true);
                        accessRights.add (uga);
                    }
                }
                if(accessRights.size () > 0)
                    group.setUGAccessRights (accessRights);
            }
            group.setTenant (user.getTenant ());
            group = genericFacade.edit (group);
            retval.setData (group);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }    
    
    @DELETE
    @Path("/usergroups")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteUserGroup(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        UserGroupDTO retval = new UserGroupDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            UserGroup group = genericFacade.findById (_systemId, UserGroup.class);
            if(group == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (group);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        CustomerListDTO retval = new CustomerListDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<Customer> customers = custFacade.findByTenant (t);
            retval.setData (customers);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/customers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addCustomer(CustomerDTO _dto) {
        CustomerDTO retval = new CustomerDTO();
        User user = getUser (_dto.getSessionString ());
        System.out.println ("DTO: " + _dto.getIsMale () + " PHONEL " + _dto.getPhone () + " IS MALE: " + _dto.getIsMale ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Customer cust = new Customer();
            cust.setAddress (_dto.getAddress ());
            cust.setBirthdate (_dto.getBirthdate ());
            cust.setDisc (_dto.getDisc ());
            cust.setEmail (_dto.getEmail ());
            cust.setName (_dto.getName ());
            cust.setMemo (_dto.getMemo ());
            cust.setMemberNo (_dto.getMemberNo ());
            cust.setPhone (_dto.getPhone ());
            cust.setIsMale (_dto.getIsMale ());
            
            TenantCustomerSetting tcs = new TenantCustomerSetting ();
            tcs.setCustomer (cust);
            tcs.setTenant (user.getTenant ());
//            tcs.setStatus (Boolean.FALSE);
//            tcs.setSysbuiltin (true);
            
            genericFacade.create (cust);
            genericFacade.flush ();
            genericFacade.create (tcs);
            
                       
            retval.setData (cust);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/customers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editCustomer(CustomerDTO _dto) {
        
        CustomerDTO retval = new CustomerDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Customer cust = genericFacade.findById (_dto.getSystemId (), Customer.class);
            cust.setAddress (_dto.getAddress () != null ? _dto.getAddress () : cust.getAddress ());
            cust.setBirthdate (_dto.getBirthdate () != null ? _dto.getBirthdate () : cust.getBirthdate ());
            cust.setDisc (_dto.getDisc () != null ? _dto.getDisc () : cust.getDisc ());
            cust.setEmail (_dto.getEmail () != null ? _dto.getEmail () : cust.getEmail ());
            cust.setName (_dto.getName () != null ? _dto.getName () : cust.getName ());
            cust.setMemo (_dto.getMemo () != null ? _dto.getMemo () : cust.getMemo ());
            cust.setMemberNo (_dto.getMemberNo () != null ? _dto.getMemberNo () : cust.getMemberNo ());
            cust.setPhone (_dto.getPhone () != null ? _dto.getPhone () : cust.getPhone ());
            cust.setIsMale (_dto.getIsMale () != null ? _dto.getIsMale () : cust.getIsMale ());
            
            
//            TenantCustomerSetting tcs = new TenantCustomerSetting ();
//            tcs.setCustomer (cust);
//            tcs.setTenant (user.getTenant ());
//            tcs.setStatus (Boolean.FALSE);
            
            genericFacade.edit (cust);
            
            retval.setData (cust);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }   
    
    @DELETE
    @Path("/customers")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteCustomer(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        CustomerDTO retval = new CustomerDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Customer customer = genericFacade.findById (_systemId, Customer.class);
            if(customer == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (customer);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
//    @POST
//    @Path("/register")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    @TransactionAttribute(TransactionAttributeType.REQUIRED)
//    public Response register(
//        @FormDataParam("tenantUploadFile") InputStream _tenantUploadedInputStream,
//        @FormDataParam("tenantUploadFile") FormDataContentDisposition _tenantFileDetail,
//        @FormDataParam("uploadFile") InputStream _uploadedInputStream,
//        @FormDataParam("uploadFile") FormDataContentDisposition _fileDetail,
//        @FormDataParam("tenantName") String _tenantName,
//        @FormDataParam("tenantEmail") String _tenantEmail,
//        @FormDataParam("tenantPassword") String _tenantPassword, 
//        @FormDataParam("tenantPhoneNumber") String _tenantPhoneNumber,
//        @FormDataParam("tenantProvince") String _tenantProvince,
//        @FormDataParam("tenantRegency") String _tenantRegency,
//        @FormDataParam("tenantAddress") String _tenantAddress,
//        @FormDataParam("description") String _description,
//        @FormDataParam("name") String _name,
//        @FormDataParam("email") String _email,
//        @FormDataParam("password") String _password, 
//        @FormDataParam("phoneNumber") String _phoneNumber, 
//        @FormDataParam("sessionString") String _sessionString ) {
//        
//        System.out.println ("CALLED REGISTER");
//        
//        UserCredDTO retval = new UserCredDTO();
//        if(_tenantName == null || _tenantName.length () <= 0) {
//            retval.setErrorMessage ("ERROR, NO TENANT NAME IS SPECIFIED");
//        }
//        else if(_tenantEmail == null || _tenantEmail.length () <= 0) {
//            System.out.println ("EXCPT IN TE");
//            retval.setErrorMessage ("ERROR, NO TENANT EMAIL IS SPECIFIED");
//        }
//        else if(_tenantPassword == null || _tenantPassword.length () <= 0) {
//            retval.setErrorMessage ("ERROR, NO TENANT PASSWORD IS SPECIFIED");
//        }
//        else if(_tenantPhoneNumber == null || _tenantPhoneNumber.length () <= 0) {
//            retval.setErrorMessage ("ERROR, NO TENANT PHONE NUMBER IS SPECIFIED");
//        }
//        else if(_name == null || _name.length () <= 0) {
//            retval.setErrorMessage ("ERROR, NO NAME IS SPECIFIED");
//        }
//        else if(_email == null || _email.length () <= 0) {
//            retval.setErrorMessage ("ERROR, NO EMAIL IS SPECIFIED");
//        }
//        else if(_password == null || _password.length () <= 0) {
//            retval.setErrorMessage ("ERROR, NO PASSWORD IS SPECIFIED");
//        }
//        else if(_phoneNumber == null || _phoneNumber.length () <= 0) {
//            retval.setErrorMessage ("ERROR, NO PHONE NUMBER IS SPECIFIED");
//        }
////        else if(_tenantProvince == null || _tenantProvince.length () <= 0) {
////            retval.setErrorMessage ("ERROR, NO PROVINCE IS SPECIFIED");
////        }
////        else if(_tenantRegency == null || _tenantRegency.length () <= 0) {
////            retval.setErrorMessage ("ERROR, No CITY IS SPECIFIED");
////        }
////        else if(_tenantAddress == null || _tenantAddress.length () <= 0) {
////            retval.setErrorMessage ("ERROR, NO ADDRESS IS SPECIFIED");
////        }
//        else {
//            Tenant tenant = new Tenant ();
//            tenant.setEmail (_tenantEmail);
//            tenant.setPassword (_tenantPassword);
//            tenant.setName (_tenantName);
//            tenant.setPhoneNumber (_tenantPhoneNumber);
//            tenant.setDescription (_description);
//            tenant.setProvince (_tenantProvince);
//            tenant.setCity (_tenantRegency);
//            tenant.setAddress (_tenantAddress);
//            tenant.setProfilePicture (_tenantFileDetail != null ? _tenantFileDetail.getFileName () : null);
//            tenant.setCreatedAt (new Date());
//            tenant.setCategory (genericFacade.findById ("1", TenantCategory.class));
//            
//            UserCred usr = new UserCred ();
//            usr.setEmail (_email);
//            usr.setPassword (_password);
//            usr.setName (_name);
//            usr.setPhoneNumber (_phoneNumber);
//            usr.setProfilePicture (_fileDetail != null ? _fileDetail.getFileName () : null);
//            usr.setCreatedAt (new Date());
//            
//            tenant = genericFacade.create (tenant);
//            usr.setTenant (tenant);
//            usr = genericFacade.create (usr);
//            
//            genericFacade.refresh (tenant);
//            genericFacade.refresh (usr);
//            
//            String path = "/Users/sunwell/Documents/upload-example/" + tenant.getId () + "/";
//            File dir = new File(path);
//            if(!dir.exists ()) {
//                dir.mkdir ();
//            }
//            
//            if(_tenantUploadedInputStream != null) {
//                String uploadedFileLocation = path + _tenantFileDetail.getFileName();
//                writeToFile(_tenantUploadedInputStream, uploadedFileLocation);
//            }
//            
//            if(_uploadedInputStream != null) {
//                String uploadedFileLocation = path + _fileDetail.getFileName();
//                writeToFile(_uploadedInputStream, uploadedFileLocation);
//            }
//            retval.setData (usr);
//        }
//        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
//    }
    
    private boolean validateLogin(String _sessionString) {
        return getUser (_sessionString) != null ? true : false;
    }
    
    private User getUser(String _sessionString) {
        UserSession session = usc.getSession (_sessionString, false);
        if(session == null)
            return null;
        
        User usr = (User)session.getObject ("user");
        return usr;
    }
    
    private Object getObject(String _sessionString, String _key) {
        UserSession session = usc.getSession (_sessionString, false);
        if(session == null)
            return null;
        
        String pwd = (String)session.getObject (_key);
        return pwd;
    }
    
    
    @GET
    @Path("/users/images")
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
            UserSession us = usc.getSession (_sessionString, false);
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
        
//        String path = "./pos-image/products/" + tenant.getSystemId () + "/" + _image;
        String path = sCtx.getInitParameter ("imagePath") + "users/" + tenant.getSystemId () + "/" + _image;
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
}


