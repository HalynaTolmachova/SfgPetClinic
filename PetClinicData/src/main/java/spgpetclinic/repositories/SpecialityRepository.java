package spgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import spgpetclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
