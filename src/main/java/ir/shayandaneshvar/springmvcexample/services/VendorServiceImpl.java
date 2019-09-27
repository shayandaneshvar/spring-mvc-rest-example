package ir.shayandaneshvar.springmvcexample.services;

import ir.shayandaneshvar.springmvcexample.api.v1.mapper.VendorMapper;
import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorDTO;
import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorListDTO;
import ir.shayandaneshvar.springmvcexample.controllers.v1.VendorController;
import ir.shayandaneshvar.springmvcexample.domain.Vendor;
import ir.shayandaneshvar.springmvcexample.exceptions.ResourceNotFoundException;
import ir.shayandaneshvar.springmvcexample.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    private final VendorMapper mapper = VendorMapper.INSTANCE;

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id).map(mapper::vendorToVendorDTO)
                .map(vendorDTO -> vendorDTO.setVendorUrl(VendorController.getBaseUrl(id)))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOS = vendorRepository.findAll().stream().map(v ->
                mapper.vendorToVendorDTO(v).setVendorUrl(VendorController.getBaseUrl
                        (v.getId()))).collect(Collectors.toList());
        return new VendorListDTO(vendorDTOS);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(mapper.vendorDTOToVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        vendor = vendorRepository.save(vendor);
        return mapper.vendorToVendorDTO(vendor).setVendorUrl(
                VendorController.getBaseUrl(vendor.getId()));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = mapper.vendorDTOToVendor(vendorDTO).setId(id);
        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {
            if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }
            return saveAndReturnDTO(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
