package pe.projects.rappi.testrappi.data.entity.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pe.projects.rappi.testrappi.data.entity.MovieEntity;

public class MoviesListResponse {

    private int page;
    private int total_results;
    private int total_pages;
    private List<MovieEntity> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
