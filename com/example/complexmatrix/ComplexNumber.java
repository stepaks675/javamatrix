package com.example.complexmatrix;

public class ComplexNumber {
    private final double real;
    private final double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public static ComplexNumber parseComplexNumber(String input) {
        // Убираем все пробелы из строки
        input = input.replaceAll("\\s+", "");
        
        double real = 0;
        double imaginary = 0;

        // Проверяем на чисто мнимую часть (например: "i", "-i")
        if (input.equals("i")) {
            return new ComplexNumber(0, 1);
        } else if (input.equals("-i")) {
            return new ComplexNumber(0, -1);
        }

        // Проверяем, если нет мнимой части (например: "4")
        if (!input.contains("i")) {
            real = Double.parseDouble(input);
            return new ComplexNumber(real, 0);
        }

        // Мнимая часть присутствует. Нужно разобрать строку.
        String[] parts = input.split("(?=[+-])", 2); // Разделение строки по первому встреченному знаку + или -

        if (parts[0].contains("i")) {
            imaginary = parseImaginary(parts[0]);
        } else {
            real = Double.parseDouble(parts[0]);
        }

        if (parts.length > 1 && parts[1].contains("i")) {
            imaginary = parseImaginary(parts[1]);
        } else if (parts.length > 1) {
            real = Double.parseDouble(parts[1]);
        }

        return new ComplexNumber(real, imaginary);
    }

    // Метод для обработки мнимой части
    private static double parseImaginary(String part) {
        if (part.equals("i")) {
            return 1;
        } else if (part.equals("-i")) {
            return -1;
        } else {
            return Double.parseDouble(part.replace("i", ""));
        }
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double realPart = this.real * other.real - this.imaginary * other.imaginary;
        double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    @Override
    public String toString() {
        if (imaginary == 0) {
            return String.format("%.2f", real);
        } else if (real == 0) {
            return String.format("%.2fi", imaginary);
        } else {
            return String.format("%.2f %s %.2fi", real, (imaginary < 0 ? "-" : "+"), Math.abs(imaginary));
        }
    }
}

