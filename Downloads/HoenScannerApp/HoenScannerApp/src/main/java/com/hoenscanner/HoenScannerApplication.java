package com.hoenscanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoenscanner.model.SearchResult;
import com.hoenscanner.resources.SearchResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.Configuration;
import com.google.common.io.Resources;

import java.util.ArrayList;
import java.util.List;

public class HoenScannerApplication extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<SearchResult> rentalCars = mapper.readValue(
            Resources.getResource("rental_cars.json"), new TypeReference<List<SearchResult>>() {}
        );
        List<SearchResult> hotels = mapper.readValue(
            Resources.getResource("hotels.json"), new TypeReference<List<SearchResult>>() {}
        );

        List<SearchResult> searchResults = new ArrayList<>();
        searchResults.addAll(rentalCars);
        searchResults.addAll(hotels);

        environment.jersey().register(new SearchResource(searchResults));
    }
}