package com.sample.Mytest.Resources;

import com.sample.Mytest.Services.InventoryService;
import com.sample.Mytest.Services.OrderResponseService;
import com.sample.Mytest.Services.StoreInventoryService;
import com.test.gen.Request.InventoryRequest;
import com.test.gen.Request.OrderRequest;
import com.test.gen.Request.StoreInventoryRequest;
import com.test.gen.Response.InventoryResponse;
import com.test.gen.Response.OrderResponse;
import com.test.gen.Response.StoreInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class InventoryResource {

  @Autowired
  InventoryService inventoryService;

  @Autowired
  StoreInventoryService storeInventoryService;

  @Autowired
  OrderResponseService orderResponseService;

    @PostMapping(value="/getInvPicture")
    public InventoryResponse postInvPicture(@RequestBody InventoryRequest inventoryRequest) throws Throwable {
        return inventoryService.inventoryServiceImpl(inventoryRequest);
    }

  @PostMapping(value="/getProdAvailability")
    public StoreInventoryResponse storeAvailabilityPicture(@RequestBody StoreInventoryRequest storeInventoryRequest){
      return storeInventoryService.storeInventoryServiceImpl(storeInventoryRequest);

    }
  @PostMapping(value="/getOrderStats")
  public OrderResponse orderResponse(@RequestBody OrderRequest orderRequest){
    return orderResponseService.orderResponseImpl(orderRequest);

  }
}
