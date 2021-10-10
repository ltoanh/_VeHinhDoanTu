package model;

import constant.StreamData;

/**
 * gui/nhan object
 * @author whiwf
 */
public class ObjectModel<T> implements java.io.Serializable{
    private static final long serialVersionUID = 6529685098267757690L;
    
    private String type;
    private T t;

    public ObjectModel(String type, T t) {
        this.type = type;
        this.t = t;
    }

    public String getType() {
        return type;
    }

    public T getT() {
        return t;
    }
    
}
