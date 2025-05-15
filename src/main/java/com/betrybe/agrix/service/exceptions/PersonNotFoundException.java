package com.betrybe.agrix.service.exceptions;

/**
 * Exception for when a person is not found.
 */
public class PersonNotFoundException extends NotFoundException {

  /**
   * Instantiates a new Person not found exception.
   */
  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }

}
