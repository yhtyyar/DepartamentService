package com.example.DepartmentService.dto;

import com.example.DepartmentService.model.Department;
import com.example.DepartmentService.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
public class DepartmentDto {


    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public Department toEntity() {
        return Department.builder()
                .id(this.id)
                .email(this.email)
                .password(this.password)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .role(this.role)
                .build();
    }

    public static DepartmentDto fromEntity(Department department) {

        if (Objects.isNull(department)) {
            return null;
        }

        return DepartmentDto.builder()
                .id(department.getId())
                .email(department.getEmail())
                .password(department.getPassword())
                .firstName(department.getFirstName())
                .lastName(department.getLastName())
                .role(department.getRole())
                .build();
    }
}
