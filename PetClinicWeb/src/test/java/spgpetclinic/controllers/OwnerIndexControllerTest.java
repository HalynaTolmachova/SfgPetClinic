package spgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import spgpetclinic.model.Owner;
import spgpetclinic.services.OwnerService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerIndexControllerTest {
    @Mock
    OwnerService ownerService;
    @InjectMocks
    OwnerIndexController ownerIndexController;
    Set<Owner> ownerSet;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(ownerIndexController)
                .build();
    }

    @Test
    void ownersIndex() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);
        mockMvc.perform(get("/owners/index"))
                .andExpect(view().name("owners/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("owners",hasSize(2)));

    }


    @Test
    void findOwner() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(status().isOk());

        verifyNoInteractions(ownerService);
    }
}