package mentor1.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class MenuNavigator {
    private static final String BACK = "BACK";
    private static final String EXIT = "EXIT";

    private final DisplayReadWriter displayReadWriter;
    private final Deque<Menu> menuHistory = new ArrayDeque<>();
    private boolean isNeedContinue = true;

    public MenuNavigator(DisplayReadWriter displayReadWriter) {
        this.displayReadWriter = displayReadWriter;
    }

    public void run(Menu rootMenu) {
        menuHistory.push(rootMenu);

        while (isNeedContinue) {
            try {
                Menu currentMenu = menuHistory.peek();

                displayReadWriter.write(List.of(currentMenu.getInfo()));
                displayReadWriter.write(List.of(currentMenu.getCommands()));

                String command = displayReadWriter.writeAndRead(List.of("Введите команду (1, 2...)"));
                String result = currentMenu.execute(command);

                if (BACK.equals(result)) {
                    goBack();
                } else if (EXIT.equals(result)) {
                    isNeedContinue = false;
                    displayReadWriter.write(List.of("Программа завершена!"));
                } else if (!result.isEmpty()) {
                    displayReadWriter.write(List.of(result));
                }
            } catch (RuntimeException e) {
                String reason = e.getMessage() != null
                        ? e.getMessage()
                        : e.getClass().getSimpleName();
                displayReadWriter.write(List.of("Ошибка: " + reason));
            }
        }
    }

    public void open(Menu menu) {
        menuHistory.push(menu);
    }

    private void goBack() {
        if (menuHistory.size() > 1) {
            menuHistory.pop();
        }
    }
}
