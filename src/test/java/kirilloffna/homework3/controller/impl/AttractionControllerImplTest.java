package kirilloffna.homework3.controller.impl;

import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_ADDED;
import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_DELETED;
import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_NOT_FOUND;
import static kirilloffna.homework3.controller.ControllerMessages.ATTRACTION_UPDATED;
import static kirilloffna.homework3.controller.ControllerMessages.SERVICE_UNAVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import kirilloffna.homework3.dto.AttractionDTO;
import kirilloffna.homework3.model.TypeOfAttraction;
import kirilloffna.homework3.service.AttractionService;
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
 * Тестовый класс для AttractionControllerImpl.
 */
@ExtendWith(MockitoExtension.class)
class AttractionControllerImplTest {

  @Mock
  private AttractionService attractionService;

  @InjectMocks
  private AttractionControllerImpl attractionController;

  private AttractionDTO attractionDTO;

  /**
   * Настройка данных для тестов.
   */
  @BeforeEach
  void setUp() {
    attractionDTO = new AttractionDTO();
    attractionDTO.setId(1L);
    attractionDTO.setName("Test Attraction");
    attractionDTO.setType(TypeOfAttraction.MUSEUM);
  }

  /**
   * Тест для метода getAttractionById. Проверяет, что достопримечательность успешно возвращается
   * по идентификатору.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void getAttractionById_ShouldReturnOk() throws NotFoundException {
    when(attractionService.getAttractionById(1L)).thenReturn(attractionDTO);

    ResponseEntity<AttractionDTO> response = attractionController.getAttractionById(1L);

    verify(attractionService).getAttractionById(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(attractionDTO, response.getBody());
  }

  /**
   * Тест для метода getAttractionById. Проверяет, что возвращается статус Not Found, если
   * достопримечательность не найдена.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void getAttractionById_ShouldReturnNotFound() throws NotFoundException {
    doThrow(new NotFoundException()).when(attractionService).getAttractionById(1L);

    ResponseEntity<AttractionDTO> response = attractionController.getAttractionById(1L);

    verify(attractionService).getAttractionById(1L);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  /**
   * Тест для метода getAttractionById. Проверяет, что возвращается статус Service Unavailable при
   * возникновении исключения.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void getAttractionById_ShouldReturnServiceUnavailable_OnException()
      throws NotFoundException {
    doThrow(new RuntimeException()).when(attractionService).getAttractionById(1L);

    ResponseEntity<AttractionDTO> response = attractionController.getAttractionById(1L);

    verify(attractionService).getAttractionById(1L);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  /**
   * Тест для метода getAttractions. Проверяет, что возвращается список всех достопримечательностей.
   */
  @Test
  void getAttractions_ShouldReturnOk() {
    when(attractionService.getAllAttractions(false, null)).thenReturn(List.of(attractionDTO));

    ResponseEntity<List<AttractionDTO>> response = attractionController.getAttractions(false, null);

    verify(attractionService).getAllAttractions(false, null);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(List.of(attractionDTO), response.getBody());
  }

  /**
   * Тест для метода getAttractions. Проверяет, что возвращается статус Service Unavailable при
   * возникновении исключения.
   */
  @Test
  void getAttractions_ShouldReturnServiceUnavailable_OnException() {
    when(attractionService.getAllAttractions(true, null)).thenThrow(new RuntimeException());

    ResponseEntity<List<AttractionDTO>> response = attractionController.getAttractions(true, null);

    verify(attractionService).getAllAttractions(true, null);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertEquals(Collections.emptyList(), response.getBody());
  }

