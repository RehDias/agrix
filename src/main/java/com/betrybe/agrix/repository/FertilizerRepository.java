package com.betrybe.agrix.repository;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Fertilizer repository.
 */
@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

  /**
   * Find all by crops containing list.
   *
   * @param crop the crop
   * @return the list
   */
  List<Fertilizer> findAllByCropsContaining(Crop crop);
}
