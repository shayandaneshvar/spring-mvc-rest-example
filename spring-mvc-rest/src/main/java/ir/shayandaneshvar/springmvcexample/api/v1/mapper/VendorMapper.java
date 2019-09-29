package ir.shayandaneshvar.springmvcexample.api.v1.mapper;

import ir.shayandaneshvar.springmvcexample.api.v1.model.VendorDTO;
import ir.shayandaneshvar.springmvcexample.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);

    VendorDTO vendorToVendorDTO(Vendor vendor);
}
