

/*
                    GNU AFFERO GENERAL PUBLIC LICENSE
                       Version 3, 19 November 2007

 Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 Everyone is permitted to copy and distribute verbatim copies
 of this license document, but changing it is not allowed.

                            Preamble

  The GNU Affero General Public License is a free, copyleft license for
software and other kinds of works, specifically designed to ensure
cooperation with the community in the case of network server software.

  The licenses for most software and other practical works are designed
to take away your freedom to share and change the works.  By contrast,
our General Public Licenses are intended to guarantee your freedom to
share and change all versions of a program--to make sure it remains free
software for all its users.

  When we speak of free software, we are referring to freedom, not
price.  Our General Public Licenses are designed to make sure that you
have the freedom to distribute copies of free software (and charge for
them if you wish), that you receive source code or can get it if you
want it, that you can change the software or use pieces of it in new
free programs, and that you know you can do these things.

  Developers that use our General Public Licenses protect your rights
with two steps: (1) assert copyright on the software, and (2) offer
you this License which gives you legal permission to copy, distribute
and/or modify the software.

  A secondary benefit of defending all users' freedom is that
improvements made in alternate versions of the program, if they
receive widespread use, become available for other developers to
incorporate.  Many developers of free software are heartened and
encouraged by the resulting cooperation.  However, in the case of
software used on network servers, this result may fail to come about.
The GNU General Public License permits making a modified version and
letting the public access it on a server without ever releasing its
source code to the public.

  The GNU Affero General Public License is designed specifically to
ensure that, in such cases, the modified source code becomes available
to the community.  It requires the operator of a network server to
provide the source code of the modified version running there to the
users of that server.  Therefore, public use of a modified version, on
a publicly accessible server, gives the public access to the source
code of the modified version.

  An older license, called the Affero General Public License and
published by Affero, was designed to accomplish similar goals.  This is
a different license, not a version of the Affero GPL, but Affero has
released a new version of the Affero GPL which permits relicensing under
this license.

  The precise terms and conditions for copying, distribution and
modification follow.
*/

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
