package com.atu.green_global_initiative_api.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DevCorsConfigTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void testCorsConfigurationForAllOrigins() throws Exception {
        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Perform OPTIONS request (to test CORS headers)
        mockMvc.perform(MockMvcRequestBuilders.options("/api/some-endpoint")
                        .header("Origin", "http://example.com"))
                // Expect that Access-Control-Allow-Origin is null (as per the updated requirement)
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", nullValue()))
                // Expect that Access-Control-Allow-Methods is null
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Methods", nullValue()))
                // Expect that Access-Control-Allow-Headers is null
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Headers", nullValue()));
    }

    @Test
    void testCorsConfigurationWithSpecificMethod() throws Exception {
        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Test specific method in OPTIONS request
        mockMvc.perform(MockMvcRequestBuilders.options("/api/some-endpoint")
                        .header("Origin", "http://example.com"))
                // Expect null for Access-Control-Allow-Methods
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Methods", nullValue()));
    }

    @Test
    void testCorsConfigurationWithInvalidOrigin() throws Exception {
        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Test an invalid origin
        mockMvc.perform(MockMvcRequestBuilders.options("/api/some-endpoint")
                        .header("Origin", "http://invalid-origin.com"))
                // Expect null for Access-Control-Allow-Origin
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", nullValue()));
    }

    @Test
    void testCorsConfigurationForApiEndpoint() throws Exception {
        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Perform OPTIONS request for API endpoint
        mockMvc.perform(MockMvcRequestBuilders.options("/api/some-endpoint"))
                // Expect null for Access-Control-Allow-Origin
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Origin", nullValue()))
                // Expect null for Access-Control-Allow-Methods
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Methods", nullValue()))
                // Expect null for Access-Control-Allow-Headers
                .andExpect(MockMvcResultMatchers.header().string("Access-Control-Allow-Headers", nullValue()));
    }


}