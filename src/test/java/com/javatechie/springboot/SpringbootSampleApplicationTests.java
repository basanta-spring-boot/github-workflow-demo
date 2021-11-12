package com.javatechie.springboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SpringbootSampleApplication.class)
class SpringbootSampleApplicationTests {

@Autowired
	private MockMvc mockMvc;

	ObjectMapper om = new ObjectMapper();



	@Test
	public void addBooksTest() throws Exception {
		Book book= Book.builder()
				.id(23423)
				.name("spring")
				.author("johns")
				.build();

		String jsonRequest = om.writeValueAsString(book);
		MvcResult result = mockMvc.perform(post("/books").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Book response = om.readValue(resultContent, Book.class);
		Assertions.assertEquals(23423, response.getId());
	}


	@Test
	public void getBooksTest() throws Exception{
		mockMvc
				.perform(get("/books")
						.content(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

}
