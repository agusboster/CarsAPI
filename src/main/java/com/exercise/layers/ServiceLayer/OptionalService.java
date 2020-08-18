package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Optional;
import com.exercise.layers.Entities.Stat;

import java.util.List;

public interface OptionalService {

    List<Optional> findAllByCarId(Integer _carId);
    List<Optional> getAllOptionals();
    void updateOrSaveCarOptionals(Integer _carId, List<Optional> _carOptionals);
    void updateOrSaveOptionalByCar(Integer _carId, Optional _carOptional);
    Float getOptionalsPrice(List<Optional> optionals);
    List<Stat> getOptionalsStats();
}
