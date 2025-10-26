package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String FIRST_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String SECOND_MESSAGE = "시도할 횟수는 몇 회인가요?";
    public String getUserInputString() {
        System.out.println(FIRST_MESSAGE);
        return Console.readLine();
    }

    public String getUserInputNumber() {
        System.out.println(SECOND_MESSAGE);
        return Console.readLine();
    }
}
