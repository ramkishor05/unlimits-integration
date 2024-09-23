package com.brijframework.integration.model.pixel;
import java.util.ArrayList;
import java.util.List;
public class PixelPageDetail {
    private Integer totalResults;
    private Integer page;
    private Integer perPage;
    private List<PixelPhoto> photos = new ArrayList<PixelPhoto>();
    private String nextPage;
    public Integer getTotalResults() {
    	if(totalResults==null) {
    		totalResults=0;
    	}
        return totalResults;
    }
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
    public Integer getPage() {
    	if(page==null) {
    		page=0;
    	}
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getPerPage() {
    	if(perPage==null) {
    		perPage=0;
    	}
        return perPage;
    }
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
    public List<PixelPhoto> getPhotos() {
    	if(photos==null) {
    		photos=new ArrayList<PixelPhoto>();
    	}
        return photos;
    }
    public void setPhotos(List<PixelPhoto> photos) {
        this.photos = photos;
    }
    public String getNextPage() {
        return nextPage;
    }
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }
}
