package com.example.DepartmentService.rest;


import com.example.DepartmentService.dto.DepartmentDto;
import com.example.DepartmentService.model.Department;
import com.example.DepartmentService.service.impl.DepartmentServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/departments")
public class DepartmentRestControllerV1 {


    private final DepartmentServiceImpl departmentService;

    @Autowired
    private RestTemplate restTemplate;

    private final static String URL = "http://localhost:8080/api/v1/users/";

    @PostMapping
    @PreAuthorize("hasAuthority('departments:write')")
    public ResponseEntity<?> create (@RequestBody @NonNull DepartmentDto departmentDto){
        log.info("Inside create method in DepartmentRestControllerV1 {}", departmentDto.toEntity());
        Department department = departmentService.create(departmentDto.toEntity());
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.CREATED);
    }


    @PutMapping
    @PreAuthorize("hasAuthority('departments:write')")
    public ResponseEntity<?> update(@RequestBody @NonNull DepartmentDto departmentDto) {
        log.info("Inside update method in DepartmentRestControllerV1 {}", departmentDto.toEntity());
        Department department = departmentService.update(departmentDto.toEntity());
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.OK);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('departments:read')")
    public ResponseEntity<?> getAll() {
        log.info("Inside getAll method in DepartmentRestControllerV1");
        List<Department> departmentList = departmentService.getAll();

        if (CollectionUtils.isEmpty(departmentList)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DepartmentDto> departmentDtoList = departmentList.stream()
                .map(DepartmentDto::fromEntity)
                .collect(Collectors.toList());
        return new ResponseEntity<>(departmentDtoList, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('departments:read')")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        log.info("Inside getById method in DepartmentRestControllerV1 {}", id);
        Department department = departmentService.getById(id);
        return new ResponseEntity<>(DepartmentDto.fromEntity(department), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('departments:write')")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        log.info("Inside deleteById method in DepartmentRestControllerV1 {}", id);
        departmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('departments:write')")
    public ResponseEntity<?> getAllUsers() {
        log.info("Inside getAllUsers method in DepartmentRestControllerV1");

        String nameAndPassword = "admin@mail.com:admin";
        byte[] nameAndPasswordBytes = nameAndPassword.getBytes();
        byte[] base64nameAndPasswordBytes = Base64.encodeBase64(nameAndPasswordBytes);
        String base64nameAndPassword= new String(base64nameAndPasswordBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64nameAndPassword);
        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.exchange(URL, HttpMethod.GET, request, Object.class);
    }
}
