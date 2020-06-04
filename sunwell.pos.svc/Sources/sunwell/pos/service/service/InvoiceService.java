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
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import sunwell.pos.entity.Customer;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.OnHandStockPK;
import sunwell.pos.entity.PaymentMethod;
import sunwell.pos.entity.PaymentMethodObj;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.SalesInvoice;
import sunwell.pos.entity.SalesInvoiceLine;
import sunwell.pos.entity.SalesPayment;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.User;
import sunwell.pos.entity.Warehouse;
import sunwell.pos.entity.bus.GenericFacade;
import sunwell.pos.entity.bus.PaymentMethodFacade;
import sunwell.pos.entity.bus.SalesPaymentFacade;
import sunwell.pos.entity.bus.ProdCategoryFacade;
import sunwell.pos.entity.bus.ProductFacade;
import sunwell.pos.entity.bus.SalesInvoiceFacade;
import sunwell.pos.entity.bus.InventoryFacade;
import sunwell.pos.entity.bus.TenantFacade;
import sunwell.pos.entity.bus.UserCredFacade;
import sunwell.pos.entity.dto.PaymentMethodDTO;
import sunwell.pos.entity.dto.PaymentMethodListDTO;
import sunwell.pos.entity.dto.PaymentMethodObjDTO;
import sunwell.pos.entity.dto.PaymentMethodObjListDTO;
import sunwell.pos.entity.dto.ProdCategoryDTO;
import sunwell.pos.entity.dto.ProdCategoryListDTO;
import sunwell.pos.entity.dto.UserListDTO;
import sunwell.pos.entity.dto.ProdListDTO;
import sunwell.pos.entity.dto.ProductDTO;
import sunwell.pos.entity.dto.SalesInvoiceDTO;
import sunwell.pos.entity.dto.SalesInvoiceLineDTO;
import sunwell.pos.entity.dto.SalesInvoiceListDTO;
import sunwell.pos.entity.dto.SalesPaymentDTO;
import sunwell.pos.entity.dto.SalesPaymentListDTO;
import sunwell.pos.entity.dto.SplitBillDTO;
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
public class InvoiceService 
{
    @EJB
    private TenantFacade tenantFacade;
    
    @EJB
    private ProductFacade productFacade;
    
    @EJB
    private SalesInvoiceFacade siFacade;
    
    @EJB
    private ProdCategoryFacade prodCtgrFacade;
    
    @EJB
    private UserCredFacade userFacade;
    
    @EJB
    PaymentMethodFacade pmfacade;
    
    @EJB
    private SalesPaymentFacade spFacade;
    
    @EJB
    private InventoryFacade stockFacade;
    
    @EJB
    private GenericFacade genericFacade;
    
    @Inject
    private UserSessionContainer usc;
    
    @Resource
    private SessionContext ctx;
    
//    @GET
//    @Path("/salesinvoices")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getSalesInvoices(@QueryParam("sessionString") String _sessionString,
//                                  @QueryParam("tenantId") String _tenantId, @QueryParam("paid") String _paid) throws Exception {
//        SalesInvoiceListDTO retval = new SalesInvoiceListDTO();
//        User user = getUser (_sessionString);
//        if(user == null) {
//            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
//            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
//        }
//        else {
//            Tenant t = user.getTenant ();
//            List<SalesInvoice> invoices = siFacade.findByTenant (t);
//            retval.setData (invoices);
//        }
//        
//        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
//    }
    
