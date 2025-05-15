package com.betrybe.agrix.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Crop.
 */
@Entity
@Table(name = "crops")
@Getter
@Setter
@NoArgsConstructor
public class Crop {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Name is Required")
  private String name;

  @Column(name = "planted_area")
  private Double plantedArea;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "farm_id")
  private Farm farm;

  @ManyToMany
  @JoinTable(
      name = "crop_fertilizer",
      joinColumns = @JoinColumn(name = "crop_id"),
      inverseJoinColumns = @JoinColumn(name = "fertilizer_id")
  )
  private Set<Fertilizer> fertilizers = new HashSet<>();

  @Column(name = "planted_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  /**
   * Instantiates a new Crop.
   *
   * @param name        the name
   * @param plantedArea the planted area
   * @param farm        the farm
   * @param plantedDate the planted date
   * @param harvestDate the harvest date
   */
  public Crop(String name, Double plantedArea, LocalDate plantedDate,
      LocalDate harvestDate, Farm farm) {
    this.name = name;
    this.plantedArea = plantedArea;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
    this.farm = farm;
  }
}
