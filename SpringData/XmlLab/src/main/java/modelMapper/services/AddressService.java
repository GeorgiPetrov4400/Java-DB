package modelMapper.services;

import modelMapper.entities.dtos.addresses.AddressDTO;
import modelMapper.entities.dtos.addresses.CreateAddressDTO;

public interface AddressService {
    AddressDTO create(CreateAddressDTO data);
}
