package com.firstmicroservice.department;

import com.firstmicroservice.department.controller.DepartmentController;
import com.firstmicroservice.department.entity.Department;
import com.firstmicroservice.department.service.DepartmentService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(DepartmentController.class)
class DepartmentServiceApplicationTest {
    @MockBean
    DepartmentService departmentService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }


    @Test
    void shouldReturnListOfDepartments() throws Exception {
        when(departmentService.getAllDepartments())
                .thenReturn(List.of(new Department("IT", "4th Cross, First Street", "IT-006")));

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/departments/all")
                        .header("X-Foo", "IT"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andDo(print());
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].departmentAddress").value("4th Cross, First Street"));
    }

    @Test
    void shouldCreateNewDepartment() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/departments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"departmentName\":\"IT\",\"departmentAddress\" :\"4th Cross, First Street\", \"departmentCode\":\"IT-006\"}")

                )
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentAddress").value("4th Cross, First Street"));
//                .andExpect(MockMvcResultMatchers.header().exists("Content-Type"))
//                .andExpect(MockMvcResultMatchers.header().string("Content-Type", (Matchers.containsString("application/json;charset=UTF-8"))));
        verify(departmentService).saveDepartment(any(Department.class));

    }

    @Test
    public void findOne() throws Exception {
        when(departmentService.findDepartmentById(Long.valueOf(1)))
                .thenReturn(new Department("IT", "4th Cross, First Street", "IT-006"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/departments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.departmentId", Matchers.is(1)));
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)));
    }

}