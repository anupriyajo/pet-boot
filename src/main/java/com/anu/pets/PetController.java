package com.anu.pets;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class PetController {
    private final PetRepository repository;

    public PetController(PetRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/pets")
    CollectionModel<EntityModel<Pet>> all() {
        List<EntityModel<Pet>> pets = repository.findAll().stream()
            .map(employee -> EntityModel.of(employee,
                linkTo(methodOn(PetController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(PetController.class).all()).withRel("pets")))
            .collect(Collectors.toList());

        return CollectionModel.of(pets, linkTo(methodOn(PetController.class).all()).withSelfRel());
    }

    @PostMapping("/pets")
    Pet newPet(@RequestBody Pet newPet) {
        return repository.save(newPet);
    }

    @GetMapping("/pets/{id}")
    EntityModel<Pet> one(@PathVariable Long id) {

        final var pet = repository.findById(id)
            .orElseThrow(() -> new PetNotFoundException(id));

        return EntityModel.of(pet,
            linkTo(methodOn(PetController.class).one(id)).withSelfRel(),
            linkTo(methodOn(PetController.class).all()).withRel("pets"));
    }

    @DeleteMapping("/pets/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/pets/{id}")
    Pet replacePet(@RequestBody Pet newPet, @PathVariable Long id) {

        return repository.findById(id)
            .map(pet -> {
                pet.setName(newPet.getName());
                pet.setAge(newPet.getAge());
                return repository.save(pet);
            })
            .orElseGet(() -> {
                newPet.setId(id);
                return repository.save(newPet);
            });
    }
}
