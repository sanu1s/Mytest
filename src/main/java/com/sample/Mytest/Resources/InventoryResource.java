package com.sample.Mytest.Resources;

import com.sample.Mytest.Services.InventoryService;
import com.test.gen.Request.InventoryRequest;
import com.test.gen.Response.InventoryResponse;
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

    @PostMapping(value="/getInvPicture")
    public InventoryResponse postInvPicture(@RequestBody InventoryRequest inventoryRequest) throws Throwable {
        return inventoryService.inventoryServiceImpl(inventoryRequest);
    }
}
