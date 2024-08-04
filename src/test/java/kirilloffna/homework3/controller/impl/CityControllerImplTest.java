package kirilloffna.homework3.controller.impl;

import static kirilloffna.homework3.controller.ControllerMessages.CITY_ADDED;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_NOT_FOUND;
import static kirilloffna.homework3.controller.ControllerMessages.CITY_UPDATED;
import static kirilloffna.homework3.controller.ControllerMessages.SERVICE_UNAVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import kirilloffna.homework3.dto.CityDTO;
import kirilloffna.homework3.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Тестовый класс для CityControllerImpl.
 */
@ExtendWith(MockitoExtension.class)
class CityControllerImplTest {

  @Mock
  private CityService cityService;

  @InjectMocks
  private CityControllerImpl cityController;

  private CityDTO cityDTO;

  /**
   * Настройка данных для тестов.
   */
  @BeforeEach
  public void setUp() {
    cityDTO = new CityDTO();
    cityDTO.setId(1L);
    cityDTO.setName("Test City");
    cityDTO.setPopulation(100000);
    cityDTO.setHasMetro(true);
  }

  /**
   * Тест для метода createCity. Проверяет, что город успешно создается.
   */
  @Test
  void createCity_ShouldReturnOk() {
    ResponseEntity<String> response = cityController.createCity(cityDTO);

    verify(cityService).saveCity(cityDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(CITY_ADDED, response.getBody());
  }

  /**
   * Тест для метода createCity. Проверяет, что возвращается статус Service Unavailable при
   * возникновении исключения.
   */
  @Test
  void createCity_ShouldReturnServiceUnavailable_OnException() {
    doThrow(new RuntimeException()).when(cityService).saveCity(any(CityDTO.class));

    ResponseEntity<String> response = cityController.createCity(cityDTO);

    verify(cityService).saveCity(cityDTO);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertEquals(SERVICE_UNAVAILABLE, response.getBody());
  }

  /**
   * Тест для метода updateCity. Проверяет, что информация о городе успешно обновляется.
   *
   * @throws NotFoundException если город не найден
   */
  @Test
  void updateCity_ShouldReturnOk() throws NotFoundException {
    ResponseEntity<String> response = cityController.updateCity(cityDTO);

    verify(cityService).updateCity(cityDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(CITY_UPDATED, response.getBody());
  }

  /**
   * Тест для метода updateCity. Проверяет, что возвращается статус Not Found, если город не
   * найден.
   *
   * @throws NotFoundException если город не найден
   */
  @Test
  void updateCity_ShouldReturnNotFound_WhenCityNotFound() throws NotFoundException {
    doThrow(new NotFoundException()).when(cityService).updateCity(any(CityDTO.class));

    ResponseEntity<String> response = cityController.updateCity(cityDTO);

    verify(cityService).updateCity(cityDTO);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(CITY_NOT_FOUND + cityDTO.getId(), response.getBody());
  }

  /**
   * Тест для метода updateCity. Проверяет, что возвращается статус Service Unavailable при
   * возникновении исключения.
   *
   * @throws NotFoundException если город не найден
   */
  @Test
  void updateCity_ShouldReturnServiceUnavailable_OnException() throws NotFoundException {
    doThrow(new RuntimeException()).when(cityService).updateCity(any(CityDTO.class));

    ResponseEntity<String> response = cityController.updateCity(cityDTO);

    verify(cityService).updateCity(cityDTO);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertEquals(SERVICE_UNAVAILABLE, response.getBody());
  }
}