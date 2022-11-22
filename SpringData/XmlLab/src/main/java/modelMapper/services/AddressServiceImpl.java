package modelMapper.services;

import modelMapper.entities.Address;
import modelMapper.entities.dtos.addresses.AddressDTO;
import modelMapper.entities.dtos.addresses.CreateAddressDTO;
import modelMapper.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ModelMapper mapper;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }

    @Override
    public AddressDTO create(CreateAddressDTO data) {
        Address address = mapper.map(data, Address.class);

        Address saved = this.addressRepository.save(address);

        return this.mapper.map(saved, AddressDTO.class);
    }
}
