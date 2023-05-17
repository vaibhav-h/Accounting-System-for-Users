package com.accounting.system.mapper;

import com.accounting.system.dto.LocationDto;
import com.accounting.system.entity.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocationMapper{
    LocationMapper mapper = Mappers.getMapper(LocationMapper.class);
    LocationDto mapToLocationDto(Location location);
    Location mapToLocation(LocationDto locationdto);

}
