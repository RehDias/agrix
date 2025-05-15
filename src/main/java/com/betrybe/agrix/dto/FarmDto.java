package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * To dto farm dto.
   *
   * @param farm the farm
   * @return the farm dto
   */
  public static FarmDto toDto(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }
}
