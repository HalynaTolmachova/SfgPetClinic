package spgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import spgpetclinic.model.Pet;
import spgpetclinic.services.CrudService;
import spgpetclinic.services.PetService;

import java.util.Set;
@Service
@Profile({"default","map"})
public class PetServiceMap extends AbstractMapService<Pet,Long> implements PetService {
    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);

    }

    @Override
    public Pet save(Pet object) {
        return super.save(object);
    }

    @Override
    public Pet findById(Long id) {
        return null;
    }
}