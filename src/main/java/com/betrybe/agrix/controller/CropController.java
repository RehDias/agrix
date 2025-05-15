package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exceptions.CropNotFoundException;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.findAll();

    return allCrops.stream().map(CropDto::toDto).toList();
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}")
  public CropDto getCropById(@PathVariable Long id) throws CropNotFoundException {
    return CropDto.toDto(cropService.findById(id));
  }

  /**
   * Gets crop by harvest date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by harvest date
   */
  @GetMapping("/search")
  public List<CropDto> getCropByHarvestDate(
      @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
      @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
    List<Crop> resultCrops = cropService.searchCropByHarvestDate(start, end);

    return resultCrops.stream().map(CropDto::toDto).toList();
  }

  /**
   * Sets fertilizer to crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the fertilizer to crop
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  @ResponseStatus(HttpStatus.CREATED)
  public String setFertilizerToCrop(@PathVariable Long cropId, @PathVariable Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    cropService.setFertilizerToCrop(cropId, fertilizerId);
    return "Fertilizante e plantação associados com sucesso!";
  }

  /**
   * Gets crop fertilizers.
   *
   * @param cropId the crop id
   * @return the crop fertilizers
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  public List<FertilizerDto> getCropFertilizers(@PathVariable Long cropId)
      throws CropNotFoundException {
    List<Fertilizer> fertilizers = cropService.getCropFertilizers(cropId);

    return fertilizers.stream().map(FertilizerDto::toDto).toList();
  }
}
