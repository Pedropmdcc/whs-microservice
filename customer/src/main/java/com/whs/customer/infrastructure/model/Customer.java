package com.whs.customer.infrastructure.model;

import com.whs.customer.domain.data.TransportZone;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    private String id;
    @Indexed(unique=true)
    private String name;
    private String address;
    private TransportZone transportZone;
    @Indexed(unique=true)
    private String vat;

    public Customer(String name,String address, TransportZone transportZone, String vat){
        this.name = name;
        this.address = address;
        this.transportZone = transportZone;
        this.vat = vat;
    }
}
