package com.betrybe.agrix.dto;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import java.time.LocalDate;

/**
 * The type Crop creation dto.
 */
public record CropCreationDto(String name, Double plantedArea, LocalDate plantedDate,
                              LocalDate harvestDate, Farm farm) {

  /**
   * To entity crop.
   *
   * @return the crop
   */
  public Crop toEntity() {
    return new Crop(name, plantedArea, plantedDate, harvestDate, farm);
  }
}
