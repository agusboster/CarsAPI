package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Optional.Optional;
import com.exercise.layers.Entities.Stat.Stat;

import java.util.List;

public interface OptionalService {

    List<Optional> findAllByCarId(Integer _carId);
    List<Optional> findAllByName(String _name);
    List<Optional> getAllOptionals();
    void updateOrSaveCarOptionals(Integer _carId, List<Optional> _carOptionals);
    void updateOrSaveOptionalByCar(Integer _carId, Optional _carOptional);
    Float getOptionalsPrice(List<Optional> optionals);
    List<Stat> getOptionalsStats();
    void getOptionalStats(String _optionalName, List<Stat> stats, int totalOptionals);
    void deleteOptional(Integer optionalId);
    void deleteCarOptionals(Integer carId);
}
