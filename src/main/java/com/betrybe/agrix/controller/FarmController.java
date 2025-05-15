package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FarmCreationDto;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
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
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
@Tag(name = "Fazendas", description = "Gerenciamento de fazendas")
public class FarmController {
  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm farm dto.
   *
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FarmDto createFarm(@RequestBody FarmCreationDto farmCreationDto) {
    return FarmDto.toDto(farmService.create(farmCreationDto.toEntity()));
  }

  /**
   * Show all farms list.
   *
   * @return the list
   */
  @GetMapping
  @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_MANAGER"})
  public List<FarmDto> showAllFarms() {
    List<Farm> allFarms = farmService.findAll();

    return allFarms.stream().map(FarmDto::toDto).toList();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public FarmDto getFarmById(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.toDto(farmService.findById(id));
  }

  /**
   * Update farm farm dto.
   *
   * @param id              the id
   * @param farmCreationDto the farm creation dto
   * @return the farm dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PutMapping("/{id}")
  @Secured({"ROLE_ADMIN", "ROLE_MANAGER"})
  public FarmDto updateFarm(@PathVariable Long id, @RequestBody FarmCreationDto farmCreationDto)
      throws FarmNotFoundException {
    return FarmDto.toDto(farmService.update(id, farmCreationDto.toEntity()));
  }

  /**
   * Delete farm farm dto.
   *
   * @param id the id
   * @return the farm dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @DeleteMapping("/{id}")
  @Secured({"ROLE_ADMIN"})
  public FarmDto deleteFarm(@PathVariable Long id) throws FarmNotFoundException {
    return FarmDto.toDto(farmService.delete(id));
  }

  /**
   * Save farm crop crop dto.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the crop dto
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("/{farmId}/crops")
  @ResponseStatus(HttpStatus.CREATED)
  public CropDto saveFarmCrop(@PathVariable Long farmId,
      @RequestBody CropCreationDto cropCreationDto)
      throws FarmNotFoundException {
    return CropDto.toDto(farmService.setFarmCrop(farmId, cropCreationDto.toEntity()));
  }

  /**
   * Gets farm crops.
   *
   * @param farmId the farm id
   * @return the farm crops
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{farmId}/crops")
  public List<CropDto> getFarmCrops(@PathVariable Long farmId) throws FarmNotFoundException {
    List<Crop> allCrops = farmService.getFarmCrops(farmId);
    return allCrops.stream().map(CropDto::toDto).toList();
  }
}
