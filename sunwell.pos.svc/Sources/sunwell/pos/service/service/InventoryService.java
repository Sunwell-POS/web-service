/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Stockservice.java
 *
 * Created on Dec 11, 2017, 4:59:19 PM
 */

package sunwell.pos.service.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sunwell.pos.entity.IncomingGood;
import sunwell.pos.entity.OutcomingGood;
import sunwell.pos.entity.OnHandStock;
import sunwell.pos.entity.ProdCategory;
import sunwell.pos.entity.bus.GenericFacade;
import sunwell.pos.entity.bus.InventoryFacade;
import sunwell.pos.entity.bus.UserCredFacade;
import sunwell.pos.entity.dto.OnHandStockListDTO;
import sunwell.pos.entity.User;
import sunwell.pos.entity.Tenant;
import sunwell.pos.entity.Product;
import sunwell.pos.entity.Warehouse;
import sunwell.pos.entity.bus.ProductFacade;
import sunwell.pos.entity.dto.IncomingGoodDTO;
import sunwell.pos.entity.dto.IncomingGoodListDTO;
import sunwell.pos.entity.dto.OnHandStockDTO;
import sunwell.pos.entity.dto.OutcomingGoodListDTO;
import sunwell.pos.entity.dto.ProductDTO;
import sunwell.pos.entity.dto.RegisterItemDTO;
import sunwell.pos.entity.dto.StandardDTO;
import sunwell.pos.entity.dto.StockMutationItemDTO;
import sunwell.pos.entity.dto.StockMutationDTO;
import sunwell.pos.entity.dto.WarehouseDTO;
import sunwell.pos.entity.dto.WarehouseListDTO;
/**
 *
 * @author Benny
 */
@Stateless
@Path("")
public class InventoryService 
{
    @EJB
    InventoryFacade stockFacade;
    
    @EJB
    ProductFacade productFacade;
    
    @EJB
    GenericFacade genericFacade;
    
    @Inject
    ServiceUtil svcUtil;
    
