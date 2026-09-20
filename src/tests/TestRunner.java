package tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        // JUnitCore — программный запуск тестов (то же, что org.junit.runner.JUnitCore в терминале)
        Result result = JUnitCore.runClasses(EquipmentRepositoryTest.class);

        // Итоговая сводка
        System.out.println("Всего тестов : " + result.getRunCount());
        System.out.println("Провалено    : " + result.getFailureCount());
        System.out.println("Успешно      : " + (result.getRunCount() - result.getFailureCount()));

        // Детали по упавшим, если есть
        for (Failure failure : result.getFailures()) {
            System.out.println("Сбой: " + failure.toString());
        }

        // Код возврата: 0 — успех, 1 — есть провалы (полезно для CI)
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}
