package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exceptions.FertilizerNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {
  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Fertilizer service.
   *
   * @param fertilizerRepository the fertilizer repository
   */
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Find by id fertilizer.
   *
   * @param id the id
   * @return the fertilizer
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Fertilizer findById(Long id) throws FertilizerNotFoundException {
    return fertilizerRepository.findById(id).orElseThrow(FertilizerNotFoundException::new);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Fertilizer> findAll() {
    return fertilizerRepository.findAll();
  }

  /**
   * Create fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public Fertilizer create(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * Update fertilizer.
   *
   * @param id         the id
   * @param fertilizer the fertilizer
   * @return the fertilizer
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Fertilizer update(Long id, Fertilizer fertilizer) throws FertilizerNotFoundException {
    Fertilizer fertilizerFromDb = findById(id);

    fertilizerFromDb.setName(fertilizer.getName());
    fertilizerFromDb.setBrand(fertilizer.getBrand());
    fertilizerFromDb.setComposition(fertilizer.getComposition());

    return fertilizerRepository.save(fertilizerFromDb);
  }

  /**
   * Delete fertilizer.
   *
   * @param id the id
   * @return the fertilizer
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public Fertilizer delete(Long id) throws FertilizerNotFoundException {
    Fertilizer fertilizer = findById(id);

    fertilizerRepository.deleteById(id);

    return fertilizer;
  }
}
