package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exceptions.CropNotFoundException;
import com.betrybe.agrix.service.exceptions.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {
  private final FarmRepository farmRepository;
  private final CropService cropService;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropService    the crop service
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropService cropService) {
    this.farmRepository = farmRepository;
    this.cropService = cropService;
  }

  /**
   * Find by id farm.
   *
   * @param id the id
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Create farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm create(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Update farm.
   *
   * @param id   the id
   * @param farm the farm
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm update(Long id, Farm farm) throws FarmNotFoundException {
    Farm farmFromDb = findById(id);

    farmFromDb.setName(farm.getName());
    farmFromDb.setSize(farm.getSize());

    return farmRepository.save(farmFromDb);
  }

  /**
   * Delete farm.
   *
   * @param id the id
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm delete(Long id) throws FarmNotFoundException {
    Farm farm = findById(id);

    farmRepository.deleteById(id);

    return farm;
  }

  /**
   * Sets farm crop.
   *
   * @param farmId the farm id
   * @param crop   the crop
   * @return the farm crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop setFarmCrop(Long farmId, Crop crop)
      throws FarmNotFoundException {
    Farm farm = findById(farmId);
    crop.setFarm(farm);
    farm.getCrops().add(crop);
    return cropService.create(crop);
  }

  /**
   * * Gets farm crops.
   *
   * @param farmId the farm id
   * @return the farm crops
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> getFarmCrops(Long farmId) throws FarmNotFoundException {
    Farm farm = findById(farmId);
    return farm.getCrops();
  }

  /**
   * Delete farm crop farm.
   *
   * @param farmId the farm id
   * @param cropId the crop id
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   * @throws CropNotFoundException the crop not found exception
   */
  public Farm deleteFarmCrop(Long farmId, Long cropId)
      throws FarmNotFoundException, CropNotFoundException {
    Farm farm = findById(farmId);
    Crop crop = cropService.findById(cropId);

    farm.getCrops().remove(crop);

    return farmRepository.save(farm);
  }
}
