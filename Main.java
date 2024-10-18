import com.example.complexmatrix.ComplexMatrix;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true; // Флаг для работы цикла

        while (running) {
            try {
                System.out.println("\nВыберите операцию:");
                System.out.println("1 - Сложение матриц");
                System.out.println("2 - Вычитание матриц");
                System.out.println("3 - Умножение матриц");
                System.out.println("4 - Транспонирование матрицы");
                System.out.println("0 - Выйти");
                System.out.print("Ваш выбор: ");

                int choice = scanner.nextInt();  // Чтение выбора пользователя

                switch (choice) {
                    case 1:
                        // Сложение
                        System.out.println("Ввод первой матрицы для сложения:");
                        ComplexMatrix matrix1 = ComplexMatrix.inputMatrixFromUser();

                        System.out.println("Ввод второй матрицы для сложения:");
                        ComplexMatrix matrix2 = ComplexMatrix.inputMatrixFromUser();

                        try {
                            System.out.println("Результат сложения:");
                            ComplexMatrix sum = matrix1.add(matrix2);
                            sum.printMatrix();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;

                    case 2:
                        // Вычитание
                        System.out.println("Ввод первой матрицы для вычитания:");
                        matrix1 = ComplexMatrix.inputMatrixFromUser();

                        System.out.println("Ввод второй матрицы для вычитания:");
                        matrix2 = ComplexMatrix.inputMatrixFromUser();

                        try {
                            System.out.println("Результат вычитания:");
                            ComplexMatrix difference = matrix1.subtract(matrix2);
                            difference.printMatrix();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Умножение
                        System.out.println("Ввод первой матрицы для умножения:");
                        matrix1 = ComplexMatrix.inputMatrixFromUser();

                        System.out.println("Ввод второй матрицы для умножения:");
                        matrix2 = ComplexMatrix.inputMatrixFromUser();

                        try {
                            System.out.println("Результат умножения:");
                            ComplexMatrix product = matrix1.multiply(matrix2);
                            product.printMatrix();
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: " + e.getMessage());
                        }
                        break;

                    case 4:
                        // Транспонирование
                        System.out.println("Ввод матрицы для транспонирования:");
                        matrix1 = ComplexMatrix.inputMatrixFromUser();

                        System.out.println("Результат транспонирования:");
                        ComplexMatrix transpose = matrix1.transpose();
                        transpose.printMatrix();
                        break;

                    case 0:
                        // Выход
                        running = false;
                        System.out.println("Завершение работы программы.");
                        break;

                    default:
                        System.out.println("Некорректный выбор. Пожалуйста, выберите правильную операцию.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Некорректный ввод. Пожалуйста, введите целое число для выбора операции.");
                scanner.nextLine(); // Очищаем ввод, чтобы предотвратить зацикливание
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
                scanner.nextLine(); // Очищаем ввод, чтобы предотвратить зацикливание
            }
        }

        scanner.close(); // Закрываем сканер после завершения работы
    }
}