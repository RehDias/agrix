package com.betrybe.agrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Fertilizer.
 */
@Entity
@Table(name = "fertilizers")
@Getter
@Setter
@NoArgsConstructor
public class Fertilizer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Name is Required")
  private String name;

  @ManyToMany(mappedBy = "fertilizers")
  private List<Crop> crops;

  private String brand;
  private String composition;

  /**
   * Instantiates a new Fertilizer.
   *
   * @param name        the name
   * @param brand       the brand
   * @param composition the composition
   */
  public Fertilizer(String name, String brand, String composition) {
    this.name = name;
    this.brand = brand;
    this.composition = composition;
  }
}
