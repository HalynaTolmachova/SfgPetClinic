package spgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spgpetclinic.model.*;
import spgpetclinic.services.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if(count==0){
            loadData();
        }


    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Joost");
        owner1.setLastName("de Pan");
        owner1.setAddress("Slaska");
        owner1.setCity("Gdynia");
        owner1.setTelephone("123123123");

        Pet joostPet = new Pet();
        joostPet.setPetType(savedDogPetType);
        joostPet.setOwner(owner1);
        joostPet.setBirthday(LocalDate.now());
        joostPet.setName("Rosco");
        owner1.getPets().add(joostPet);
        ownerService.save(owner1);

        Visit dogVisit = new Visit();
        dogVisit.setPet(joostPet);
        dogVisit.setDate(LocalDate.now());
        dogVisit.setDescription("Bad breath smells");
        visitService.save(dogVisit);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jack");
        owner2.setLastName("Borota");
        owner2.setAddress("Morska");
        owner2.setCity("Gdansk");
        owner2.setTelephone("09877889");
        Pet jackPet = new Pet();
        jackPet.setPetType(savedCatPetType);
        jackPet.setName("Kitty");
        jackPet.setBirthday(LocalDate.now());
        jackPet.setOwner(owner2);
        owner2.getPets().add(jackPet);
        ownerService.save(owner2);

        System.out.println("owners are loaded");

        Vet vet1 = new Vet();
        vet1.setFirstName("Alex");
        vet1.setLastName("Brim");
        vetService.save(vet1);
        Vet vet2 = new Vet ();
        vet2.setFirstName("Tod");
        vet2.setLastName("Perera");
        vetService.save(vet2);
        System.out.println("ovets are loaded");
    }
}
