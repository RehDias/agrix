package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

/**
 * The type Person dto.
 */
public record PersonDto(Long id, String username, Role role) {

  /**
   * To dto person dto.
   *
   * @param person the person
   * @return the person dto
   */
  public static PersonDto toDto(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}
