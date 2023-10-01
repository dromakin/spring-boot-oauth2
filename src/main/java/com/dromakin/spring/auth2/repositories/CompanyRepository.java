package com.dromakin.spring.auth2.repositories;


import com.dromakin.spring.auth2.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

// extends JpaRepository<Company, Long>
@Repository
public interface CompanyRepository  {

    Company findById(Long id);

    Company findByName(String name);

    List<Company> findAll();

    void create(Company company);

    Company update(Company company);

    void delete(Long id);

    void delete(Company company);
}
