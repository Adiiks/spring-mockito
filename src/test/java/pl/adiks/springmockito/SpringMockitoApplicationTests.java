package pl.adiks.springmockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.adiks.springmockito.model.Employee;
import pl.adiks.springmockito.model.Response;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringMockitoApplicationTests {

      private MockMvc mockMvc;

      @Autowired
      private WebApplicationContext webApplicationContext;

      private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addEmployee() throws Exception {

        Employee employee = getEmployee();
        String jsonRequest = objectMapper.writeValueAsString(employee);

        MvcResult mvcResult = mockMvc.perform(post("/employee")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = mvcResult.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);

        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void getAllEmployees() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/employee")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent = mvcResult.getResponse().getContentAsString();
        Response response = objectMapper.readValue(resultContent, Response.class);

        Assert.assertTrue(response.isSuccess());
    }

    private Employee getEmployee() {
        Employee employee = new Employee();
        employee.setName("Adrian");
        employee.setDept("IT");
        return employee;
    }
}
