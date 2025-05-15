package com.betrybe.agrix.security;

import lombok.Getter;
import lombok.Setter;

/**
 * Enum representing a Role.
 */
@Getter
public enum Role {
  ADMIN("ROLE_ADMIN"),
  MANAGER("ROLE_MANAGER"),
  USER("ROLE_USER");

  private final String name;

  Role(String name) {
    this.name = name;
  }

}