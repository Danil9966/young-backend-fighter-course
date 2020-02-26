package groupId.mockMvcTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import groupId.model.Dog;
import groupId.resource.MainController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.rmi.MarshalledObject;

import static groupId.CommonTestUtil.DOGGIE_URL;
import static groupId.CommonTestUtil.getConsistentDoggie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testng.Assert.assertEquals;

public class TrivialMockMVCTest {
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    private Dog createDog() throws Exception {
        String dogResponse = mockMvc.perform(MockMvcRequestBuilders.post(DOGGIE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getConsistentDoggie())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readValue(dogResponse, Dog.class);
    }

    @BeforeMethod
    public void setUp() throws Exception {
//        MockMvcBuilders.standaloneSetup(MainController.class);
//        mockMvc = MockMvcBuilders.standaloneSetup(MainController.class).build();
//        objectMapper = new ObjectMapper();
//        objectMapper.findAndRegisterModules();
    }

    @Test
    public void testGetDog() throws Exception {
//        Dog dog = createDog();

        String response = mockMvc.perform(MockMvcRequestBuilders.get(DOGGIE_URL + "/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("", objectMapper.readValue(response, Dog.class));

    }

}
