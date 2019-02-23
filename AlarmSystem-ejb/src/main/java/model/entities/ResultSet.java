package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultSet<T> implements Serializable {


    private List data;
    private Pagination pagination;

    public ResultSet() {
        this.data = new ArrayList<T>();
        this.pagination = new Pagination();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addData(T data){
        if(this.data ==  null)
            this.data = new ArrayList<T>();
        this.data.add(data);
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
