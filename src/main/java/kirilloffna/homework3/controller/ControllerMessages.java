package kirilloffna.homework3.controller;

import lombok.experimental.UtilityClass;

/**
 * Утилитарный класс для формирования сообщений и логов Контроллеров.
 */
@UtilityClass
public class ControllerMessages {

  public static final String CITY_ADDED = "City added successfully";
  public static final String CITY_UPDATED = "City updated successfully";
  public static final String CITY_NOT_FOUND = "City not found for this id : ";
  public static final String ATTRACTION_ADDED = "Attraction added successfully";
  public static final String ATTRACTION_UPDATED = "Attraction updated successfully";
  public static final String ATTRACTION_DELETED = "Attraction deleted successfully";
  public static final String ATTRACTION_NOT_FOUND = "Attraction not found for this id : ";
  public static final String ATTRACTION_WITH_CITY_ID_NOT_FOUND = "Достопримечательность с id {} "
      + "не найдена";
  public static final String SERVICE_UNAVAILABLE = "Service is currently unavailable. Please try"
      + " again later.";

  public static final String CITY_CONT_SAVE_CITY = "CityControllerImpl. Запрос на создание"
      + " города: {}";
  public static final String CITY_CONT_SAVE_ERROR = "CityControllerImpl. Ошибка при создании города";
  public static final String CITY_CONT_UPDATE_CITY = "CityControllerImpl. Запрос на обновление"
      + " города с id: {}";
  public static final String CITY_CONT_UPDATE_ERROR = "CityControllerImpl. Ошибка при обновлении "
      + "города с id {}";
  public static final String CITY_CONT_CITY_NOT_FOUND = "CityControllerImpl.Город с id {} не найден";
  public static final String ATT_CONT_GET_ATTRACTION_BY_ID = "Запрос на получение"
      + " достопримечательности с id: {}";
  public static final String ATT_CONT_GET_ATTRACTION_BY_ID_ERROR = "Ошибка при получении "
      + "достопримечательности с id {}";
  public static final String ATT_CONT_GET_ATTRACTION_SORT_BY_NAME = "Запрос на получение списка"
      + " достопримечательностей с сортировкой по имени: {} и фильтрацией по типу: {}";
  public static final String ATT_CONT_GET_ATTRACTION_SORT_BY_NAME_ERROR = "Ошибка при получении "
      + "списка достопримечательностей";
  public static final String ATT_CONT_GET_ATTRACTION_FOR_CITY_ID = "Запрос на получение"
      + " достопримечательностей для города с id: {}";
  public static final String ATT_CONT_GET_ATTRACTION_FOR_CITY_ID_NOT_FOUND = "Достопримечательности"
      + " для города с id {} не найдены";
  public static final String ATT_CONT_GET_ATTRACTION_FOR_CITY_ID_ERROR = "Ошибка при получении "
      + "достопримечательностей для города с id {}";
  public static final String ATT_CONT_SAVE_ATTRACTION = "Запрос на создание "
      + "достопримечательности: {}";
  public static final String ATT_CONT_SAVE_ATTRACTION_ERROR = "Ошибка при создании "
      + "достопримечательности";
  public static final String ATT_CONT_UPDATE_ATTRACTION = "Запрос на обновление"
      + " достопримечательности с id: {}";
  public static final String ATT_CONT_UPDATE_ATTRACTION_ERROR = "Ошибка при обновлении"
      + " достопримечательности с id {}";
  public static final String ATT_CONT_DELETE_ATTRACTION =
      "Запрос на удаление достопримечательности "
          + "с id: {}";
  public static final String ATT_CONT_DELETE_ATTRACTION_ERROR = "Ошибка при удалении "
      + "достопримечательности с id {}";
}
