package racingcar;

import model.domain.RacingCars;
import model.domain.UserInputNumber;
import model.domain.UserInputString;
import model.domain.Winners;
import model.service.NameExtractorService;
import model.service.RacingCarsService;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        UserInputString userInputString = new UserInputString(inputView.getUserInputString());
        NameExtractorService nameExtractorService = new NameExtractorService();
        RacingCars racingCars = nameExtractorService.extractStringReturnRacingCars(userInputString);
        OutputView outputView = new OutputView();
        RacingCarsService racingCarsService = new RacingCarsService(outputView);
        UserInputNumber userInputNumber = new UserInputNumber(inputView.getUserInputNumber());
        RacingCars movedRacingCars = racingCarsService.moveAndLogSnapshots(userInputNumber, racingCars);
        Winners winnerRacingCars = movedRacingCars.findWinners();
        outputView.showOutput(winnerRacingCars.toResult());
    }
}
