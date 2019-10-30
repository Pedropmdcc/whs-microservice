package com.whs.customer.api.dto.response;

import com.whs.customer.domain.data.TransportZone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResponse {

    private String name;
    private String address;
    private TransportZone transportZone;
    private String vat;
    private String status;

}
