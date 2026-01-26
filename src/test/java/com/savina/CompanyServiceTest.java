package com.savina;

import com.savina.ticketsystem.model.Company;
import com.savina.ticketsystem.repository.CompanyRepository;
import com.savina.ticketsystem.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateCompany_Success() {
        String name = "Test Company";
        String email = "test@company.com";

        Company savedCompany = new Company(name, email, "ACTIVE");
        when(companyRepository.existsByCompanyName(name)).thenReturn(false);
        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);

        Company result = companyService.createCompany(name, email);

        assertNotNull(result);
        assertEquals(name, result.getCompanyName());
        assertEquals(email, result.getCompanyEmail());
        assertEquals("ACTIVE", result.getStatus());
        verify(companyRepository, times(1)).save(any(Company.class));
    }


    @Test
    void testCreateCompany_AlreadyExists() {
        String name = "Existing Company";
        String email = "existing@company.com";

        when(companyRepository.existsByCompanyName(name)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                companyService.createCompany(name, email));

        assertEquals("Kompania me kete emer egziston", exception.getMessage());
        verify(companyRepository, never()).save(any(Company.class));
    }


    @Test
    void testUpdateCompany_Success() {
        Integer companyId = 1;
        String newName = "Updated Name";
        String newEmail = "updated@company.com";

        Company existingCompany = new Company("Old Name", "old@company.com", "ACTIVE");
        when(companyRepository.findByCompanyId(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(any(Company.class))).thenReturn(existingCompany);

        boolean result = companyService.updateCompany(companyId, newName, newEmail);

        assertTrue(result);
        assertEquals(newName, existingCompany.getCompanyName());
        assertEquals(newEmail, existingCompany.getCompanyEmail());
        verify(companyRepository, times(1)).save(existingCompany);
    }


    @Test
    void testUpdateCompany_NotFound() {
        Integer companyId = 99;
        String newName = "Name";
        String newEmail = "email@company.com";

        when(companyRepository.findByCompanyId(companyId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                companyService.updateCompany(companyId, newName, newEmail));

        assertEquals("Kjo kompani me kete ID nuk ekziston", exception.getMessage());
        verify(companyRepository, never()).save(any(Company.class));
    }
}