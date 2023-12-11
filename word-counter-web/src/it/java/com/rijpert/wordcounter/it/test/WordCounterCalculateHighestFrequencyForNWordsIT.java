package com.rijpert.wordcounter.it.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rijpert.wordcounter.web.dto.CalculateMostFrequentNWordsDTO;
import com.rijpert.wordcounter.web.dto.TextDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class WordCounterCalculateHighestFrequencyForNWordsIT {

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


        String response = mockMvc.perform(MockMvcRequestBuilders.get("/calculateMostFrequentNWords?limit=6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new TextDTO(content))))
                .andExpect(status().is(200))
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<CalculateMostFrequentNWordsDTO> result = new ObjectMapper().readValue(response, new TypeReference<>(){});
        Assertions.assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(6);
        assertThat(result.get(0).getName()).isEqualTo("in");
        assertThat(result.get(0).getFrequency()).isEqualTo(3);
        assertThat(result.get(1).getName()).isEqualTo("ut");
        assertThat(result.get(1).getFrequency()).isEqualTo(3);
        assertThat(result.get(2).getName()).isEqualTo("dolor");
        assertThat(result.get(2).getFrequency()).isEqualTo(2);
        assertThat(result.get(3).getName()).isEqualTo("dolore");
        assertThat(result.get(3).getFrequency()).isEqualTo(2);
        assertThat(result.get(4).getName()).isEqualTo("ad");
        assertThat(result.get(4).getFrequency()).isEqualTo(1);
        assertThat(result.get(5).getName()).isEqualTo("adipiscing");
        assertThat(result.get(5).getFrequency()).isEqualTo(1);
    }

    @Test
    void testCalculateHighestFrequencyNoText() throws Exception {
        String content = "";

        mockMvc.perform(MockMvcRequestBuilders.get("/calculateMostFrequentNWords?searchKey=dolor")
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

        mockMvc.perform(MockMvcRequestBuilders.get("/calculateMostFrequentNWords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(new TextDTO(content))))
                .andExpect(status().is(400));
    }
}

