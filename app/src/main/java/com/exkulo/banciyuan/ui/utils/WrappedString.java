package com.exkulo.banciyuan.ui.utils;

import com.exkulo.banciyuan.ui.model.BaseModel;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by exkulo on 10/4/2015.
 */
@DatabaseTable(tableName = "_string")
public class WrappedString extends BaseModel{

    public WrappedString(){}

    public WrappedString(String string) {
        s = string;
    }

    @DatabaseField(columnName = "s", foreign = true) private String s;
    @DatabaseField(generatedId = true, columnName = "_id") private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
