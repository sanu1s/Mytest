package com.sample.Mytest.Services;

import com.test.gen.Request.OrderData;
import com.test.gen.Request.OrderDatum;
import com.test.gen.Request.OrderRequest;
import com.test.gen.Response.Order;
import com.test.gen.Response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class OrderResponseService {


    Logger logger=Logger.getLogger(String.valueOf(OrderResponse.class));
    int sumQty=0;
    int qtyCheck=0;
    int temp=99999;
    Map<String, String> productQtyMap=new HashMap<String,String>();

    public OrderResponse orderResponseImpl(OrderRequest orderRequest){

        OrderResponse orderResponse=new OrderResponse();
        List<Order> orderList=new ArrayList<Order>();
        OrderData orderData=createOrderData();
        if(("MAX_SALE").equalsIgnoreCase(orderRequest.getStatName())){
            orderData.getOrderData().stream()
                    .map(a->checkProductId(a))
                    .collect(Collectors.toList());
            logger.info("The Final Map is"+productQtyMap.toString());

            for(Map.Entry<String,String> iterate:productQtyMap.entrySet()) {
                String strQty = iterate.getValue();
                int iQty = Integer.parseInt(strQty);
                if (iQty > qtyCheck) {
                    logger.info("Qty==>" + iQty);
                    qtyCheck = iQty;
                    orderResponse.setProductId(iterate.getKey());
                }
            }
            logger.info("The Max Sold Out Quantity IS"+String.valueOf(qtyCheck));
            logger.info("The Max Sold Out Quantity IS"+orderResponse.getProductId());
            List<OrderDatum> orderDatumList=orderData.getOrderData().stream().filter(a->a.getProductId()
                    .equalsIgnoreCase(orderResponse.getProductId()))
                    .collect(Collectors.toList());

            logger.info("orderDatumList==>"+orderDatumList.toString());
            for(OrderDatum str:orderDatumList){
                Order order=new Order();
                order.setQuantity(str.getQuantity());
                order.setCreateDate(str.getCreateDate());
                order.setOrderNo(str.getOrderNo());
                orderList.add(order);
            }
            orderResponse.setOrderList(orderList);

        }

        return orderResponse;
    }


    private Object checkProductId(OrderDatum orderData) {

        String strQty=orderData.getQuantity();
        String strProductId=orderData.getProductId();
        logger.info("Product ID is==>"+strProductId);
        logger.info("Qty  is==>"+strQty);
        if(!productQtyMap.isEmpty()
                && Objects.nonNull(productQtyMap.get(strProductId))){
                sumQty= Integer.parseInt(productQtyMap.get(strProductId))+Integer.parseInt(strQty);
                logger.info("Qty  inside==>"+productQtyMap.get(strProductId));
                logger.info("sumQty==>"+sumQty);
                productQtyMap.put(strProductId,String.valueOf(sumQty));
        }else{
            logger.info("Map is Empty");
            productQtyMap.put(strProductId,strQty);
        }


        return orderData;
    }

    private OrderData createOrderData() {
        OrderData orderData=new OrderData();
        List<OrderDatum> datumList=new ArrayList<OrderDatum>();
        OrderDatum orderDatum=new OrderDatum();
        orderDatum.setCreateDate("2021-03-16");
        orderDatum.setProductId("Product1");
        orderDatum.setQuantity("10");
        orderDatum.setOrderNo("Order1");
        datumList.add(orderDatum);

        OrderDatum orderDatum1=new OrderDatum();
        orderDatum1.setCreateDate("2021-03-19");
        orderDatum1.setProductId("Product2");
        orderDatum1.setQuantity("5");
        orderDatum1.setOrderNo("Order2");
        datumList.add(orderDatum1);

        OrderDatum orderDatum2=new OrderDatum();
        orderDatum2.setCreateDate("2021-03-16");
        orderDatum2.setProductId("Product1");
        orderDatum2.setQuantity("30");
        orderDatum2.setOrderNo("Order3");
        datumList.add(orderDatum2);

        OrderDatum orderDatum3=new OrderDatum();
        orderDatum3.setCreateDate("2021-03-20");
        orderDatum3.setProductId("Product4");
        orderDatum3.setQuantity("20");
        orderDatum3.setOrderNo("Order4");
        datumList.add(orderDatum3);

        OrderDatum orderDatum4=new OrderDatum();
        orderDatum4.setCreateDate("2021-03-16");
        orderDatum4.setProductId("Product2");
        orderDatum4.setQuantity("20");
        orderDatum4.setOrderNo("Order5");
        datumList.add(orderDatum4);
        orderData.setOrderData(datumList);
        return orderData;

    }
}
