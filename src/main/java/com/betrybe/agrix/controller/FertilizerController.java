package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FertilizerCreationDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
@Tag(name = "Fertilizantes", description = "Gerenciamento dos fertilizantes")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create fertilizer fertilizer dto.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the fertilizer dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerCreationDto fertilizerCreationDto) {
    return FertilizerDto.toDto(fertilizerService.create(fertilizerCreationDto.toEntity()));
  }

  /**
   * Show all fertilizers list.
   *
   * @return the list
   */
  @GetMapping
  @Secured({"ROLE_ADMIN"})
  public List<FertilizerDto> showAllFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.findAll();

    return allFertilizers.stream().map(FertilizerDto::toDto).toList();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public FertilizerDto getFertilizerById(@PathVariable Long id)
      throws FertilizerNotFoundException {
    return FertilizerDto.toDto(fertilizerService.findById(id));
  }

  /**
   * Update fertilizer fertilizer dto.
   *
   * @param id                    the id
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the fertilizer dto
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PutMapping("/{id}")
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN", "ROLE_USER"})
  public FertilizerDto updateFertilizer(
      @PathVariable Long id,
      @RequestBody FertilizerCreationDto fertilizerCreationDto)
      throws FertilizerNotFoundException {
    return FertilizerDto.toDto(fertilizerService.update(id, fertilizerCreationDto.toEntity()));
  }

  /**
   * Delete fertilizer fertilizer dto.
   *
   * @param id the id
   * @return the fertilizer dto
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @DeleteMapping("/{id}")
  @Secured({"ROLE_ADMIN"})
  public FertilizerDto deleteFertilizer(@PathVariable Long id) throws FertilizerNotFoundException {
    return FertilizerDto.toDto(fertilizerService.delete(id));
  }

}
