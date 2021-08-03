package com.sample.Mytest.Services;

import com.test.gen.Request.*;
import com.test.gen.Response.StoreInventoryResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class StoreInventoryService {

    Logger logger=Logger.getLogger(String.valueOf(StoreInventoryService.class));

    public StoreInventoryResponse storeInventoryServiceImpl(StoreInventoryRequest storeInventoryRequest){
    StoreInventoryResponse storeInventoryResponse=new StoreInventoryResponse();
    storeInventoryResponse.setProductId(storeInventoryRequest.getProductId());
    storeInventoryResponse.setReqDate(storeInventoryRequest.getReqDate());
    storeInventoryResponse.setReqQty(storeInventoryRequest.getReqQty());
    storeInventoryResponse.setStoreNo(storeInventoryRequest.getStoreNo());

    AvailabilityPicture availabilityPicture=createAvailabilityPicture();
    CapacityPicture capacityPicture=createCapacityPicture();

        Executor executor= Executors.newFixedThreadPool(10);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                logger.info("Running the Executor Service");
                List<String> lavailability=getAvailability(availabilityPicture,storeInventoryRequest);
                List<String> lcapacity=getCapacity(capacityPicture,storeInventoryRequest);

                boolean bNotAvailable=lavailability.stream().findFirst().orElse("0").equalsIgnoreCase("0");
                boolean bNoCapacity=lcapacity.stream().findFirst().orElse("0").equalsIgnoreCase("0");
                if(bNotAvailable || bNoCapacity){
                    storeInventoryResponse.setStatus("NotAvailable");
                }
                else if(!bNotAvailable && !bNoCapacity){
                    storeInventoryResponse.setStatus("Available");
                }
                else{
                    storeInventoryResponse.setStatus("NotAvailable");
                }

            }
        });

    return storeInventoryResponse;
    }

    private List<String> getCapacity(CapacityPicture capacityPicture, StoreInventoryRequest storeInventoryRequest) {
        return capacityPicture.getCapacityData().stream()
                .filter(a->a.getStoreNo().equalsIgnoreCase(storeInventoryRequest.getStoreNo()))
                .filter(b->b.getDate().equalsIgnoreCase(storeInventoryRequest.getReqDate()))
                .filter(c->c.getProductId().equalsIgnoreCase(storeInventoryRequest.getProductId()))
                .map(d->d.getNoOfOrdersAccepted()).collect(Collectors.toList());

    }

    private List<String> getAvailability(AvailabilityPicture availabilityPicture, StoreInventoryRequest storeInventoryRequest) {
        return availabilityPicture.getAvailabilityData().stream()
                .filter(a->a.getStoreNo().equalsIgnoreCase(storeInventoryRequest.getStoreNo()))
                .filter(b->b.getDate().equalsIgnoreCase(storeInventoryRequest.getReqDate()))
                .filter(c->c.getProductId().equalsIgnoreCase(storeInventoryRequest.getProductId()))
                .map(d->d.getAvailableQty()).collect(Collectors.toList());

    }

    public AvailabilityPicture createAvailabilityPicture(){
        AvailabilityPicture availabilityPicture=new AvailabilityPicture();
        List<AvailabilityDatum> availabilityDatumList=new ArrayList<AvailabilityDatum>();

        AvailabilityDatum availabilityDatum= new AvailabilityDatum();
        availabilityDatum.setAvailableQty("1");
        availabilityDatum.setProductId("Prod1");
        availabilityDatum.setStoreNo("Store001");
        availabilityDatum.setDate("2021-02-19");

        AvailabilityDatum availabilityDatum1= new AvailabilityDatum();
        availabilityDatum1.setAvailableQty("3");
        availabilityDatum1.setProductId("Prod1");
        availabilityDatum1.setStoreNo("Store001");
        availabilityDatum1.setDate("2021-02-20");


        AvailabilityDatum availabilityDatum2= new AvailabilityDatum();
        availabilityDatum2.setAvailableQty("0");
        availabilityDatum2.setProductId("Prod2");
        availabilityDatum2.setStoreNo("Store001");
        availabilityDatum2.setDate("2021-02-21");

        availabilityDatumList.add(availabilityDatum);
        availabilityDatumList.add(availabilityDatum1);
        availabilityDatumList.add(availabilityDatum2);
        availabilityPicture.setAvailabilityData(availabilityDatumList);
        return availabilityPicture;
    }

    public CapacityPicture createCapacityPicture(){
        CapacityPicture capacityPicture=new CapacityPicture();

        List<CapacityDatum> capacityData=new ArrayList<CapacityDatum>();

        CapacityDatum capacityDatum= new CapacityDatum();
        capacityDatum.setNoOfOrdersAccepted("0");
        capacityDatum.setProductId("Prod1");
        capacityDatum.setStoreNo("Store001");
        capacityDatum.setDate("2021-02-19");

        CapacityDatum capacityDatum1= new CapacityDatum();
        capacityDatum1.setNoOfOrdersAccepted("2");
        capacityDatum1.setProductId("Prod1");
        capacityDatum1.setStoreNo("Store001");
        capacityDatum1.setDate("2021-02-20");


        CapacityDatum capacityDatum2= new CapacityDatum();
        capacityDatum2.setNoOfOrdersAccepted("2");
        capacityDatum2.setProductId("Prod1");
        capacityDatum2.setStoreNo("Store001");
        capacityDatum2.setDate("2021-02-21");


        CapacityDatum capacityDatum3= new CapacityDatum();
        capacityDatum3.setNoOfOrdersAccepted("0");
        capacityDatum3.setProductId("Prod1");
        capacityDatum3.setStoreNo("Store001");
        capacityDatum3.setDate("2021-02-22");

        capacityData.add(capacityDatum);
        capacityData.add(capacityDatum1);
        capacityData.add(capacityDatum2);
        capacityData.add(capacityDatum3);
        capacityPicture.setCapacityData(capacityData);
        return capacityPicture;

    }
}
