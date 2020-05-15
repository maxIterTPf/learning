package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Msg<M> implements Serializable {
    private static final long serialVersionUID = -7221643802149245353L;
    public static int SUCCESS = 0;
    private int code = -1;
    private String err;
    private M data;
}
