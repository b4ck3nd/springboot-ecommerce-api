package com.kodlamiyoruz.ecomm.exception;


import com.kodlamiyoruz.ecomm.model.Address;

public class AddressException extends RuntimeException {
    public AddressException() {
        super("not found any address");
    }
    public AddressException(int id) {
        super("not found any address with this id: " + id);
    }


}
