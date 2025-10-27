package model.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.domain.CarName;
import model.domain.Distance;
import model.domain.RacingCar;
import model.domain.RacingCars;
import model.domain.UserInputString;

public class NameExtractorService {
    private static final int ZERO = 0;
    private final UserInputString userInputString;

    public NameExtractorService(UserInputString userInputString) {
        this.userInputString = userInputString;
    }

    public RacingCars extractStringReturnRacingCars() {
        Iterator<String> names = userInputString.getNameIterator();
        List<RacingCar> racingCars = new ArrayList<>();
        while (names.hasNext()) {
            String name = names.next();
            racingCars.add(new RacingCar(new CarName(name), new Distance(ZERO)));
        }
        return new RacingCars(racingCars);
    }
}