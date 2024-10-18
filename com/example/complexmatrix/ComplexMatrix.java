package com.example.complexmatrix;

import java.util.Scanner;

public class ComplexMatrix {
    private ComplexNumber[][] matrix;
    private int rows, cols;

    public ComplexMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры матрицы должны быть положительными числами.");
        }
        this.rows = rows;
        this.cols = cols;
        this.matrix = new ComplexNumber[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.matrix[i][j] = new ComplexNumber(0, 0);
            }
        }
    }

    public void setElement(int row, int col, ComplexNumber value) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            throw new IndexOutOfBoundsException("Индексы строки и столбца находятся за пределами матрицы.");
        }
        this.matrix[row][col] = value;
    }

    public ComplexNumber getElement(int row, int col) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            throw new IndexOutOfBoundsException("Индексы строки и столбца находятся за пределами матрицы.");
        }
        return this.matrix[row][col];
    }

    public static ComplexMatrix inputMatrixFromUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        ComplexMatrix matrix = new ComplexMatrix(rows, cols);
        scanner.nextLine();  // Очищаем буфер

        System.out.println("Введите элементы матрицы в формате: a+bi");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.printf("Элемент [%d][%d]: ", i, j);
                String input = scanner.nextLine();
                try {
                    ComplexNumber complexNumber = ComplexNumber.parseComplexNumber(input);
                    matrix.setElement(i, j, complexNumber);
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Некорректный ввод комплексного числа.");
                    j--;  // Повторить ввод для этого элемента
                }
            }
        }
        return matrix;
    }

    public ComplexMatrix add(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера для сложения.");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.setElement(i, j, this.matrix[i][j].add(other.getElement(i, j)));
            }
        }
        return result;
    }

    public ComplexMatrix subtract(ComplexMatrix other) {
        if (this.rows != other.rows || this.cols != other.cols) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера для вычитания.");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, this.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.setElement(i, j, this.matrix[i][j].subtract(other.getElement(i, j)));
            }
        }
        return result;
    }

    public ComplexMatrix multiply(ComplexMatrix other) {
        if (this.cols != other.rows) {
            throw new IllegalArgumentException("Число столбцов первой матрицы должно быть равно числу строк второй матрицы.");
        }
        ComplexMatrix result = new ComplexMatrix(this.rows, other.cols);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                ComplexNumber sum = new ComplexNumber(0, 0);
                for (int k = 0; k < this.cols; k++) {
                    sum = sum.add(this.matrix[i][k].multiply(other.getElement(k, j)));
                }
                result.setElement(i, j, sum);
            }
        }
        return result;
    }

    public ComplexMatrix transpose() {
        ComplexMatrix result = new ComplexMatrix(this.cols, this.rows);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                result.setElement(j, i, this.matrix[i][j]);
            }
        }
        return result;
    }

    // Метод для вывода матрицы на экран
    public void printMatrix() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                System.out.print(this.matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
