package spgpetclinic.services;

import org.springframework.stereotype.Service;
import spgpetclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CrudService <Owner,Long> {
    Owner findByLastName(String lastName);
}
