package com.sample.Mytest.Services;

import com.test.gen.Request.Datum;
import com.test.gen.Request.InventoryPicture;
import com.test.gen.Request.InventoryRequest;
import com.test.gen.Response.InventoryResponse;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Component
public class InventoryService {
    Logger logger=Logger.getLogger(String.valueOf(InventoryService.class));

    public InventoryResponse inventoryServiceImpl(InventoryRequest inventoryRequest) throws Throwable {


        int sumQty=0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
        LocalDate sysDate= LocalDate.now();
        logger.info("Creating the Inventory Picture"+sysDate);
        sysDate.plusDays(30);



        if((inventoryRequest.getReqDate()).compareTo(String.valueOf(sysDate.plusDays(30)))>0){

            logger.info("If in case the requested Date is 30 days greater than the Sysdate , throw exception");
            Exception exception= new Exception();
            throw exception;

        }
        InventoryResponse inventoryResponse=new InventoryResponse();
        InventoryPicture inventoryPicture = new InventoryPicture();
        createInventoryPicture(inventoryPicture);
        Optional<Boolean> prodIdValidation=Optional.of(
                inventoryRequest.getProductId().equalsIgnoreCase("Prod1"));
        Optional<String> productType=Optional.of(
                inventoryRequest.getProdName());
        Optional<String> reqDateCheck=Optional.of(
                inventoryRequest.getReqDate());


        if(prodIdValidation.get()
            && productType.isPresent() && reqDateCheck.isPresent() ){
            {
                String strProductName=productType.get();
                logger.info("strProductName==>"+strProductName);
                String strReqDate=reqDateCheck.get();
                Date reqDate = formatter.parse(strReqDate);
                if(inventoryPicture.getData().stream().filter(a->a.getProdName().
                        equalsIgnoreCase(productType.get())).findFirst().isPresent()) {
                    LocalDate lRequestedDate = LocalDate.parse(strReqDate);
                    LocalDate newDate=lRequestedDate.plusDays(10);
                    List<String> lAvailableQty=inventoryPicture.getData().stream()
                            .filter(a->a.getAvailDate()
                                    .compareTo(newDate.toString())>0)
                                    .filter(b->b.getProdName().equalsIgnoreCase("Shirt"))
                                    .map(c->c.getAvailQty()).collect(Collectors.toList());


                    logger.info("lAvailableQty==>"+lAvailableQty);
                    for(String availQty:lAvailableQty){
                        Integer integer=Integer.parseInt(availQty);
                        sumQty=sumQty+integer;
                    }

                    setResponse(inventoryResponse,inventoryRequest,sumQty);

                }

            }

        }
        return inventoryResponse;
    }

    private void setResponse(InventoryResponse inventoryResponse,InventoryRequest inventoryRequest,int sumQty){
        inventoryResponse.setProdName(inventoryRequest.getProdName());
        inventoryResponse.setProductId("Prod1");
        inventoryResponse.setAvailQty(String.valueOf(sumQty));

    }

    private void createInventoryPicture(InventoryPicture inventoryPicture) {

        logger.info("Creating the Inventory Picture");
        List<Datum> data=new ArrayList<Datum>();

        Datum datum=new Datum();
        datum.setAvailDate("2021-03-19");
        datum.setProdName("Shirt");
        datum.setAvailQty("10");
        data.add(datum);

        Datum datum1=new Datum();
        datum1.setAvailDate("2021-03-21");
        datum1.setProdName("Shirt");
        datum1.setAvailQty("20");
        data.add(datum1);

        Datum datum2=new Datum();
        datum2.setAvailDate("2021-03-29");
        datum2.setProdName("Shirt");
        datum2.setAvailQty("20");
        data.add(datum2);
        inventoryPicture.setData(data);
    }


}
