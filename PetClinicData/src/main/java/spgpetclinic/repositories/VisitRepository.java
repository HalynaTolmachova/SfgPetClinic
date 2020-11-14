package spgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import spgpetclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
