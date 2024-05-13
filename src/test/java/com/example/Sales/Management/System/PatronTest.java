package com.example.Sales.Management.System;
import com.example.Sales.Management.System.dto.PatronDto;
import com.example.Sales.Management.System.entity.Patron;
import com.example.Sales.Management.System.exception.RecordNotFoundException;
import com.example.Sales.Management.System.mapper.PatronMapper;
import com.example.Sales.Management.System.repository.BorrowingRecordRepository;
import com.example.Sales.Management.System.repository.PatronRepository;
import com.example.Sales.Management.System.service.PatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.MessageSource;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
public class PatronTest {

    @Mock
    private PatronRepository patronRepository;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private PatronMapper patronMapper;

    @Mock
    private MessageSource messageSource;

    @InjectMocks
    private PatronService patronService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPatronById_WhenPatronExists() {
        Long id = 1L;
        Patron patron = new Patron();
        patron.setId(id);
        PatronDto patronDto = new PatronDto();
        patronDto.setId(id);

        when(patronRepository.findById(id)).thenReturn(Optional.of(patron));
        when(patronMapper.toDto(patron)).thenReturn(patronDto);

        PatronDto result = patronService.getPatronById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testGetPatronById_WhenPatronNotExists() {
        Long id = 1L;

        when(patronRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> patronService.getPatronById(id));

    }

}
