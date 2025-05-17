import java.util.*;

class Question {
    String question;
    String[] options;
    int correctOption; // 0-based index

    Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }

    void display() {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}

public class QuizGame {
    static List<Question> questions = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int score = 0;

    public static void main(String[] args) {
        loadQuestions();
        System.out.println("=== Welcome to the Confidence Quiz Game ===");

        for (Question q : questions) {
            q.display();
            System.out.print("Enter your answer (1-4): ");
            int answer = sc.nextInt() - 1;
            sc.nextLine(); // consume newline

            System.out.print("Rate your confidence (High/Medium/Low): ");
            String confidence = sc.nextLine().toLowerCase();

            int points = calculateScore(answer, q.correctOption, confidence);
            score += points;

            if (points > 0) {
                System.out.println("Correct! You earned " + points + " points.");
            } else {
                System.out.println("Wrong! You lost " + Math.abs(points) + " points.");
            }
        }

        System.out.println("\n=== Quiz Over! Your total score: " + score + " ===");
    }

    static int calculateScore(int answer, int correct, String confidence) {
        boolean isCorrect = answer == correct;
        return switch (confidence) {
            case "high" -> isCorrect ? 3 : -2;
            case "medium" -> isCorrect ? 2 : 0;
            case "low" -> isCorrect ? 1 : 0;
            default -> 0;
        };
    }

    static void loadQuestions() {
        questions.add(new Question(
                "Which language is used for Android app development?",
                new String[]{"Python", "Java", "Swift", "C#"}, 1));

        questions.add(new Question(
                "What planet is known as the Red Planet?",
                new String[]{"Earth", "Venus", "Mars", "Jupiter"}, 2));

        questions.add(new Question(
                "Who wrote 'Romeo and Juliet'?",
                new String[]{"Shakespeare", "Tolstoy", "Hemingway", "Poe"}, 0));

        questions.add(new Question(
                "Which element has the chemical symbol 'O'?",
                new String[]{"Gold", "Oxygen", "Silver", "Iron"}, 1));
    }
}