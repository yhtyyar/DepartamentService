

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
