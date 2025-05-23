package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.repository.PersonRepository;
import com.betrybe.agrix.service.exceptions.PersonNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService implements UserDetailsService {

  private final PersonRepository personRepository;

  /**
   * Instantiates a new Person service.
   *
   * @param personRepository the person repository
   */
  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Returns a person for a given ID.
   *
   * @param id the id
   * @return the person by id
   * @throws PersonNotFoundException the person not found exception
   */
  public Person getPersonById(Long id) throws PersonNotFoundException {
    return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
  }

  /**
   * Returns a person for a given username.
   *
   * @param username the username
   * @return the person by username
   * @throws PersonNotFoundException the person not found exception
   */
  public Person getPersonByUsername(String username) throws PersonNotFoundException {
    return personRepository.findByUsername(username).orElseThrow(PersonNotFoundException::new);
  }

  /**
   * Creates a new person.
   *
   * @param person the person
   * @return the person
   */
  public Person create(Person person) {
    String hashedPassword = new BCryptPasswordEncoder().encode(person.getPassword());
    person.setPassword(hashedPassword);

    return personRepository.save(person);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return personRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
  }

  /**
   * List all list.
   *
   * @return the list
   */
  public List<Person> listAll() {
    return personRepository.findAll();
  }

  /**
   * Update person.
   *
   * @param id     the id
   * @param person the person
   * @return the person
   * @throws PersonNotFoundException the person not found exception
   */
  public Person update(Long id, Person person) throws PersonNotFoundException {
    Person personFromDb = getPersonById(id);
    personFromDb.setUsername(person.getUsername());
    personFromDb.setPassword(person.getPassword());

    return personRepository.save(personFromDb);
  }

  /**
   * Delete person.
   *
   * @param id the id
   * @return the person
   * @throws PersonNotFoundException the person not found exception
   */
  public Person delete(Long id) throws PersonNotFoundException {
    Person person = getPersonById(id);
    personRepository.deleteById(id);

    return person;
  }
}
