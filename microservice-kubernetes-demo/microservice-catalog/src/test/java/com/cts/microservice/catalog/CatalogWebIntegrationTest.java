package com.cts.microservice.catalog;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.cts.microservice.catalog.CatalogApp;
import com.cts.microservice.catalog.Item;
import com.cts.microservice.catalog.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CatalogApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CatalogWebIntegrationTest {

	@Autowired
	private ItemRepository itemRepository;

	@LocalServerPort
	private int serverPort;

	private Item iPhone;

	private RestTemplate restTemplate;

	@Before
	public void setup() {
		iPhone = itemRepository.findByName("iPhone").get(0);
		restTemplate = new RestTemplate();
	}

	@Test
	public void IsItemReturnedAsHTML() {
		String url = catalogURL() + "/" + iPhone.getId() + ".html";
		String body = getForMediaType(String.class, MediaType.TEXT_HTML, url);

		assertThat(body, containsString("iPhone"));
		assertThat(body, containsString("<div"));
	}

	private String catalogURL() {
		return "http://localhost:" + serverPort;
	}

	@Test
	public void IsItemReturnedAsJON() {
		String url = catalogURL() + "/catalog/" + iPhone.getId();
		Item body = getForMediaType(Item.class, MediaType.APPLICATION_JSON, url);

		assertThat(body, equalTo(iPhone));
	}

	@Test
	public void FormReturned() {
		String url = catalogURL() + "/searchForm.html";
		String body = getForMediaType(String.class, MediaType.TEXT_HTML, url);

		assertThat(body, containsString("<form"));
		assertThat(body, containsString("<div"));
	}

	@Test
	public void SearchWorks() {
		String url = catalogURL() + "/searchByName.html?query=iPhone";
		String body = restTemplate.getForObject(url, String.class);

		assertThat(body, containsString("iPhone"));
		assertThat(body, containsString("<div"));
	}

	private <T> T getForMediaType(Class<T> value, MediaType mediaType, String url) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(mediaType));

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<T> resultEntity = restTemplate.exchange(url, HttpMethod.GET, entity, value);

		return resultEntity.getBody();
	}

}
