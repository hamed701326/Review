package ir.techco.review.utils;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResponse<T> {
    private long totalSize;
    private int totalPage;
    private int size;
    private List<T> contents;

    public PageResponse(Page<T> page){
        this.totalPage  = page.getTotalPages();
        this.totalSize  = page.getTotalElements();
        this.size = page.getNumberOfElements();
        this.contents = page.getContent();
    }

    public long getTotalSize() {
        return totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getSize() {
        return size;
    }

    public List<T> getContents() {
        return contents;
    }
}
