package kirilloffna.homework3.service;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServiceLogMessages {

  public static final String ATT_SERVICE_GET_BY_ID = "AttractionServiceImpl. Запрос на получение"
      + " достопримечательности с id: {}";
  public static final String ATT_SERVICE_WITH_ID_NOT_FOUND = "AttractionServiceImpl. "
      + "Достопримечательность с id {} не найдена";
  public static final String ATT_SERVICE_GET_BY_CITY_ID = "AttractionServiceImpl. Запрос "
      + "на получение достопримечательностей для города с id: {}";
  public static final String ATT_SERVICE_FOR_CITY_WITH_ID_NOT_FOUND = "AttractionServiceImpl."
      + " Достопримечательности для города с id {} не найдены";
  public static final String ATT_SERVICE_GET_ALL_WITH_SORT_AND_FILTER = "AttractionServiceImpl. "
      + "Запрос на получение всех достопримечательностей с сортировкой по имени: {} и "
      + "фильтрацией по типу: {}";
  public static final String ATT_SERVICE_GET_ALL_WITH_FILTER = "AttractionServiceImpl. "
      + "Фильтрация достопримечательностей по типу: {}";
  public static final String ATT_SERVICE_GET_ALL_WITH_SORT = "AttractionServiceImpl."
      + " Сортировка достопримечательностей по имени";
  public static final String ATT_SERVICE_GET_ALL = "AttractionServiceImpl. Запрос"
      + " на получение всех достопримечательностей";
  public static final String ATT_SERVICE_SAVE_ATTRACTION = "AttractionServiceImpl. Сохранение "
      + "достопримечательности: {}";
  public static final String ATT_SERVICE_SAVE_ATTRACTION_DONE = "AttractionServiceImpl. "
      + "Достопримечательность успешно сохранена: {}";
  public static final String ATT_SERVICE_UPDATE_ATTRACTION = "AttractionServiceImpl. Обновление"
      + " достопримечательности с id: {}";
  public static final String ATT_SERVICE_UPDATE_ATTRACTION_DONE = "AttractionServiceImpl. "
      + "Достопримечательность успешно обновлена: {}";
  public static final String ATT_SERVICE_DELETE_ATTRACTION = "AttractionServiceImpl. Удаление "
      + "достопримечательности с id: {}";
  public static final String ATT_SERVICE_DELETE_ATTRACTION_DONE = "AttractionServiceImpl."
      + " Достопримечательность успешно удалена: {}";
  public static final String ATT_SERVICE_ATTRACTION_NOT_FOUND = "AttractionServiceImpl. "
      + "Достопримечательность с id {} не найдена";

  public static final String CITY_SERVICE_SAVE_CITY = "CityServiceImpl. Сохранение города: {}";
  public static final String CITY_SERVICE_SAVE_CITY_DONE = "CityServiceImpl.Город"
      + " успешно сохранен: {}";
  public static final String CITY_SERVICE_UPDATE_CITY = "CityServiceImpl. Обновление города: {}";
  public static final String CITY_SERVICE_UPDATE_CITY_DONE = "CityServiceImpl. Город успешно"
      + " обновлен";
  public static final String CITY_SERVICE_CITY_NOT_FOUND = "CityServiceImpl. Город с id {}"
      + " не найден";
}
