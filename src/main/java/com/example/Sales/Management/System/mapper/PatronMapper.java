package com.example.Sales.Management.System.mapper;

import com.example.Sales.Management.System.dto.PatronDto;
import com.example.Sales.Management.System.entity.Patron;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class PatronMapper {
    
    public abstract PatronDto toDto(Patron patron);
    public abstract Patron toEntity(PatronDto patronDto);
}
