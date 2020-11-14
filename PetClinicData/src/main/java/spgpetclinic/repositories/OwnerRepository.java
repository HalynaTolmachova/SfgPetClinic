package spgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import spgpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Owner findByLastName(String lastName);
}
