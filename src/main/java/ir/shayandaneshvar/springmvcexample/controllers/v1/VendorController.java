package ir.shayandaneshvar.springmvcexample.controllers.v1;

import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorDTO;
import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorListDTO;
import ir.shayandaneshvar.springmvcexample.services.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(VendorController.BASE_URL)
public class VendorController {
    static final String BASE_URL = "/api/v1/vendors";
    private final VendorService vendorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getListOfVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO
                                             vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody
            VendorDTO vendorDTO) {
        return vendorService.saveVendorByDTO(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody
            VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendorById(id);
    }


    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getBaseUrl(Long id) {
        return getBaseUrl() + "/" + id;
    }
}