    @GET
    @Path("/stocks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStocks(@BeanParam OnHandStockDTO _dto) throws Exception {
        OnHandStockListDTO retval = new OnHandStockListDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<OnHandStock> listOnHand = stockFacade.find (t, svcUtil.makeOnHandStockFilter (_dto));
            retval.setData (listOnHand);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/incominggoods")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIncomingGoods(@QueryParam("sessionString") String _sessionString, 
            @QueryParam("productId") String _prodId, @QueryParam("warehouseId") String _warehouseId, 
            @QueryParam("startDate") String _startDate, @QueryParam("endDate") String _endDate ) throws Exception {
        System.out.println ("IG CALLED");
        IncomingGoodListDTO retval = new IncomingGoodListDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else if(_prodId == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_PRODUCT_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO PRODUCt SPECIFIED");
        }
        else if(_warehouseId == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_WAREHOUSE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO WAREHOUSE SPECIFIED");
        }
        else if(_startDate == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_START_DATE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO START DATE SPECIFIED");
        }
        else if(_endDate == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_END_DATE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO END DATE SPECIFIED");
        }
        else {
            
            Tenant t = user.getTenant ();
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            Product p = genericFacade.findById (_prodId, Product.class);
            Warehouse w = genericFacade.findById (_warehouseId, Warehouse.class);
            Date startDate = sdf.parse (_startDate);
            Date endDate = sdf.parse (_endDate);
            List<IncomingGood> listOnHand = stockFacade.findIncomingGoodWithinDate (t, p, w, startDate, endDate, true);
            retval.setData (listOnHand);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/outcominggoods")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOutcomingGoods(@QueryParam("sessionString") String _sessionString, 
            @QueryParam("productId") String _prodId, @QueryParam("warehouseId") String _warehouseId, 
            @QueryParam("startDate") String _startDate, @QueryParam("endDate") String _endDate ) throws Exception {
        System.out.println ("OG CALLED");
        OutcomingGoodListDTO retval = new OutcomingGoodListDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else if(_prodId == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_PRODUCT_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO PRODUCt SPECIFIED");
        }
        else if(_warehouseId == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_WAREHOUSE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO WAREHOUSE SPECIFIED");
        }
        else if(_startDate == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_START_DATE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO START DATE SPECIFIED");
        }
        else if(_endDate == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_END_DATE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO END DATE SPECIFIED");
        }
        else {
            
            Tenant t = user.getTenant ();
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
            Product p = genericFacade.findById (_prodId, Product.class);
            Warehouse w = genericFacade.findById (_warehouseId, Warehouse.class);
            Date startDate = sdf.parse (_startDate);
            Date endDate = sdf.parse (_endDate);
            List<OutcomingGood> listOnHand = stockFacade.findOutcomingGoodWithinDate (t, p, w, startDate, endDate, true);
            retval.setData (listOnHand);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/stockmutation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStockMutation(@QueryParam("sessionString") String _sessionString, 
            @QueryParam("prodCategoryId") String _prodCategoryId, @QueryParam("warehouseId") String _warehouseId, 
            @QueryParam("startDate") String _startDate, @QueryParam("endDate") String _endDate ) throws Exception {
        StockMutationDTO retval = new StockMutationDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
//        else if(_prodCategoryId == null) {
//            retval.setErrorCode (StandardDTO.ERROR_NO_PRODUCT_CATEGORY_SPECIFIED);
//            retval.setErrorMessage ("ERROR, NO PRODUCT CATEGORY SPECIFIED");
//        }
        else if(_warehouseId == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_WAREHOUSE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO WAREHOUSE SPECIFIED");
        }
        else if(_startDate == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_START_DATE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO START DATE SPECIFIED");
        }
        else if(_endDate == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_END_DATE_SPECIFIED);
            retval.setErrorMessage ("ERROR, NO END DATE SPECIFIED");
        }
        else {
            
            Tenant t = user.getTenant ();
            SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            Warehouse w = genericFacade.findById (_warehouseId, Warehouse.class);
            Date startDate = sdf.parse (_startDate);
            Date endDate = sdf.parse (_endDate);
            List<Product> products = null;
            List<StockMutationItemDTO> listSmItem = new LinkedList<>();
            if(_prodCategoryId != null) {
                ProdCategory p = genericFacade.findById (_prodCategoryId, ProdCategory.class);
                products = productFacade.findByCategory (t, p);
            }
            else {
                products = productFacade.findByTenant (t);
            }
            
            for (Product product : products) {
                StockMutationItemDTO smItem = new StockMutationItemDTO ();
                double prevTotal = stockFacade.sumTotalBeforeDate (t, product, w, startDate);
                double inc = stockFacade.sumIncomingQtyWithinDate (t, product, w, startDate, endDate);
                double out = stockFacade.sumOutcomingQtyWithinDate (t, product, w, startDate, endDate);
                
                smItem.setProduct (new ProductDTO(product));
                smItem.setBeginningBalance (prevTotal);
                smItem.setInQty (inc);
                smItem.setOutQty (out);
                
                listSmItem.add (smItem);
            }
            
            retval.setListStockMutation (listSmItem);
            
//            List<IncomingGood> listOnHand = stockFacade.findIncomingGoodWithinDate (t, p, w, startDate, endDate, true);
//            retval.setData (listOnHand);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/incominggoods")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    public Response addIncomingGood(RegisterItemDTO _dto) throws Exception {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date example = dateFormat.parse (_dto.get);
//        System.out.println ("message: " + example.toString ());
        IncomingGoodListDTO retval = new IncomingGoodListDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Warehouse warehouse = genericFacade.findById (_dto.getWarehouse ().getSystemId (), Warehouse.class);
            Date date = _dto.getIncomingDate ();
            String memo = _dto.getMemo ();
            List<IncomingGoodDTO> listIncomingGoodDTO = _dto.getListIncomingGood ();
            if(listIncomingGoodDTO == null || listIncomingGoodDTO.size () <= 0) {
                retval.setErrorCode (StandardDTO.ERROR_NO_OBJECT);
                retval.setErrorMessage ("ERROR, NO OBJECT SPECIFIED");
            }
            List<IncomingGood> listIncomingGood = new LinkedList<> ();
            for(IncomingGoodDTO _icDTO : listIncomingGoodDTO) {
                Product prod = genericFacade.findById (_icDTO.getProduct ().getSystemId (), Product.class);
                System.out.println ("PROD: " + prod.getName ());
//                IncomingGood ic = new IncomingGood ();
//                ic.setProduct (prod);
//                ic.setWarehouse (warehouse);
//                ic.setRefType (IncomingGood.REF_REGISTER_ITEM);
//                ic.setQty (_icDTO.getQty ());
//                ic.setMemo (memo);
//                ic.setUnitPrice (_icDTO.getUnitPrice ());
//                ic.setIncomingDate (date);
//                genericFacade.create (ic);
                
                OnHandStock ohs = new OnHandStock ();
                ohs.setProduct (prod);
                ohs.setWarehouse (warehouse);
                ohs.setQty (_icDTO.getQty ());
                ohs.setLastInputDate (date);
                ohs.setTenant (user.getTenant ());
                IncomingGood ic = stockFacade.addOnHandForIncomingGood (ohs, _icDTO.getUnitPrice (), memo);
                listIncomingGood.add (ic);
            }
            retval.setData (listIncomingGood);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/incominggoodsstocks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED) 
    public Response addIncomingGoodForStock(RegisterItemDTO _dto) throws Exception {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date example = dateFormat.parse (_dto.get);
//        System.out.println ("message: " + example.toString ());
        OnHandStockListDTO retval = new OnHandStockListDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            Warehouse warehouse = genericFacade.findById (_dto.getWarehouse ().getSystemId (), Warehouse.class);
            Date date = _dto.getIncomingDate ();
            String memo = _dto.getMemo ();
            List<IncomingGoodDTO> listIncomingGoodDTO = _dto.getListIncomingGood ();
            if(listIncomingGoodDTO == null || listIncomingGoodDTO.size () <= 0) {
                retval.setErrorCode (StandardDTO.ERROR_NO_OBJECT);
                retval.setErrorMessage ("ERROR, NO OBJECT SPECIFIED");
            }
            List<OnHandStock> listOnHand = new LinkedList<> ();
            for(IncomingGoodDTO _icDTO : listIncomingGoodDTO) {
                Product prod = genericFacade.findById (_icDTO.getProduct ().getSystemId (), Product.class);
                IncomingGood ic = new IncomingGood ();
                ic.setProduct (prod);
                ic.setWarehouse (warehouse);
                ic.setRefType (IncomingGood.REF_REGISTER_ITEM);
                ic.setQty (_icDTO.getQty ());
                ic.setMemo (memo);
                ic.setUnitPrice (_icDTO.getUnitPrice ());
                ic.setIncomingDate (date);
//                genericFacade.create (ic);
                
                OnHandStock ohs = new OnHandStock ();
                ohs.setProduct (prod);
                ohs.setWarehouse (warehouse);
                ohs.setQty (_icDTO.getQty ());
                ohs.setLastInputDate (date);
                ohs.setTenant (user.getTenant ());
                
                OnHandStock onhand = stockFacade.addOnHand (ohs, _icDTO.getUnitPrice (), memo);
                listOnHand.add (onhand);
            }
            retval.setData (listOnHand);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @POST
    @Path("/stocks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Response addOnHandStock(OnHandStockListDTO _dto) {
        
        OnHandStockListDTO retval = new OnHandStockListDTO();
        User user = svcUtil.getUser (_dto.getSessionString ());
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION IS ACTIVE");
        }
        else {
            List<OnHandStockDTO> listOnHandStockDTO = _dto.getListOnHandStock ();
            if(listOnHandStockDTO == null || listOnHandStockDTO.size () <= 0) {
                retval.setErrorCode (StandardDTO.ERROR_NO_OBJECT);
                retval.setErrorMessage ("ERROR, NO OBJECt SPECIFIED");
            }
            List<OnHandStock> listOnHandStock = new LinkedList<> ();
            for(OnHandStockDTO _ohsDTO : listOnHandStockDTO) {
                OnHandStock ohs = new OnHandStock ();
                ohs.setProduct (genericFacade.findById (_ohsDTO.getProduct ().getSystemId (), Product.class));
                ohs.setWarehouse (genericFacade.findById (_ohsDTO.getWarehouse ().getSystemId (), Warehouse.class));
                ohs.setQty (_ohsDTO.getQty ());
                listOnHandStock.add (stockFacade.addOnHand (ohs));
            }
            retval.setData (listOnHandStock);
        }

        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
    
    @GET
    @Path("/warehouses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWarehouses(@QueryParam("sessionString") String _sessionString,
                                  @QueryParam("tenantId") String _tenantId) throws Exception {
        WarehouseListDTO retval = new WarehouseListDTO();
        User user = svcUtil.getUser (_sessionString);
        if(user == null) {
            retval.setErrorCode (StandardDTO.ERROR_NO_LOGIN_SESSION);
            retval.setErrorMessage ("ERROR, NO LOGIN SESSION FOR THIS USER");
        }
        else {
            Tenant t = user.getTenant ();
            List<Warehouse> listWarehouses = stockFacade.findAllWarehouseByTenant (t);
            retval.setData (listWarehouses);
        }
        
        return Response.ok ().entity (retval).type (MediaType.APPLICATION_JSON).build ();
    }
}
