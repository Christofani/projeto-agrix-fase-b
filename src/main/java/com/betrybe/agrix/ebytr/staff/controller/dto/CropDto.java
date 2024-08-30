package com.betrybe.agrix.ebytr.staff.controller.dto;

import com.betrybe.agrix.ebytr.staff.entity.Crop;

/**
 * The type Crop dto.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    Long farmId
) {

  /**
   * From entity crop dto.
   *
   * @param crop the crop
   * @return the crop dto
   */
  public static CropDto fromEntity(Crop crop) {
    Long farmDto = crop.getFarm() != null ? crop.getFarm().getId() : null;

    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        farmDto
    );
  }
}
