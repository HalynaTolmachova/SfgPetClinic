package spgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import spgpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
