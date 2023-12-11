package com.rijpert.wordcounter.it.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rijpert.wordcounter.web.dto.CalculateHighestFrequencyDTO;
import com.rijpert.wordcounter.web.dto.CalculateHighestFrequencyForWordDTO;
import com.rijpert.wordcounter.web.dto.TextDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WordCounterCalculateFrequencyForWordIT {

    private final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCalculateFrequencyForWord() throws Exception {
        String content = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
                ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum.
                """;


        String response = mockMvc.perform(MockMvcRequestBuilders.get("/calculateFrequencyForWord?searchKey=dolor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new TextDTO(content))))
                .andExpect(status().is(200))
                .andReturn()
                .getResponse()
                .getContentAsString();

        CalculateHighestFrequencyForWordDTO result = om.readValue(response, new TypeReference<>(){});
        assertThat(result).isNotNull();
        assertThat(result.getFrequency()).isEqualTo(2);
    }

    @Test
    void testCalculateHighestFrequencyNoText() throws Exception {
        String content = "";

        mockMvc.perform(MockMvcRequestBuilders.get("/calculateHighestFrequency?searchKey=dolor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new TextDTO(content))))
                .andExpect(status().is(400));
    }

    @Test
    void testCalculateHighestFrequencyNoSearchKey() throws Exception {
        String content = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit,
                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
                ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident,
                sunt in culpa qui officia deserunt mollit anim id est laborum.
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/calculateFrequencyForWord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new TextDTO(content))))
                .andExpect(status().is(400));
    }
}

