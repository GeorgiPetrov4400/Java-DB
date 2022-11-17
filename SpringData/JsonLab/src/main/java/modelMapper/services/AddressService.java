package modelMapper.services;

import modelMapper.entities.Address;
import modelMapper.entities.dtos.AddressDTO;

public interface AddressService {
    Address create(AddressDTO data);
}