  /**
   * Тест для метода getAttractionsByCityId. Проверяет, что возвращается список
   * достопримечательностей по идентификатору города.
   *
   * @throws NotFoundException если аттракции не найдены
   */
  @Test
  void getAttractionsByCityId_ShouldReturnOk() throws NotFoundException {
    when(attractionService.getAttractionsByCityId(1L)).thenReturn(List.of(attractionDTO));

    ResponseEntity<List<AttractionDTO>> response = attractionController.getAttractionsByCityId(1L);

    verify(attractionService).getAttractionsByCityId(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(List.of(attractionDTO), response.getBody());
  }

  /**
   * Тест для метода getAttractionsByCityId. Проверяет, что возвращается статус Not Found, если
   * достопримечательности не найдены.
   *
   * @throws NotFoundException если аттракции не найдены
   */
  @Test
  void getAttractionsByCityId_ShouldReturnNotFound() throws NotFoundException {
    doThrow(new NotFoundException()).when(attractionService).getAttractionsByCityId(1L);

    ResponseEntity<List<AttractionDTO>> response = attractionController.getAttractionsByCityId(1L);

    verify(attractionService).getAttractionsByCityId(1L);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(Collections.emptyList(), response.getBody());
  }

  /**
   * Тест для метода getAttractionsByCityId. Проверяет, что возвращается статус Service Unavailable
   * при возникновении исключения.
   *
   * @throws NotFoundException если аттракции не найдены
   */
  @Test
  void getAttractionsByCityId_ShouldReturnServiceUnavailable_OnException()
      throws NotFoundException {
    doThrow(new RuntimeException()).when(attractionService).getAttractionsByCityId(1L);

    ResponseEntity<List<AttractionDTO>> response = attractionController.getAttractionsByCityId(1L);

    verify(attractionService).getAttractionsByCityId(1L);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertEquals(Collections.emptyList(), response.getBody());
  }

  /**
   * Тест для метода createAttraction. Проверяет, что достопримечательность успешно создается.
   */
  @Test
  void createAttraction_ShouldReturnOk() {
    ResponseEntity<String> response = attractionController.createAttraction(attractionDTO);

    verify(attractionService).saveAttraction(attractionDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ATTRACTION_ADDED, response.getBody());
  }

  /**
   * Тест для метода createAttraction. Проверяет, что возвращается статус Service Unavailable при
   * возникновении исключения.
   */
  @Test
  void createAttraction_ShouldReturnServiceUnavailable_OnException() {
    doThrow(new RuntimeException()).when(attractionService)
        .saveAttraction(any(AttractionDTO.class));

    ResponseEntity<String> response = attractionController.createAttraction(attractionDTO);

    verify(attractionService).saveAttraction(attractionDTO);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertEquals(SERVICE_UNAVAILABLE, response.getBody());
  }

  /**
   * Тест для метода updateAttraction. Проверяет, что достопримечательность успешно обновляется.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void updateAttraction_ShouldReturnOk() throws NotFoundException {
    ResponseEntity<String> response = attractionController.updateAttraction(attractionDTO);

    verify(attractionService).updateAttraction(attractionDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ATTRACTION_UPDATED, response.getBody());
  }

  /**
   * Тест для метода updateAttraction. Проверяет, что возвращается статус Not Found, если
   * достопримечательность не найдена.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void updateAttraction_ShouldReturnNotFound() throws NotFoundException {
    doThrow(new NotFoundException()).when(attractionService)
        .updateAttraction(any(AttractionDTO.class));

    ResponseEntity<String> response = attractionController.updateAttraction(attractionDTO);

    verify(attractionService).updateAttraction(attractionDTO);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(ATTRACTION_NOT_FOUND + attractionDTO.getId(), response.getBody());
  }

  /**
   * Тест для метода updateAttraction. Проверяет, что возвращается статус Service Unavailable при
   * возникновении исключения.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void updateAttraction_ShouldReturnServiceUnavailable_OnException()
      throws NotFoundException {
    doThrow(new RuntimeException()).when(attractionService)
        .updateAttraction(any(AttractionDTO.class));

    ResponseEntity<String> response = attractionController.updateAttraction(attractionDTO);

    verify(attractionService).updateAttraction(attractionDTO);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertEquals(SERVICE_UNAVAILABLE, response.getBody());
  }

  /**
   * Тест для метода deleteAttraction. Проверяет, что достопримечательность успешно удаляется.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void deleteAttraction_ShouldReturnOk() throws NotFoundException {
    ResponseEntity<String> response = attractionController.deleteAttraction(1L);

    verify(attractionService).deleteAttraction(1L);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(ATTRACTION_DELETED, response.getBody());
  }

  /**
   * Тест для метода deleteAttraction. Проверяет, что возвращается статус Not Found, если
   * достопримечательность не найдена.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void deleteAttraction_ShouldReturnNotFound() throws NotFoundException {
    doThrow(new NotFoundException()).when(attractionService).deleteAttraction(1L);

    ResponseEntity<String> response = attractionController.deleteAttraction(1L);

    verify(attractionService).deleteAttraction(1L);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(ATTRACTION_NOT_FOUND + 1L, response.getBody());
  }

  /**
   * Тест для метода deleteAttraction. Проверяет, что возвращается статус Service Unavailable при
   * возникновении исключения.
   *
   * @throws NotFoundException если аттракция не найдена
   */
  @Test
  void deleteAttraction_ShouldReturnServiceUnavailable_OnException()
      throws NotFoundException {
    doThrow(new RuntimeException()).when(attractionService).deleteAttraction(1L);

    ResponseEntity<String> response = attractionController.deleteAttraction(1L);

    verify(attractionService).deleteAttraction(1L);
    assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
    assertEquals(SERVICE_UNAVAILABLE, response.getBody());
  }
}