package com.example.HotelManagerment.Payment;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class TransactionStatusDTO implements Serializable {

    public String  status;
    public String message;
    public String data;
}
