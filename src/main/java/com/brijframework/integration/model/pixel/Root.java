package com.brijframework.integration.model.pixel;
import java.util.ArrayList;
import java.util.List;
public class Root {
    private Integer totalResults;
    private Integer page;
    private Integer perPage;
    private List<Photo> photos = new ArrayList<Photo>();
    private String nextPage;
    public Integer getTotalResults() {
        return totalResults;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPerPage() {
        return perPage;
    }
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
    public List<Photo> getPhotos() {
        return photos;
    }
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    public String getNextPage() {
        return nextPage;
    }
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
