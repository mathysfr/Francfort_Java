package atelier3.model;

public class GameModel {
    int scorePlayer1;
    int scorePlayer2;

    public GameModel() {
        scorePlayer1 = 0;
        scorePlayer2 = 0;
    }

    public void displayScores() {
        System.out.println("Scores:");
        System.out.println("Player 1: " + scorePlayer1);
        System.out.println("Player 2: " + scorePlayer2);
    }
    
    public boolean isGameOver() {
        // Définissez ici le score maximal pour la victoire
        int maxScore = 20;
        
        return scorePlayer1 >= maxScore || scorePlayer2 >= maxScore;
    }

    public void displayWinner() {
        if (scorePlayer1 > scorePlayer2) {
            System.out.println("Fin de la partie ! Les noirs ont gagnés !");
        } else if (scorePlayer2 > scorePlayer1) {
            System.out.println("Fin de la partie ! Les blancs ont gagnés !");
        } 
    }
    
}
