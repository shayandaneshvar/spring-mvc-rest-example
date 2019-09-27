package ir.shayandaneshvar.springmvcexample.services;

import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorDTO;
import ir.shayandaneshvar.springmvcexample.domain.Vendor;
import ir.shayandaneshvar.springmvcexample.exceptions.ResourceNotFoundException;
import ir.shayandaneshvar.springmvcexample.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class VendorServiceImplTest {
    public static final String NAME_1 = "MyVendor";
    public static final String NAME_2 = "MyVendor";
    public static final Long ID_1 = 1l;
    public static final Long ID_2 = 2l;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository);
    }

    @Test
    public void getVendorById() {
        Vendor vendor = getVendor1();
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        VendorDTO vendorDTO = vendorService.getVendorById(ID_1);
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getVendorByIdNotFound() {
        given(vendorRepository.findById(anyLong())).willReturn(Optional.empty());
        VendorDTO vendorDTO = vendorService.getVendorById(ID_1);
        then(vendorRepository).should(times(1)).findById(anyLong());
    }

    private Vendor getVendor1() {
        return new Vendor().setId(ID_1).setName(NAME_1);
    }

    private Vendor getVendor2() {
        return new Vendor().setId(ID_2).setName(NAME_2);
    }

}