import java.util.Random;
import java.util.Scanner;

public class SubwayGame {
    private static final int TRACK_LENGTH = 20;
    private static final char PLAYER = 'P';
    private static final char OBSTACLE = 'X';
    private static final char EMPTY = '_';

    private char[] track;
    private int playerPosition;
    private int score;

    public SubwayGame() {
        track = new char[TRACK_LENGTH];
        playerPosition = TRACK_LENGTH / 2;
        score = 0;
        initializeTrack();
    }

    private void initializeTrack() {
        for (int i = 0; i < TRACK_LENGTH; i++) {
            track[i] = EMPTY;
        }
        track[playerPosition] = PLAYER;
    }

    private void movePlayer(int direction) {
        if (direction == -1 && playerPosition > 0) {
            track[playerPosition] = EMPTY;
            playerPosition--;
            track[playerPosition] = PLAYER;
        } else if (direction == 1 && playerPosition < TRACK_LENGTH - 1) {
            track[playerPosition] = EMPTY;
            playerPosition++;
            track[playerPosition] = PLAYER;
        }
    }

    private void generateObstacle() {
        Random random = new Random();
        int position = random.nextInt(TRACK_LENGTH);
        if (track[position] == EMPTY) {
            track[position] = OBSTACLE;
        }
    }

    private boolean checkCollision() {
        return track[playerPosition] == OBSTACLE;
    }

    private void updateScore() {
        score++;
    }

    private void displayTrack() {
        System.out.println(new String(track));
        System.out.println("Score: " + score);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;

        while (!gameOver) {
            displayTrack();
            System.out.print("Move left (a), right (d), or quit (q): ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "a":
                    movePlayer(-1);
                    break;
                case "d":
                    movePlayer(1);
                    break;
                case "q":
                    gameOver = true;
                    continue;
                default:
                    System.out.println("Invalid input. Try again.");
                    continue;
            }

            generateObstacle();
            if (checkCollision()) {
                gameOver = true;
                System.out.println("Game Over! Final score: " + score);
            } else {
                updateScore();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        SubwayGame game = new SubwayGame();
        game.play();
    }
}