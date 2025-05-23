package com.betrybe.agrix.service.exceptions;

/**
 * The type Not found exception.
 */
public class NotFoundException extends Exception {

  /**
   * Instantiates a new Not found exception.
   *
   * @param message the message
   */
  public NotFoundException(String message) {
    super(message);
  }
}
