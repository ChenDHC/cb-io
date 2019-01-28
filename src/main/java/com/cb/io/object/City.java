package com.cb.io.object;

import java.io.Serializable;

/**
 * @author ChenOT
 * @date 2019-01-25
 * @see
 * @since
 */
public class City implements Serializable{
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString(){
        return code+name;
    }
}
