package com.krishtech.orders.constants;

public enum SearchField {
	STATUS("status"), DISCOUNT("discount"), PRODUCT_COUNT("productcounts"), PRICE("price");
	
	private String name;
	SearchField(final String name) {
		this.name= name;
	}
	
	@Override 
    public String toString(){ 
        return name; 
    }
}
