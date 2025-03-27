package com.atu.green_global_initiative_api.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@WebAppConfiguration
class ProdCorsConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    void testCorsConfigurationForNullHeaders() throws Exception {
        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Test CORS settings for headers expected to be null
        mockMvc.perform(MockMvcRequestBuilders.options("/api/some-endpoint")
                        .header("Origin", "http://example.com"))
                .andExpect(result -> {
                    String allowOriginHeader = result.getResponse().getHeader("Access-Control-Allow-Origin");
                    assertThat(allowOriginHeader, is((String) null));
                })
                .andExpect(result -> {
                    String allowHeadersHeader = result.getResponse().getHeader("Access-Control-Allow-Headers");
                    assertThat(allowHeadersHeader, is((String) null));
                });
    }

    @Test
    void testCorsConfigurationWithInvalidOriginForNullHeaders() throws Exception {
        // Setup MockMvc
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Test with an invalid origin to ensure headers are null
        mockMvc.perform(MockMvcRequestBuilders.options("/api/some-endpoint")
                        .header("Origin", "http://invalid-origin.com"))
                .andExpect(result -> {
                    String allowOriginHeader = result.getResponse().getHeader("Access-Control-Allow-Origin");
                    assertThat(allowOriginHeader, is((String) null));
                })
                .andExpect(result -> {
                    String allowHeadersHeader = result.getResponse().getHeader("Access-Control-Allow-Headers");
                    assertThat(allowHeadersHeader, is((String) null));
                });
    }

}