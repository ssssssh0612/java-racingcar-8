package model.service;

import model.domain.RacingCars;
import model.domain.UserInputNumber;
import view.OutputView;

public class RacingCarsService {
    private final OutputView outputView;

    public RacingCarsService(OutputView outputView) {
        this.outputView = outputView;
    }

    public RacingCars moveAndLogSnapshots(UserInputNumber number, RacingCars cars){
        outputView.showOutput("");
        outputView.showOutput("실행 결과");
        int count = number.getCount();
        for (int i = 0; i < count; i++) {
            cars = cars.moveRacingCars();
            outputView.showOutput(cars);
        }
        return cars;
    }


}
