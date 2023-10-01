package com.dromakin.spring.auth2.controller;

import com.dromakin.spring.auth2.config.SwaggerConfig;
import com.dromakin.spring.auth2.model.Company;
import com.dromakin.spring.auth2.services.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/secured/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Get List of companies",
            security = {@SecurityRequirement(name = SwaggerConfig.AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "Companies",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = List.class))),
                    @ApiResponse(responseCode = "400", description = "Companies not found")
            }
    )
    public @ResponseBody
    List<Company> getAll() {
        return companyService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Get company by id",
            security = {@SecurityRequirement(name = SwaggerConfig.AUTH_SECURITY_SCHEME)}
    )
    public @ResponseBody
    void get(@PathVariable Long id) {
        companyService.get(id);
    }

    @RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Get company by name",
            security = {@SecurityRequirement(name = SwaggerConfig.AUTH_SECURITY_SCHEME)},
            responses = {
                    @ApiResponse(description = "The company",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Company.class))),
                    @ApiResponse(responseCode = "400", description = "Company not found")
            }
    )
    public @ResponseBody
    Company get(@RequestParam String name) {
        return companyService.get(name);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> create(@RequestBody Company company) {
        companyService.create(company);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/")
                .build()
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@RequestBody Company company) {
        companyService.update(company);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }
}