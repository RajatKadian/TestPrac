package com.example.testprac;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import javax.swing.text.View;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
public class MyCOntrollerTest {

    Users users;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    StuRepository stuRepository;

    @Mock
    View mockView;


    @InjectMocks
    private MyCOntroller myCOntroller;

    @BeforeEach
    public void setup() throws ParseException
    {
        users = new Users();
        users.setId(1L);
        users .setName("Rajat");

        MockitoAnnotations.openMocks(this);



        mockMvc = MockMvcBuilders.standaloneSetup(myCOntroller).build();
    }

    @Test
    public void fun() throws Exception{

        List<Users> list = new ArrayList<Users>();

        list.add(users);
        list.add(users);

        when(stuRepository.findAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listStudents", list))
                .andExpect(view().name("home"))
                .andExpect((model().attribute("listStudents", hasSize(2))));

        verify(stuRepository, Mockito.times(1)).findAll();
        verifyNoMoreInteractions(stuRepository);


    }

    @Test
    public void del() throws Exception {

        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(stuRepository).deleteById(idCapture.capture());
        stuRepository.deleteById(1L);
        assertEquals(1L, idCapture.getValue());
        verify(stuRepository, times(1)).deleteById(1L);
    }

    @Test
    public void edi() throws  Exception {

        Users s2= new Users();
        s2.setId(1L);
        s2.setName("John");

        Long iid = 1L;

        when(stuRepository.findById(iid)).thenReturn(Optional.of(s2));

        mockMvc.perform(MockMvcRequestBuilders.get("/editS").param("id", String.valueOf(1L)))
                .andExpect(status().isOk())
                .andExpect(model().attribute("student", s2))
                .andExpect(view().name("editS"));

        verify(stuRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(stuRepository);




    }


//    @Before
//    public void setUp() throws Exception
//    {
//        mockMvc = MockMvcBuilders.standaloneSetup(myCOntroller).build();
//
//    }
//
//    @Test
//    public void hello() throws Exception {
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("hello"));
//
//
//
//
//    }
}