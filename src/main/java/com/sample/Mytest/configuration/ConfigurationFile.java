package com.sample.Mytest.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Component
@ConfigurationProperties("scanevent")
public class ConfigurationFile {

    public String Received;

    public void setDelivered(String delivered) {
        Delivered = delivered;
    }

    public void setDeparted(String departed) {
        Departed = departed;
    }

    public void setLoaded(String loaded) {
        Loaded = loaded;
    }

    public void setOrderCreated(String orderCreated) {
        OrderCreated = orderCreated;
    }

    public String Delivered;
    public String Departed;

    public String getDelivered() {
        return Delivered;
    }

    public String getDeparted() {
        return Departed;
    }

    public String getLoaded() {
        return Loaded;
    }

    public String getOrderCreated() {
        return OrderCreated;
    }

    public String Loaded;
    public String OrderCreated;

    public String getDelivered_DLVD() {
        return Delivered_DLVD;
    }

    public String getLoaded_Loaded() {
        return Loaded_Loaded;
    }

    public void setDelivered_DLVD(String delivered_DLVD) {
        Delivered_DLVD = delivered_DLVD;
    }

    public void setLoaded_Loaded(String loaded_Loaded) {
        Loaded_Loaded = loaded_Loaded;
    }

    private String Delivered_DLVD;
    private String Loaded_Loaded;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public List<String> getlCarrierService() {
        return lCarrierService;
    }

    public void setlCarrierService(List<String> lCarrierService) {
        this.lCarrierService = lCarrierService;
    }

    List<String>lCarrierService=new ArrayList<String>();

    public String getCarrierscan() {
        return carrierscan;
    }

    public String getServicescan() {
        return servicescan;
    }

    public void setReceived(String received) {
        Received = received;
    }

    private String carrierscan;

    public void setCarrierscan(String carrierscan) {
        this.carrierscan = carrierscan;
    }

    public void setServicescan(String servicescan) {
        this.servicescan = servicescan;
    }

    private String servicescan;

    public String getReceived() {
        return Received;
    }
}
