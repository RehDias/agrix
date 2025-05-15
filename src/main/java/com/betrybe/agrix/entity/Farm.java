package com.betrybe.agrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Farm.
 */
@Entity
@Table(name = "farms")
@Getter
@Setter
@NoArgsConstructor
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Name is Required")
  private String name;
  private Double size;

  @OneToMany(mappedBy = "farm")
  private List<Crop> crops;

  /**
   * Instantiates a new Farm.
   *
   * @param name the name
   * @param size the size
   */
  public Farm(String name, Double size) {
    this.name = name;
    this.size = size;
  }
}
