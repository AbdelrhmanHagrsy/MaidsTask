package com.example.Sales.Management.System.mapper;


import com.example.Sales.Management.System.dto.BorrowingRecordDto;
import com.example.Sales.Management.System.entity.BorrowingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowingRecordMapper {
    
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private PatronMapper patronMapper;
    
    public BorrowingRecordDto toDto(BorrowingRecord borrowingRecord){
        BorrowingRecordDto borrowingRecordDto = new BorrowingRecordDto();
        borrowingRecordDto.setId(borrowingRecord.getId());
        borrowingRecordDto.setBook(bookMapper.toDto(borrowingRecord.getBook()));
        borrowingRecordDto.setPatron(patronMapper.toDto(borrowingRecord.getPatron()));
        borrowingRecordDto.setBorrowingDate(borrowingRecord.getBorrowingDate());
        borrowingRecordDto.setReturnDate(borrowingRecord.getReturnDate());
        return borrowingRecordDto;
    }


}
