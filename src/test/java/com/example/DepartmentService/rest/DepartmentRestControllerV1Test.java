package com.example.DepartmentService.rest;

import com.example.DepartmentService.dto.DepartmentDto;
import com.example.DepartmentService.model.Department;
import com.example.DepartmentService.model.Role;
import com.example.DepartmentService.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartmentRestControllerV1Test {

    @Mock
    private DepartmentServiceImpl departmentService;

    private DepartmentRestControllerV1 underTest;

    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "test";
    private static final String FIRST_NAME = "Roman";
    private static final String LAST_NAME = "Sergeyev";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DepartmentRestControllerV1(departmentService);
    }

    @Test
    public void createTest() {

        Department department = new Department(
                EMAIL,
                PASSWORD,
                FIRST_NAME,
                LAST_NAME,
                Role.ROLE_USER
        );

        underTest.create(Objects.requireNonNull(DepartmentDto.fromEntity(department)));

        ArgumentCaptor<Department> departmentArgumentCaptor =
                ArgumentCaptor.forClass(Department.class);
        verify(departmentService).create(departmentArgumentCaptor.capture());

        Department departmentCaptor = departmentArgumentCaptor.getValue();

        assertThat(departmentCaptor.getEmail()).isEqualTo(department.getEmail());
        assertThat(departmentCaptor.getFirstName()).isEqualTo(department.getFirstName());
        assertThat(departmentCaptor.getLastName()).isEqualTo(department.getLastName());
        assertThat(departmentCaptor.getRole()).isEqualTo(department.getRole());

    }

    @Test
    public void updateTest() {
        Department department = new Department(
                EMAIL,
                PASSWORD,
                FIRST_NAME,
                LAST_NAME,
                Role.ROLE_USER
        );

        underTest.update(Objects.requireNonNull(DepartmentDto.fromEntity(department)));

        ArgumentCaptor<Department> departmentArgumentCaptor =
                ArgumentCaptor.forClass(Department.class);
        verify(departmentService).update(departmentArgumentCaptor.capture());

        Department departmentCaptor = departmentArgumentCaptor.getValue();

        assertThat(departmentCaptor.getEmail()).isEqualTo(department.getEmail());
        assertThat(departmentCaptor.getFirstName()).isEqualTo(department.getFirstName());
        assertThat(departmentCaptor.getLastName()).isEqualTo(department.getLastName());
        assertThat(departmentCaptor.getRole()).isEqualTo(department.getRole());
    }
    
    @Test
    public void getByIdTest() {
        underTest.getById(1L);
        verify(departmentService, Mockito.times(1)).getById(1L);
    }

    @Test
    public void getAllTest() {
        underTest.getAll();
        verify(departmentService).getAll();
    }

    @Test
    public void deleteById() {
        underTest.deleteById(2L);
        verify(departmentService, Mockito.times(1)).deleteById(2L);
    }

}
