package model.service;

import model.domain.RacingCars;
import model.domain.UserInputNumber;
import view.OutputView;

public class RacingCarsService {
    private final OutputView outputView;
    private final UserInputNumber number;

    public RacingCarsService(UserInputNumber number, OutputView outputView) {
        this.outputView = outputView;
        this.number = number;
    }

    public RacingCars moveAndLogSnapshots(RacingCars cars) {
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
