package com.whs.warehouse.api.dto.errors;

public class WeightLimitsException extends RuntimeException {
    public WeightLimitsException (final String msg) {
        super("Invalid weight, please input weight between 10.0 kilos and 100.0 kilos");
    }
}
