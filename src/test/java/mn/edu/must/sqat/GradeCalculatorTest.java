package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    @Test
    @DisplayName("90 оноо яг A дүн байх ёстой (хязгаарын тохиолдол)")
    void ninetyIsExactlyA() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        String grade = calc.letterGrade(90.0);
        // Assert
        assertEquals("A", grade);
    }

    @Test
    @DisplayName("89.99 оноо B дүн байх ёстой (хязгаарын тохиолдол)")
    void eightyNinePointNineNineIsB() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        String grade = calc.letterGrade(89.99);
        // Assert
        assertEquals("B", grade);
    }

    @Test
    @DisplayName("Сөрөг болон 100-аас дээш оноонд IllegalArgumentException шидэх ёстой")
    void invalidScoreThrowsException() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1.0));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101.0));
    }

    @ParameterizedTest
    @DisplayName("letterGrade-ийн хязгаар ба ердийн утгуудыг нэгтгэн шалгах")
    @CsvSource({
        "100.0, A",
        "95.0, A",
        "90.0, A",
        "89.99, B",
        "80.0, B",
        "79.99, C",
        "70.0, C",
        "69.99, D",
        "60.0, D",
        "59.99, F",
        "0.0, F"
    })
    void letterGradeBoundaries(double score, String expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        String actual = calc.letterGrade(score);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("totalScore зөв дээд утгуудаар 100.0 нийлбэр гарах ёстой")
    void totalScoreValidStandard() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        double result = calc.totalScore(10.0, 40.0, 10.0, 10.0, 30.0);
        // Assert
        assertEquals(100.0, result, 0.001);
    }

    @Test
    @DisplayName("totalScore ирц сөрөг утгатай (-5) үед exception шидэх ёстой")
    void totalScoreNegativeAttThrows() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(-5.0, 40.0, 10.0, 10.0, 30.0));
    }

    @Test
    @DisplayName("totalScore лаборатори хэтэрсэн утгатай (41) үед exception шидэх ёстой")
    void totalScoreExceededLabThrows() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(10.0, 41.0, 10.0, 10.0, 30.0));
    }

    @ParameterizedTest
    @DisplayName("totalScore зөв утгуудын нийлбэрийг шалгах")
    @CsvSource({
        "10.0, 40.0, 10.0, 10.0, 30.0, 100.0",
        "8.0,  35.0, 9.0,  8.5,  25.0, 85.5",
        "0.0,  0.0,  0.0,  0.0,  0.0,  0.0"
    })
    void totalScoreValidParameterized(double att, double lab, double quiz1, double quiz2, double exam, double expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        double actual = calc.totalScore(att, lab, quiz1, quiz2, exam);
        // Assert
        assertEquals(expected, actual, 0.001);
    }

    @ParameterizedTest
    @DisplayName("totalScore буруу/хязгаар хэтэрсэн утгуудад exception шидэхийг шалгах")
    @CsvSource({
        "-1.0, 40.0, 10.0, 10.0, 30.0",
        "11.0, 40.0, 10.0, 10.0, 30.0",
        "10.0, -1.0, 10.0, 10.0, 30.0",
        "10.0, 40.1, 10.0, 10.0, 30.0",
        "10.0, 40.0, -0.1, 10.0, 30.0",
        "10.0, 40.0, 10.1, 10.0, 30.0",
        "10.0, 40.0, 10.0, -0.5, 30.0",
        "10.0, 40.0, 10.0, 10.5, 30.0",
        "10.0, 40.0, 10.0, 10.0, -1.0",
        "10.0, 40.0, 10.0, 10.0, 30.1"
    })
    void totalScoreInvalidParameterized(double att, double lab, double quiz1, double quiz2, double exam) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(att, lab, quiz1, quiz2, exam));
    }
}