package com.hoenscanner.resources;

import com.hoenscanner.model.Search;
import com.hoenscanner.model.SearchResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/search")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

    private final List<SearchResult> allResults;

    public SearchResource(List<SearchResult> allResults) {
        this.allResults = allResults;
    }

    @POST
    public List<SearchResult> search(Search search) {
        String city = search.getCity().toLowerCase();
        return allResults.stream()
                .filter(result -> result.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }
}