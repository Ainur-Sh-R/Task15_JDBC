package StudentsService.dao;

import StudentsService.pojo.City;

public interface CityDao {
    public City searchCityByName(String name);
    public City searchCityById(int id);
    public boolean addCity(City student);
}
