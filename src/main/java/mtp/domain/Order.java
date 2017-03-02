package mtp.domain;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table("fixedorder")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    private UUID id;

    private String store;

    private String product;

    private String qty;
	
	private String date;
	
/*
    private String orderId;

    public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
*/	
	public Order() {
    }

    public Order(String store) {
        this.store = store;
    }

    public UUID getId() {
        return id;
    }

    public String getStore() {
        return this.store;
    }

  

    public String getProduct() {
        return this.product;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStore(String store) {
        this.store = store;
    }

    

    public void setProduct(String product) {
        this.product = product;
    }

    public String getQty() {
        return this.qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
	
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}