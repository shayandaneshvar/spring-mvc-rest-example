package ir.shayandaneshvar.springmvcexample.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.shayandaneshvar.springmvcexample.api.v1.model.CustomerDTO;
import ir.shayandaneshvar.springmvcexample.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void patchCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO().setFirstName("Fiona");
        CustomerDTO returnedDTO = new CustomerDTO().setFirstName(customerDTO.
                getFirstName()).setLastName("Rox").setCustomerUrl(CustomerController.
                getBaseUrl() + "/1");
        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).
                thenReturn(returnedDTO);
        mockMvc.perform(patch(CustomerController.getBaseUrl() + "/1").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(customerDTO))).andExpect(status().isOk()).
                andExpect(jsonPath("$.firstName", equalTo("Fiona"))).
                andExpect(jsonPath("$.lastName", equalTo("Rox"))).
                andExpect(jsonPath("$.customer_url", equalTo(
                        CustomerController.getBaseUrl() + "/1")));

    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(CustomerController.getBaseUrl() + "/1").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk());
        verify(customerService).deleteCustomerById(anyLong());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}