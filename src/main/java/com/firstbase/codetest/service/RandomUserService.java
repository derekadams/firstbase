package com.firstbase.codetest.service;

import java.util.List;

import com.firstbase.codetest.model.dto.RandomUser;

/**
 * Service that generates random user information by calling an external API.
 */
public interface RandomUserService {

    /**
     * Create a given number of random users.
     * 
     * @param count
     * @return
     */
    List<RandomUser> createRandomUsers(int count);

    /**
     * Create a random job title based on a common words.
     * 
     * @return
     */
    String createRandomJobTitle();
}
