package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Optional;

import java.util.List;

public interface OptionalService {

    List<Optional> findAllByCarId(Integer _carId);
}
