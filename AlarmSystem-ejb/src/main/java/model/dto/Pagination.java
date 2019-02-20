package model.dto;

public class Pagination {

    private int pageSize;
    private int total;
    private int currentPage;


    public Pagination(int pageSize, int total, int currentPage) {
        this.pageSize = pageSize;
        this.total = total;
        this.currentPage = currentPage;
    }

    public Pagination() {
        pageSize = 0;
        total = 0;
        currentPage = 0;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
