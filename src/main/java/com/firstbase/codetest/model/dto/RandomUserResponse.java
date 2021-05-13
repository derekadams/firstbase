package com.firstbase.codetest.model.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps a list of {@link RandomUser} entities.
 */
public class RandomUserResponse {

    /** List of results from query */
    private List<RandomUser> results = new ArrayList<>();

    public List<RandomUser> getResults() {
	return results;
    }

    public void setResults(List<RandomUser> results) {
	this.results = results;
    }
}
