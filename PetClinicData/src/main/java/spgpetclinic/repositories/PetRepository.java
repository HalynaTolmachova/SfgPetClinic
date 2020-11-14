package spgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import spgpetclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
