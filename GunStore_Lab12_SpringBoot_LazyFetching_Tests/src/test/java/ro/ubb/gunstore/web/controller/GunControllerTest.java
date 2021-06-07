package ro.ubb.gunstore.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.ubb.gunstore.core.model.Gun;
import ro.ubb.gunstore.core.service.GunService;
import ro.ubb.gunstore.web.converter.GunConverter;
import ro.ubb.gunstore.web.dto.GunDto;

import javax.print.attribute.standard.Media;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GunControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private GunController gunController;

    @Mock
    private GunService gunService;

    @Mock
    private GunConverter gunConverter;

    private Gun gun1;
    private Gun gun2;
    private GunDto gunDto1;
    private GunDto gunDto2;

    @Before
    public void setup() throws Exception {
        initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(gunController)
                .build();
        initData();
    }

    @Test
    public void getGuns() throws Exception {
        List<Gun> guns = Arrays.asList(gun1, gun2);
        List<GunDto> gunDtos = Arrays.asList(gunDto1, gunDto2);

        when(gunService.getAllGuns()).thenReturn(guns);
        when(gunConverter.convertModelsToDtos(guns)).thenReturn(gunDtos);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/guns"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].model", anyOf(is("model1"), is("model2"))))
                .andExpect(jsonPath("$[1].type", anyOf(is("type1"), is("type2"))));

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);

        verify(gunService, times(1)).getAllGuns();
        verify(gunConverter, times(1)).convertModelsToDtos(guns);
        verifyNoMoreInteractions(gunService, gunConverter);
    }

    @Test
    public void addGun() throws Exception {
        when(gunService.addGun(gun1)).thenReturn(gun1);
        when(gunConverter.convertDtoToModel(gunDto1)).thenReturn(gun1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/guns")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(toJsonString(gunDto1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void updateGun() throws Exception {
        var newGun = Gun.builder()
                .model(gun1.getModel())
                .type(gun1.getType())
                .manufacturer(gun1.getManufacturer())
                .price(gun1.getPrice())
                .weight(gun1.getWeight())
                .build();
        newGun.setId(gun1.getId());

        when(gunService.updateGun(newGun)).thenReturn(gun1);

        when(gunConverter.convertModelToDto(gun1)).thenReturn(gunDto1);

//        Map<String, StudentDto> studentDtoMap = new HashMap<>();
//        studentDtoMap.put("student", studentDto1);

        ResultActions resultActions = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/guns/{gunId}", gun1.getId(), gunDto1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(gunDto1)))
                .andExpect(status().isOk());

        String result = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println(result);
       // verify(gunService, times(1)).updateGun(newGun);
        // verify(studentConverter, times(1)).convertModelToDto(student1);
        // verifyNoMoreInteractions(studentService, studentConverter);
    }


    private void initData() {
        gun1 = Gun.builder().model("model1").type("type1").manufacturer("manufacturer1").price(11).weight(11).build();
        gun1.setId(1L);
        gun2 = Gun.builder().model("model2").type("type2").manufacturer("manufacturer2").price(22).weight(22).build();
        gun2.setId(2L);

        gunDto1 = createGunDto(gun1);
        gunDto2 = createGunDto(gun2);

    }

    private String toJsonString(GunDto gunDto) {
        try {
            return new ObjectMapper().writeValueAsString(gunDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private GunDto createGunDto(Gun gun) {
        GunDto gunDto = GunDto.builder()
                .model(gun.getModel())
                .type(gun.getType())
                .manufacturer(gun.getManufacturer())
                .price(gun.getPrice())
                .weight(gun.getWeight())
                .build();
        gunDto.setId(gun.getId());
        return gunDto;
    }

}
