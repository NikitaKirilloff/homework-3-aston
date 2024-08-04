package kirilloffna.homework3.controller.impl;

import static kirilloffna.homework3.controller.ControllerMessages.CITY_ADDED;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_CONT_CITY_NOT_FOUND;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_CONT_SAVE_CITY;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_CONT_SAVE_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_CONT_UPDATE_CITY;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_CONT_UPDATE_ERROR;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_NOT_FOUND;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_UPDATED;
import static kirilloffna.homework3.controller.ControllerMessages.SERVICE_UNAVAILABLE;

import kirilloffna.homework3.controller.CityController;
import kirilloffna.homework3.dto.CityDTO;
import kirilloffna.homework3.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для создания сущностей городов. Предоставляет REST API для создания, обновления и
 * получения информации о городах.
 */
@Slf4j
@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityControllerImpl implements CityController {

  private final CityService cityService;

  @PostMapping()
  public ResponseEntity<String> createCity(@RequestBody CityDTO cityDTO) {
    log.info(CITY_CONT_SAVE_CITY, cityDTO);
    try {
      cityService.saveCity(cityDTO);
      return ResponseEntity.ok(CITY_ADDED);
    } catch (Exception e) {
      log.error(CITY_CONT_SAVE_ERROR, e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(SERVICE_UNAVAILABLE);
    }
  }

  @PutMapping()
  public ResponseEntity<String> updateCity(@RequestBody CityDTO cityDTO) {
    log.info(CITY_CONT_UPDATE_CITY, cityDTO.getId());
    try {
      cityService.updateCity(cityDTO);
      return ResponseEntity.ok(CITY_UPDATED);
    } catch (NotFoundException e) {
      log.warn(CITY_CONT_CITY_NOT_FOUND, cityDTO.getId());
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(CITY_NOT_FOUND + cityDTO.getId());
    } catch (Exception e) {
      log.error(CITY_CONT_UPDATE_ERROR, cityDTO.getId(), e);
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
          .body(SERVICE_UNAVAILABLE);
    }
  }
}
