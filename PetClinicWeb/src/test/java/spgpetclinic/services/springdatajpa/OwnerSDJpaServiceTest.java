package spgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import spgpetclinic.model.Owner;
import spgpetclinic.repositories.OwnerRepository;
import spgpetclinic.repositories.PetRepository;
import spgpetclinic.repositories.PetTypeRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    String lastName = "Smith";
    Long ownerId = 1L;
    Owner returnOwner;


    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    @Test
    void findByLastName() {
        when(ownerSDJpaService.findByLastName(any())).thenReturn(returnOwner);
        assertEquals(lastName,ownerSDJpaService.findByLastName(lastName).getLastName());

    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        Owner owner = Owner.builder().id(2L).lastName("Cos").build();
        ownerSet.add(owner);
        ownerSet.add(returnOwner);
        when(ownerSDJpaService.findAll()).thenReturn(ownerSet);
        assertNotNull(ownerSDJpaService.findAll());
        assertEquals(2,ownerSDJpaService.findAll().size());
    }

    @Test
    void findById() {
      when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
      assertNotNull(ownerSDJpaService.findById(ownerId));
      assertEquals(ownerId,ownerSDJpaService.findById(ownerId).getId());

    }

    @Test
    void save() {
        Owner owner2 = Owner.builder().id(2L).lastName("Cos").build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner savedOvner = ownerSDJpaService.save(owner2);
        assertNotNull(savedOvner);
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);
        verify(ownerRepository).delete(any());

    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(ownerId);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}