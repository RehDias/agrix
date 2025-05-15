package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.PersonCreationDto;
import com.betrybe.agrix.dto.PersonDto;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.service.PersonService;
import com.betrybe.agrix.service.exceptions.PersonNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
@Tag(name = "Cadastro", description = "Gerenciamento dos cadastros de pessoas")
public class PersonController {
  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person person dto.
   *
   * @param personCreationDto the person creation dto
   * @return the person dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PersonDto createPerson(@RequestBody PersonCreationDto personCreationDto) {
    return PersonDto.toDto(personService.create(personCreationDto.toEntity()));
  }

  /**
   * Show all persons list.
   *
   * @return the list
   */
  @GetMapping
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
  public List<PersonDto> showAllPersons() {
    List<Person> personList = personService.listAll();

    return personList.stream().map(PersonDto::toDto).toList();
  }

  /**
   * Gets person by id.
   *
   * @param id the id
   * @return the person by id
   * @throws PersonNotFoundException the person not found exception
   */
  @GetMapping("/{id}")
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN", "ROLE_USER"})
  public PersonDto getPersonById(@PathVariable Long id) throws PersonNotFoundException {
    return PersonDto.toDto(personService.getPersonById(id));
  }

  /**
   * Update person person dto.
   *
   * @param id                the id
   * @param personCreationDto the person creation dto
   * @return the person dto
   * @throws PersonNotFoundException the person not found exception
   */
  @PutMapping("/{id}")
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN", "ROLE_USER"})
  public PersonDto updatePerson(@PathVariable Long id,
      @RequestBody PersonCreationDto personCreationDto) throws PersonNotFoundException {
    return PersonDto.toDto(personService.update(id, personCreationDto.toEntity()));
  }

  /**
   * Delete person person dto.
   *
   * @param id the id
   * @return the person dto
   * @throws PersonNotFoundException the person not found exception
   */
  @DeleteMapping("/{id}")
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN", "ROLE_USER"})
  public PersonDto deletePerson(@PathVariable Long id) throws PersonNotFoundException {
    return PersonDto.toDto(personService.delete(id));
  }
}
