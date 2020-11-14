package spgpetclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spgpetclinic.model.Owner;
import spgpetclinic.services.PetService;
import spgpetclinic.services.PetTypeService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;
    Long id = 1L;
    String name = "Jack";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(id).build());

    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void saveExistingId() {
        Long idSecond = 2L;
        Owner owner2 = Owner.builder().id(idSecond).build();
        ownerServiceMap.save(owner2);
        assertEquals(idSecond,owner2.getId());
    }

    @Test
    void saveNoId() {
        Owner owner2 = Owner.builder().build();
        ownerServiceMap.save(owner2);
        assertNotNull(owner2);
        assertNotNull(owner2.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(id));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(id);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Owner ownerId =ownerServiceMap.findById(id);
        ownerId.setFirstName(name);
        assertEquals(name, ownerId.getFirstName());
    }

    @Test
    void findByLastName() {
        Owner ownerId =ownerServiceMap.findById(id);
        ownerId.setLastName(name);
        assertEquals(name,ownerServiceMap.findByLastName(name).getLastName());

    }
}