package com.firstbase.codetest.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.firstbase.codetest.model.dto.RandomUser;
import com.firstbase.codetest.model.dto.RandomUserResponse;
import com.firstbase.codetest.service.RandomUserService;

import reactor.core.publisher.Mono;

/**
 * Implementation of {@link RandomUserService} service interface.
 */
@Service
public class RandomUserServiceImpl implements RandomUserService, InitializingBean {

    @Value("${randomuser.api.base.url}")
    private String randomUserApiBaseUrl;

    /** Web/REST client */
    private WebClient webClient;

    /** Seniority levels for generating job titles */
    private static final String[] SENIORITY_LEVELS = { "Senior", "Junior", "Novice", "Entry-level" };

    /** Positions for generating job titles */
    private static final String[] POSITIONS = { "Java Developer", "Neurosurgeon", "Basketweaver", "Crypto Trader",
	    "Archeologist", "Solutions Architect" };

    /*
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	this.webClient = WebClient.builder().baseUrl(getRandomUserApiBaseUrl()).build();
    }

    /*
     * @see com.firstbase.codetest.service.RandomUserService#createRandomUsers(int)
     */
    @Override
    public List<RandomUser> createRandomUsers(int count) {
	Mono<ResponseEntity<RandomUserResponse>> mono = getWebClient().get()
		.uri(uriBuilder -> uriBuilder.path("/api").queryParam("results", String.valueOf(count)).build())
		.accept(MediaType.APPLICATION_JSON).retrieve().toEntity(RandomUserResponse.class);
	// TODO: Could add better error handling by checking the ResponseEntity state.
	RandomUserResponse response = mono.block().getBody();
	return response.getResults();
    }

    /*
     * @see com.firstbase.codetest.service.RandomUserService#createRandomJobTitle()
     */
    @Override
    public String createRandomJobTitle() {
	int sindex = new Random().nextInt(SENIORITY_LEVELS.length);
	int pindex = new Random().nextInt(POSITIONS.length);
	return String.format("%s %s", SENIORITY_LEVELS[sindex], POSITIONS[pindex]);
    }

    protected String getRandomUserApiBaseUrl() {
	return randomUserApiBaseUrl;
    }

    protected WebClient getWebClient() {
	return webClient;
    }
}
