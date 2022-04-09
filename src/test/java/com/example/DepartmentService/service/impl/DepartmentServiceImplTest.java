package com.example.DepartmentService.service.impl;

import com.example.DepartmentService.model.Department;
import com.example.DepartmentService.model.Role;
import com.example.DepartmentService.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentServiceImpl underTest;

    private static final String EMAIL = "test@mail.com";
    private static final String PASSWORD = "test";
    private static final String FIRST_NAME = "Roman";
    private static final String LAST_NAME = "Sergeyev";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new DepartmentServiceImpl(departmentRepository);
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

        underTest.create(department);

        ArgumentCaptor<Department> departmentArgumentCaptor =
                ArgumentCaptor.forClass(Department.class);
        verify(departmentRepository).save(departmentArgumentCaptor.capture());

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

        underTest.update(department);

        ArgumentCaptor<Department> departmentArgumentCaptor =
                ArgumentCaptor.forClass(Department.class);
        verify(departmentRepository).save(departmentArgumentCaptor.capture());

        Department departmentCaptor = departmentArgumentCaptor.getValue();

        assertThat(departmentCaptor.getEmail()).isEqualTo(department.getEmail());
        assertThat(departmentCaptor.getFirstName()).isEqualTo(department.getFirstName());
        assertThat(departmentCaptor.getLastName()).isEqualTo(department.getLastName());
        assertThat(departmentCaptor.getRole()).isEqualTo(department.getRole());
    }

    @Test
    public void getByIdTest() {
        underTest.getById(1L);
        verify(departmentRepository, Mockito.times(1)).getById(1L);
    }

    @Test
    public void deleteById() {
        underTest.deleteById(2L);
        verify(departmentRepository, Mockito.times(1)).deleteById(2L);
    }

    @Test
    public void getAllTest() {
        underTest.getAll();
        verify(departmentRepository).findAll();
    }
}
