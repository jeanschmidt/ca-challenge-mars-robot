package org.ca.challenge.nasabot.jeanschmidt.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.ca.challenge.nasabot.jeanschmidt.rest.MarsRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(MarsRestController.class)
public class MarsRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void marsRobotNoRoute() throws Exception {
      this.mvc.perform(MockMvcRequestBuilders.post("/rest/mars/"))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("(0, 0, N)")));
    }

    @Test
    public void marsRobotMailMMRMMRMM() throws Exception {
      this.mvc.perform(MockMvcRequestBuilders.post("/rest/mars/MMRMMRMM"))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("(2, 0, S)")));
    }

    @Test
    public void marsRobotMailMML() throws Exception {
      this.mvc.perform(MockMvcRequestBuilders.post("/rest/mars/MML"))
        .andExpect(status().isOk())
        .andExpect(content().string(equalTo("(0, 2, W)")));
    }

    @Test
    public void marsRobotMailAAA() throws Exception {
      this.mvc.perform(MockMvcRequestBuilders.post("/rest/mars/AAA"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(equalTo("400 Bad Request")));
    }

    @Test
    public void marsRobotMailMMMMMMMMMMMMMMMMMMMMMMMM() throws Exception {
      this.mvc.perform(MockMvcRequestBuilders.post("/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(equalTo("400 Bad Request")));
    }
}
