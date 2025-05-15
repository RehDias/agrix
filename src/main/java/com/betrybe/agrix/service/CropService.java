package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exceptions.CropNotFoundException;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;
  private final FertilizerService fertilizerService;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerService    the fertilizer service
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FertilizerService fertilizerService,
      FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerService = fertilizerService;
    this.fertilizerRepository = fertilizerRepository;
  }


  /**
   * Find by id crop.
   *
   * @param id the id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Crop> findAll() {
    return cropRepository.findAll().stream().filter(crop -> crop.getFarm() != null).toList();
  }

  /**
   * Create crop.
   *
   * @param crop the crop
   * @return the crop
   */
  public Crop create(Crop crop) {
    return cropRepository.save(crop);
  }

  /**
   * Update crop.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop update(Long id, Crop crop) throws CropNotFoundException {
    Crop cropFromDb = findById(id);

    cropFromDb.setName(crop.getName());
    cropFromDb.setPlantedArea(crop.getPlantedArea());
    cropFromDb.setFarm(crop.getFarm());

    return cropRepository.save(cropFromDb);
  }

  /**
   * Delete crop.
   *
   * @param id the id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop delete(Long id) throws CropNotFoundException {
    Crop crop = findById(id);

    cropRepository.deleteById(id);

    return crop;
  }

  /**
   * Search crop by harvest date list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<Crop> searchCropByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Sets fertilizer to crop.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public void setFertilizerToCrop(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    crop.getFertilizers().add(fertilizer);
    fertilizer.getCrops().add(crop);

    cropRepository.save(crop);
  }

  /**
   * Gets crop fertilizers.
   *
   * @param cropId the crop id
   * @return the crop fertilizers
   * @throws CropNotFoundException the crop not found exception
   */
  public List<Fertilizer> getCropFertilizers(Long cropId) throws CropNotFoundException {
    Crop crop = findById(cropId);
    return fertilizerRepository.findAllByCropsContaining(crop);
  }
}
