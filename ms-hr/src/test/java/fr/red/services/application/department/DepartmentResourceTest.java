package fr.red.services.application.department;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.red.services.application.department.model.DepartmentUI;
import fr.red.services.domain.department.model.Department;
import fr.red.services.domain.department.port.application.DepartmentRequester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentResourceTest {

    @Mock
    DepartmentRequester departmentRequester;

    @InjectMocks
    DepartmentResource departmentResource;

    private MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(departmentResource).build();
    }


    @Test
    public void testGetDepartments() throws Exception {

        List<Department> departments = populateDepartmentsList();

        Mockito.when(departmentRequester.getDepartments()).thenReturn(departments);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/private/api/v1/departments").contentType(MediaType.APPLICATION_JSON)).andExpect(
                MockMvcResultMatchers.status().isOk())
                                     .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        List<DepartmentUI> departmentsUI = Arrays.asList(mapper.readValue(content, DepartmentUI[].class));
        Assert.assertNotNull(departmentsUI);
        Assert.assertEquals(2, departmentsUI.size());
        Mockito.verify(departmentRequester , Mockito.times(1)).getDepartments();
    }

    @Test
    public void testGetDepartmentById() throws Exception {
        Mockito.when(departmentRequester.getDepartmentById("1")).thenReturn(expectedDepartment());
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/private/api/v1/departments/{id}", "1").contentType(MediaType.APPLICATION_JSON)).andExpect(
                MockMvcResultMatchers.status().isOk())
                                     .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        DepartmentUI result = mapper.readValue(content, DepartmentUI.class);
        Assert.assertNotNull(result);
        Assert.assertEquals("1", result.getDeptno());
        Assert.assertEquals("ACCOUNTING", result.getDname());
        Assert.assertEquals("NEW YORK", result.getLoc());
    }

    @Test
    public void testAddDepartment() throws Exception {

        Mockito.when(departmentRequester.addDepartment(ArgumentMatchers.any())).thenReturn(expectedDepartment());
        DepartmentUI departmentToAdd = DepartmentUI.builder()
                                               .deptno("1")
                                               .dname("ACCOUNTING")
                                               .loc("NEW YORK")
                                               .build();

        String inputJson = mapToJson(departmentToAdd);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/private/api/v1/departments").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
        ObjectMapper mapper = new ObjectMapper();
        DepartmentUI result = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(),
                                               new TypeReference<>() {
                                               });
        Assert.assertNotNull(result);
        Assert.assertEquals("1", result.getDeptno());
        Assert.assertEquals("ACCOUNTING", result.getDname());
        Assert.assertEquals("NEW YORK", result.getLoc());

    }

    @Test
    public void testUpdateDepartment() throws Exception {

        Mockito.when(departmentRequester.updateDepartment(ArgumentMatchers.any())).thenReturn(expectedUpdatedDepartment());
        DepartmentUI departmentToUpdate = DepartmentUI.builder()
                                                   .deptno("1")
                                                   .dname("ACCOUNTING")
                                                   .loc("LAS VEGAS")
                                                   .build();

        String inputJson = mapToJson(departmentToUpdate);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/private/api/v1/departments").contentType(MediaType.APPLICATION_JSON).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        ObjectMapper mapper = new ObjectMapper();
        DepartmentUI result = mapper.readValue(mvcResult.getResponse().getContentAsByteArray(),
                                               new TypeReference<>() {
                                               });
        Assert.assertNotNull(result);
        Assert.assertEquals("1", result.getDeptno());
        Assert.assertEquals("ACCOUNTING", result.getDname());
        Assert.assertEquals("LAS VEGAS", result.getLoc());

    }

    @Test
    public void testDeleteDepartmentWithItsEmployes() throws Exception {
        Mockito.when(departmentRequester.getDepartmentById("1")).thenReturn(expectedDepartment());
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/private/api/v1/departments/{id}", "1").contentType(MediaType.APPLICATION_JSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(content, Map.class);
        Assert.assertNotNull(result);
        Assert.assertEquals("success", result.get("Deleting department with its employes!"));

    }



    private List<Department> populateDepartmentsList(){
        return Arrays.asList(Department.builder()
                                       .deptno("1")
                                       .dname("ACCOUNTING")
                                       .loc("NEW YORK")
                                       .build(),
                             Department.builder()
                                       .deptno("2")
                                       .dname("RESEARCH")
                                       .loc("DALLAS")
                                       .build());
    }

    private Department expectedDepartment(){
        return Department.builder()
                         .deptno("1")
                         .dname("ACCOUNTING")
                         .loc("NEW YORK")
                         .build();
    }

    private Department expectedUpdatedDepartment(){
        return Department.builder()
                         .deptno("1")
                         .dname("ACCOUNTING")
                         .loc("LAS VEGAS")
                         .build();
    }

    private String mapToJson(DepartmentUI obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}
