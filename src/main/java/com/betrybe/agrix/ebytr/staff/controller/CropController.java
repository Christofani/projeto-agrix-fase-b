package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.controller.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.controller.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService       the crop service.ebytr.staff.
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {

    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
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
    return CropDto.fromEntity(
            cropService.findById(id)
    );
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public List<CropDto> getAllCrops() {
    List<Crop> allCrops = cropService.findAll();
    return allCrops.stream()
            .map(CropDto::fromEntity)
            .collect(Collectors.toList());
  }

  /**
   * Search crops by date range list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  @GetMapping("search")
  public List<CropDto> searchCropsByDateRange(
          @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
          @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

    return cropService.getCropsBySearchDate(start, end).stream()
            .map(CropDto::fromEntity)
            .collect(Collectors.toList());
  }

  /**
   * Associate fertilizer with crop response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateFertilizerWithCrop(
          @PathVariable Long cropId,
          @PathVariable Long fertilizerId
  ) throws CropNotFoundException, FertilizerNotFoundException {

    Crop crop = cropService.findById(cropId);
    Fertilizer fertilizer = fertilizerService.findById(fertilizerId);

    cropService.associateFertilizer(crop, fertilizer);

    String successMessage = "Fertilizante e plantação associados com sucesso!";

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

  /**
   * Gets all fertilizers.
   *
   * @param cropId the crop id
   * @return the all fertilizers
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers(
          @PathVariable Long cropId
  ) throws CropNotFoundException {
    Crop crop = cropService.findById(cropId);

    List<Fertilizer> fertilizers = crop.getFertilizers();

    List<FertilizerDto> fertilizerDtos = fertilizers.stream()
            .map(FertilizerDto::fromEntity)
            .collect(Collectors.toList());

    return ResponseEntity.ok(fertilizerDtos);
  }

}
