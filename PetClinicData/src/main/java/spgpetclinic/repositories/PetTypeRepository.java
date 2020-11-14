package spgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import spgpetclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
