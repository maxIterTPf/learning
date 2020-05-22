package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class Msg<M> implements Serializable {

    private static final long serialVersionUID = -7221643802149245353L;

    private static int SUCCESS = 0;
    private int code = -1;
    private String err;
    private M data;

    public static Msg<Void> errMsg(String err) {
        Msg<Void> msg = new Msg<>();
        msg.setErr(err);
        return msg;
    }

    public static <M> Msg<M> sucMsg(M data) {
        Msg<M> msg = new Msg<>();
        msg.setCode(SUCCESS);
        msg.setData(data);
        return msg;
    }

}
