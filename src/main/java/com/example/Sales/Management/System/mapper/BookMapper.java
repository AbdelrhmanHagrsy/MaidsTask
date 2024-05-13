package com.example.Sales.Management.System.mapper;

import com.example.Sales.Management.System.dto.BookDto;
import com.example.Sales.Management.System.entity.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public abstract class BookMapper {
    
    public abstract BookDto toDto(Book book);
    public abstract Book toEntity(BookDto bookDto);
}
