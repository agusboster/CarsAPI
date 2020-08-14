package com.exercise.layers.ServiceLayer;

import com.exercise.layers.Entities.Optional;
import com.exercise.layers.RepositoryLayer.OptionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("optionalService")
@Transactional
public class OptionalServiceImpl implements OptionalService {
    OptionalRepository optionalRepo;

    @Autowired
    public OptionalServiceImpl(OptionalRepository _optionalRepo){
        this.optionalRepo = _optionalRepo;
    }

    //No lo hago "Optional (java.lang)" porque me simplifica a la hora de agregarlo en los cars, sumado a que la palabra optional repetirla tantas veces podria confundir.
    public List<Optional> findAllByCarId(Integer _carId){
        List<Optional> optionals = new ArrayList<>();
        Iterable<Optional> iterableOptionals = optionalRepo.findAllByCarId(_carId);
        iterableOptionals.forEach(optional -> optionals.add(optional));
        return optionals;
    }
}