    @GET
    @Path("/salesinvoices")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesInvoices(@BeanParam SalesInvoiceDTO _dto) throws Exception {
        SalesInvoiceListDTO retval = new SalesInvoiceListDTO();
        System.out.println ("PAID FILTER : " + _dto.getPaid () + " sessionString: " + _dto.getSessionString () + " voided: " + _dto.getVoided () + " discTotal: " + _dto.getDiscTotal ());
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            Util.objToMap (_dto);
            List<SalesInvoice> invoices = siFacade.find(t, Util.objToMap (_dto));
            retval.setData (invoices);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/salesinvoices")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addSalesInvoice(SalesInvoiceDTO _dto) {
        SalesInvoiceDTO retval = new SalesInvoiceDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesInvoice si = new SalesInvoice ();
            
            if(_dto.getCustomer () != null) {
                Customer cust = genericFacade.findById (_dto.getCustomer().getSystemId (), Customer.class);
                si.setCustomer (cust);
            }
            else
                si.setName (_dto.getName ());
            
            Calendar cal = Calendar.getInstance ();
            String stringDate = "#" + String.valueOf (cal.get(Calendar.YEAR)).substring (2) + cal.get(Calendar.MONTH) + cal.get(Calendar.DAY_OF_MONTH) + 
                    cal.get (Calendar.HOUR_OF_DAY) + cal.get(Calendar.MINUTE) + cal.get(Calendar.SECOND);
            Calendar invDate = Calendar.getInstance ();
//            invDate.setTime (_dto.getNoInvoiceDate ());
            si.setNoInvoice (stringDate);
            si.setTenant (user.getTenant ());
            si.setNoInvoiceDate (invDate);
            SalesInvoice entity = genericFacade.create (si);
            retval.setData (entity);
            
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/splitbill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response splitBill(SalesInvoiceDTO _dto) {
        SalesInvoiceDTO retval = new SalesInvoiceDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesInvoice si = genericFacade.findById (_dto.getSystemId (), SalesInvoice.class);
            SalesInvoice newSI = new SalesInvoice();
            Calendar invDate = Calendar.getInstance ();
          
            if(si.getSplitCount () == null)
                si.setSplitCount (1);
            else
                si.setSplitCount (si.getSplitCount () + 1);
            
            newSI.setName (si.getName ());
            newSI.setNoInvoice (si.getNoInvoice () + "-" + si.getSplitCount ());
            newSI.setTenant (si.getTenant ());
            newSI.setNoInvoiceDate (invDate);
            newSI.setCustomer (si.getCustomer ());
            List<SalesInvoiceLine> lines = new LinkedList<>();
            List<SalesInvoiceLine> deletedLines = new LinkedList<>();
            for (SalesInvoiceLineDTO newLine : _dto.getSalesInvoiceLines ()) {
                for (SalesInvoiceLine oldLine : si.getSalesInvoiceLines ()) {
                    if(oldLine.getSystemId ().equals (newLine.getSystemId ())) {
                        SalesInvoiceLine siLine = new SalesInvoiceLine ();
                        siLine.setQty (oldLine.getQty ());
                        siLine.setDiscType (oldLine.getDiscType ());
                        siLine.setDiscValue (oldLine.getDiscValue ());
                        siLine.setHasdiscount (oldLine.getHasdiscount ());
                        siLine.setMetric (oldLine.getMetric ());
                        siLine.setParent (newSI);
                        siLine.setPrice (oldLine.getPrice ());
                        siLine.setProduct (oldLine.getProduct ());
                        if(oldLine.getQty () > newLine.getQty ())
                            oldLine.setQty (oldLine.getQty () - newLine.getQty ());
                        else
                            deletedLines.add (oldLine);
                        lines.add (siLine);
                        break;
                    }
                }
            }
            
            for (SalesInvoiceLine oldLine : deletedLines) {
                si.getSalesInvoiceLines ().remove (oldLine);
                genericFacade.remove (oldLine);
            }
            
            System.out.println ("LINES SIZE: " + lines.size ());
            newSI.setSalesInvoiceLines (lines);
            genericFacade.create (newSI);
            genericFacade.flush();
            
            retval.setData (newSI);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/salesinvoices")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editSalesInvoice(SalesInvoiceDTO _dto) {
        SalesInvoiceDTO retval = new SalesInvoiceDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesInvoice si = genericFacade.findById (_dto.getSystemId (), SalesInvoice.class);
            if(si == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                if(_dto.getCustomer () != null) {
                    Customer cust = genericFacade.findById (_dto.getCustomer().getSystemId (), Customer.class);
                    si.setCustomer (cust);
                }
                else
                    si.setName (_dto.getName ());

                si.setNoInvoice (_dto.getNoInvoice () != null ? _dto.getNoInvoice () : si.getNoInvoice ());
                SalesInvoice entity = genericFacade.edit (si);
                retval.setData (entity);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @DELETE
    @Path("/salesinvoices")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteSalesInvoice(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        SalesInvoiceDTO retval = new SalesInvoiceDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesInvoice si = genericFacade.findById (_systemId, SalesInvoice.class);
            if(si == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (si);
            }
        }
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/salesinvoicelines")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addSalesInvoiceLine(SalesInvoiceLineDTO _dto) {
        
        SalesInvoiceLineDTO retval = new SalesInvoiceLineDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesInvoiceLine sil = new SalesInvoiceLine ();
            
            SalesInvoice si = genericFacade.findById (_dto.getSalesInvoiceId (), SalesInvoice.class);
            Product p = genericFacade.findById (_dto.getProduct ().getSystemId (), Product.class);
            List<Warehouse> listWarehouse = stockFacade.findAllWarehouseByTenant (user.getTenant ());
            OnHandStock ohs = stockFacade.findByProductAndWarehouse (user.getTenant (), p, listWarehouse.get (0));
            if(ohs == null || (ohs.getQty () < _dto.getQty ())) {
                retval.setErrorCode (StandardDTO.ERROR_NOT_ENOUGH_STOCK);
                retval.setErrorMessage ("ERROR, NOT ENOUGH STOCK");
            }
//            OnHandStock ohs = new OnHandStock ();
//            ohs.setProduct (p);
//            ohs.setWarehouse (listWarehouse.get (0));
//            ohs.setQty (_dto.getQty ());
//            ohs.setTenant (user.getTenant ());
//            ohs.setLastInputDate (new Date());
//            System.out.println ("P: " + p + " W " + listWarehouse.get (0) + " ");
//            if(stockFacade.removeOnHand (ohs, si.getSystemId ()) == null) {
//                retval.setErrorCode (StandardDTO.ERROR_NOT_ENOUGH_STOCK);
//                retval.setErrorMessage ("ERROR, NOT ENOUGH STOCK");
//            }
            else {          
                si.getSalesInvoiceLines ().add (sil);
                sil.setParent (si);
                sil.setProduct (p);
                sil.setPrice (_dto.getPrice ());
                sil.setQty (_dto.getQty ());
                sil.setMetric (_dto.getMetric ());
                sil.setDiscType (_dto.getDiscType ());
                sil.setDiscValue (_dto.getDiscValue ());
                sil = genericFacade.create (sil);
                retval.setData (sil);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/salesinvoicelines")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editSalesInvoiceLine(SalesInvoiceLineDTO _dto) {
        
        SalesInvoiceLineDTO retval = new SalesInvoiceLineDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
//            SalesInvoiceLine sil = new SalesInvoiceLine ();
            SalesInvoiceLine oldSil = genericFacade.findById (_dto.getSystemId (), SalesInvoiceLine.class);
            
            SalesInvoice si = genericFacade.findById (_dto.getSalesInvoiceId (), SalesInvoice.class);
            Product p = genericFacade.findById (_dto.getProduct ().getSystemId (), Product.class);
            List<Warehouse> listWarehouse = stockFacade.findAllWarehouseByTenant (user.getTenant ());
            OnHandStock ohs = stockFacade.findByProductAndWarehouse (user.getTenant (), p, listWarehouse.get (0));
            double currQty = ohs == null ? 0 : ohs.getQty ();
            if((currQty + oldSil.getQty ()) < _dto.getQty ()) {
                retval.setErrorCode (StandardDTO.ERROR_NOT_ENOUGH_STOCK);
                retval.setErrorMessage ("ERROR, NOT ENOUGH STOCK");
            }
            else {          
//                si.getSalesInvoiceLines ().add (sil);
//                sil.setParent (si);

//                if(_dto.getQty () != oldSil.getQty ()) {
//                    OnHandStock oh = new OnHandStock ();
//                    oh.setProduct (p);
//                    oh.setWarehouse (listWarehouse.get (0));
//                    oh.setTenant (user.getTenant ());
//                    oh.setLastInputDate (new Date());
//                    if(_dto.getQty () > oldSil.getQty ()) {
//                        oh.setQty (_dto.getQty () - oldSil.getQty ());
//                        System.out.println ("P: " + p + " W " + listWarehouse.get (0) + " ");
//                        if(stockFacade.removeOnHand (oh, si.getSystemId ()) == null) {
//                            retval.setErrorCode (StandardDTO.ERROR_NOT_ENOUGH_STOCK);
//                            retval.setErrorMessage ("ERROR, NOT ENOUGH STOCK");
//                            ctx.setRollbackOnly ();
//                        }
//                    }
//                    else {
//                        oh.setQty (oldSil.getQty () - _dto.getQty ());
//                        stockFacade.addOnHand (oh, 0, null);
//                    }
//                }
                
                if(retval.getErrorCode () == null) {
                    oldSil.setProduct (p);
                    oldSil.setPrice (_dto.getPrice ());
                    oldSil.setQty (_dto.getQty ());
                    oldSil.setMetric (_dto.getMetric ());
                    oldSil.setDiscType (_dto.getDiscType ());
                    oldSil.setDiscValue (_dto.getDiscValue ());
                    oldSil = genericFacade.edit (oldSil);
                    retval.setData (oldSil);
                }
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/paymentmethods")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBasicPaymentMethod(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        PaymentMethodListDTO retval = new PaymentMethodListDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<PaymentMethod> methods = pmfacade.findBasicPaymentMethod ();
            retval.setData (methods);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/paymentmethodobjs")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPaymentMethod(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        PaymentMethodObjListDTO retval = new PaymentMethodObjListDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<PaymentMethodObj> methods = pmfacade.findByTenant (t);
            retval.setData (methods);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/paymentmethodobjs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addPaymentMethod(PaymentMethodObjDTO _dto) {
        PaymentMethodObjDTO retval = new PaymentMethodObjDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            PaymentMethodObj pmo = new PaymentMethodObj ();
            PaymentMethodDTO parentDTO = _dto.getParent ();
            PaymentMethod pm = genericFacade.findById (parentDTO.getSystemId (), PaymentMethod.class);
            pmo.setParent (pm);
            pmo.setName (_dto.getName ());
            pmo.setMemo (_dto.getMemo ());
            pmo.setStatus (_dto.getStatus ());
            pmo.setHasDisc (_dto.getHasDisc ());
            pmo.setDiscType (PaymentMethodObj.DISC_TYPE_PERCENTAGE);
            pmo.setTenant (user.getTenant ());
            PaymentMethodObj entity = genericFacade.create (pmo);
            retval.setData (entity);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/paymentmethodobjs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editPaymentMethod(PaymentMethodObjDTO _dto) {
        PaymentMethodObjDTO retval = new PaymentMethodObjDTO();
        System.out.println ("MEMO: " + _dto.getMemo ());
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            PaymentMethodObj pmo = genericFacade.findById (_dto.getSystemId (), PaymentMethodObj.class);
            if(pmo == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                PaymentMethodDTO parentDTO = _dto.getParent ();
                PaymentMethod pm = genericFacade.findById (parentDTO.getSystemId (), PaymentMethod.class);
                pmo.setParent (pm);
                pmo.setName (_dto.getName () != null ? _dto.getName () : pmo.getName ());
                pmo.setMemo (_dto.getMemo () != null ? _dto.getMemo () : pmo.getMemo ());
                pmo.setStatus (_dto.getStatus () != null ? _dto.getStatus () : pmo.getStatus ());
                pmo.setHasDisc (_dto.getHasDisc () != null ? _dto.getHasDisc () : pmo.getHasDisc ());
                PaymentMethodObj entity = genericFacade.edit (pmo);
                retval.setData (entity);
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @DELETE
    @Path("/paymentmethodobjs")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deletePaymentMethod(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        PaymentMethodObjDTO retval = new PaymentMethodObjDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            PaymentMethodObj pmo = genericFacade.findById (_systemId, PaymentMethodObj.class);
            if(pmo == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (pmo);
            }
        }
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/salespayments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesPayment(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        SalesPaymentListDTO retval = new SalesPaymentListDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<SalesPayment> payments = spFacade.findByTenant (t);
            System.out.println ("SP SIZE: " + payments.size ());
            retval.setData (payments);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/salespayments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addSalesPayment(SalesPaymentDTO _dto) {
        
        SalesPaymentDTO retval = new SalesPaymentDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesPayment sp = new SalesPayment ();
            PaymentMethod pm = genericFacade.findById (_dto.getPaymentMethod ().getSystemId (), PaymentMethod.class);
            SalesInvoice si = genericFacade.findById (_dto.getParent ().getSystemId (), SalesInvoice.class);
            List<Warehouse> listWarehouse = stockFacade.findAllWarehouseByTenant (user.getTenant ());
            List<OnHandStock> listRemovedOnHand = new LinkedList<>();
            String errMessage = "";
            Map<Product, Double> accumulatedProductQtys = new HashMap<>();
            for (SalesInvoiceLine sil : si.getSalesInvoiceLines ()) {
                OnHandStock ohs = new OnHandStock ();
                ohs.setProduct (sil.getProduct ());
                ohs.setWarehouse (listWarehouse.get (0));
                ohs.setQty (sil.getQty ());
                ohs.setTenant (user.getTenant ());
                ohs.setLastInputDate (new Date()); 
                listRemovedOnHand.add (ohs);
                OnHandStock currOH = genericFacade.findById (new OnHandStockPK(sil.getProduct ().getSystemId (), listWarehouse.get (0).getSystemId ()), 
                        OnHandStock.class);
                Double accumulatedQty = accumulatedProductQtys.get (sil.getProduct ());
                
                // kalau -1 udah nggak cukup jadi nggak perlu diperiksa lagi supaya pesan errornya nggak berulang - ulang
                
                if(accumulatedQty == null) //{
                    accumulatedQty = 0.0;
                if(!(accumulatedQty < 0)) {
                    accumulatedQty += sil.getQty ();

                    if(currOH == null || (currOH.getQty () < accumulatedQty)) {
                        double qty = currOH == null ? 0 : currOH.getQty ();
                        accumulatedProductQtys.put (sil.getProduct (), -1.0);
                        errMessage += "\nQty. " + sil.getProduct ().getName () + " = " + qty;
                    }
                    else
                        accumulatedProductQtys.put (sil.getProduct (), accumulatedQty);
                }
            }
            
            if(errMessage.length () > 0) {
                errMessage = " Error, not enough stock" + errMessage;
                retval.setErrorCode (StandardDTO.ERROR_NOT_ENOUGH_STOCK);
                retval.setErrorMessage (errMessage);
                ctx.setRollbackOnly ();
            }
            else {
                for(OnHandStock removedOH : listRemovedOnHand) {
                    if(stockFacade.removeOnHand (removedOH, si.getSystemId ()) == null) {
                        retval.setErrorCode (StandardDTO.ERROR_NOT_ENOUGH_STOCK);
                        retval.setErrorMessage ("ERROR, NOT ENOUGH STOCK");
                        ctx.setRollbackOnly ();
    //                    genericFacade.rollback ();
                        break;
                    }
                }
                
                if(retval.getErrorCode () == null) {
                    sp.setPaymentMethod (pm);
                    sp.setParent (si);
                    sp.setAmount (_dto.getAmount ());
                    sp.setCardNumber (_dto.getCardNumber ());
                    sp.setNameCardHolder (_dto.getNameCardHolder ());
                    Calendar paidDate = Calendar.getInstance ();
                    paidDate.setTime (new Date());
                    sp.setPaidDate (paidDate);
                    sp.setMemo (_dto.getMemo ());
                    si.setPaid (true);
                    SalesPayment entity = genericFacade.create (sp);
                    genericFacade.flush ();
                    retval.setData (entity);
                }
            }
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @PUT
    @Path("/salespayments")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response editSalesPayment(SalesPaymentDTO _dto) {
        SalesPaymentDTO retval = new SalesPaymentDTO();
        User user = getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesPayment sp = genericFacade.findById (_dto.getSystemId (), SalesPayment.class);
            if(sp == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED SALES PAYMENT");
            }
            else {
                PaymentMethod pm = genericFacade.findById (_dto.getPaymentMethod ().getSystemId (), PaymentMethod.class);
                SalesInvoice si = genericFacade.findById (_dto.getParent ().getSystemId (), SalesInvoice.class);
                sp.setPaymentMethod (pm != null ? pm : sp.getPaymentMethod ());
                sp.setAmount (_dto.getAmount () != null ? _dto.getAmount () : null);
                sp.setCardNumber (_dto.getCardNumber () != null ? _dto.getCardNumber () : sp.getCardNumber ());
                sp.setNameCardHolder (_dto.getNameCardHolder () != null ? _dto.getNameCardHolder () : sp.getNameCardHolder ());
                sp.setMemo (_dto.getMemo () != null ? _dto.getMemo () : sp.getMemo ());                
                SalesPayment entity = genericFacade.create (sp);
                retval.setData (entity);
            }
        }
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @DELETE
    @Path("/salespayments")
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response deleteSalesPayment(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("systemId") String _systemId) throws Exception {
        SalesPaymentDTO retval = new SalesPaymentDTO();
        User user = getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            SalesPayment sp = genericFacade.findById (_systemId, SalesPayment.class);
            if(sp == null) {
                retval.setErrorCode (StandardDTO.ERROR_CANT_FIND_SPECIFIED_OBJECT);
                retval.setErrorMessage ("ERROR, CAN't FIND THE SPECIFIED CATEGORY");
            }
            else {
                genericFacade.deleteObject (sp);
            }
        }
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }

            
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
        
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

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
    
    private void writeToFile(byte[] _bytes, String uploadedFileLocation) {

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
}
