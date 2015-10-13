package com.exkulo.banciyuan.ui.model;

import java.io.Serializable;

/**
 * Created by exkulo on 9/15/2015.
 */
public class BaseModel implements Serializable{

    private final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected int id;

}
