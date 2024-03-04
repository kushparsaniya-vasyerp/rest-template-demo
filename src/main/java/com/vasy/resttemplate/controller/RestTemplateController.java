package com.vasy.resttemplate.controller;

import com.vasy.resttemplate.models.Employee;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class RestTemplateController {

    private final RestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080";
    private final String URI_EMPLOYEES = BASE_URL + "/employees";
    private final String URI_EMPLOYEES_ID = BASE_URL + "/employees/{id}";

    public RestTemplateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URI_EMPLOYEES, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<Employee>>() {});
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URI_EMPLOYEES_ID, HttpMethod.GET,entity, Employee.class, id);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(employee, headers);
        return restTemplate.exchange(URI_EMPLOYEES, HttpMethod.POST,entity, Employee.class);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(URI_EMPLOYEES_ID, HttpMethod.DELETE,entity, Employee.class, id);
    }


    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<?> entity = new HttpEntity<>(employee, headers);
        return restTemplate.exchange(URI_EMPLOYEES_ID, HttpMethod.PUT,entity, Employee.class, id);
    }
}
