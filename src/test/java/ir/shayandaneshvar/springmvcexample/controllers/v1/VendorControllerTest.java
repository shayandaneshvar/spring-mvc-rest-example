package ir.shayandaneshvar.springmvcexample.controllers.v1;

import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorDTO;
import ir.shayandaneshvar.springmvcexample.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendorController.class)
@RunWith(SpringRunner.class)
public class VendorControllerTest {
    @MockBean
    VendorService vendorService;
    @Autowired
    MockMvc mockMvc;

    VendorDTO vendorDTO1;
    VendorDTO vendorDTO2;


    @Before
    public void setUp() throws Exception {
        vendorDTO1 = new VendorDTO().setName("Vendor_1").setVendorUrl(
                VendorController.getBaseUrl(1L));
        vendorDTO2 = new VendorDTO().setName("Vendor_2").setVendorUrl(
                VendorController.getBaseUrl(2L));
    }

    @Test
    public void getVendorById() throws Exception {
        given(vendorService.getVendorById(anyLong())).willReturn(vendorDTO1);
        mockMvc.perform(get(VendorController.getBaseUrl(1L)).contentType(
                MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(
                jsonPath("$.name", equalTo(vendorDTO1.getName())));
    }
}