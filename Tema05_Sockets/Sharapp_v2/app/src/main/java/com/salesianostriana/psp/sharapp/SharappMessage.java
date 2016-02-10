package com.salesianostriana.psp.sharapp;

import java.io.Serializable;
import java.util.Date;

public class SharappMessage implements Serializable {

    public int typeMessage;
    public String userName;
    public String fileName;
    public byte[] content;
    public String message;
    public Date date;

}
