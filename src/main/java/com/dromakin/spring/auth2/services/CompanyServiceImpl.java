package com.dromakin.spring.auth2.services;

import com.dromakin.spring.auth2.model.Company;
import com.dromakin.spring.auth2.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Company get(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ') and hasAuthority('DEPARTMENT_READ')")
    public Company get(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('COMPANY_READ')")
    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_CREATE')")
    public void create(Company company) {
        companyRepository.create(company);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_UPDATE')")
    public Company update(Company company) {
        return companyRepository.update(company);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Long id) {
        companyRepository.delete(id);
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('COMPANY_DELETE')")
    public void delete(Company company) {
        companyRepository.delete(company);
    }
}
