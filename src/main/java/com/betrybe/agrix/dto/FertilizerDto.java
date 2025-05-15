package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * The type Fertilizer dto.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * To dto fertilizer dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer dto
   */
  public static FertilizerDto toDto(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition());
  }
}
