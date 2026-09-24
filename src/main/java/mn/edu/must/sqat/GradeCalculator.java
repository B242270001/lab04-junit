package mn.edu.must.sqat;

public class GradeCalculator {

    /**
     * Оюутны нийлбэр онооноос үсгэн дүн тооцно.
     * 90+ -> A, 80-89.99 -> B, 70-79.99 -> C, 60-69.99 -> D, <60 -> F
     * score нь 0-100 хязгаараас гарвал IllegalArgumentException шиднэ.
     */
    public String letterGrade(double score) {
        if (score < 0.0 || score > 100.0) {
            throw new IllegalArgumentException("Оноо 0 ба 100-ийн хооронд байх ёстой: " + score);
        }
        if (score >= 90.0) { // score >= 90.0 -> score > 90.0 bolgoj mutant test hiiv
            return "A";
        } else if (score >= 80.0) {
            return "B";
        } else if (score >= 70.0) {
            return "C";
        } else if (score >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * Ирц(10), лаб+бие даалт(40), сорил1(10), сорил2(10), шалгалт(30)-ийн оноонуудаас нийлбэр тооцно.
     * Аль нэг нь сөрөг эсвэл дээд хязгаараасаа хэтэрсэн бол IllegalArgumentException шиднэ.
     */
    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        if (att < 0.0 || att > 10.0) {
            throw new IllegalArgumentException("Ирц 0-10 хооронд байх ёстой: " + att);
        }
        if (lab < 0.0 || lab > 40.0) {
            throw new IllegalArgumentException("Лаборатори ба бие даалт 0-40 хооронд байх ёстой: " + lab);
        }
        if (quiz1 < 0.0 || quiz1 > 10.0) {
            throw new IllegalArgumentException("Сорил 1 оноо 0-10 хооронд байх ёстой: " + quiz1);
        }
        if (quiz2 < 0.0 || quiz2 > 10.0) {
            throw new IllegalArgumentException("Сорил 2 оноо 0-10 хооронд байх ёстой: " + quiz2);
        }
        if (exam < 0.0 || exam > 30.0) {
            throw new IllegalArgumentException("Шалгалтын оноо 0-30 хооронд байх ёстой: " + exam);
        }
        return att + lab + quiz1 + quiz2 + exam;
    }
}