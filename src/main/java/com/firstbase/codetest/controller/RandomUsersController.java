package com.firstbase.codetest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.firstbase.codetest.model.dto.RandomUser;
import com.firstbase.codetest.service.RandomUserService;

/**
 * REST controller test APIs for getting random users.
 */
@RestController
@RequestMapping("/api/random")
public class RandomUsersController {

    @Autowired
    private RandomUserService randomUserService;

    /**
     * Get a given number of random users.
     * 
     * @param count
     * @return
     */
    @GetMapping
    public List<RandomUser> getRandomUsers(@RequestParam(required = false) Integer count) {
	int numUsers = count != null ? count : 10;
	return getRandomUserService().createRandomUsers(numUsers);
    }

    /**
     * Get a random job title.
     * 
     * @return
     */
    @GetMapping("/title")
    public String getRandomJobTitle() {
	return getRandomUserService().createRandomJobTitle();
    }

    protected RandomUserService getRandomUserService() {
	return randomUserService;
    }
}
