package ir.shayandaneshvar.springmvcexample.services;

import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorDTO;
import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorListDTO;

public interface VendorService {
    VendorDTO getVendorById(Long id);

    VendorListDTO getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
