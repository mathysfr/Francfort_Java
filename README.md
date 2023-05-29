Pour mettre en place les rafles dans le code, j’ai réalisé les étapes suivantes :

1. J’ai ajouté une méthode dans la classe `PawnModel` pour vérifier si une pièce peut effectuer une rafle


public boolean canCaptureAgain() {
    List<Coord> targetCoords = getTargetCoordsInMultiJumpCase();
    return !targetCoords.isEmpty();
}


Cette méthode utilise la méthode `getTargetCoordsInMultiJumpCase()` pour obtenir une liste de coordonnées cibles où la pièce peut capturer. Si la liste n'est pas vide, cela signifie qu'il est possible d'effectuer une rafle.

2. J’ai modifié la méthode `isMoveOk` pour prendre en compte les rafles. Je dois vérifier si une rafle est possible avant d'autoriser le déplacement. 


@Override
public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
    boolean ret = false;

    int colDistance = targetCoord.getColonne() - this.getColonne();
    int ligDistance = targetCoord.getLigne() - this.getLigne();
    int deltaLig = (int) Math.signum(ligDistance);

    // Cas d'un déplacement en diagonale
    if (Math.abs(colDistance) == Math.abs(ligDistance)) {

        // sans prise
        if (!isPieceToCapture) {
            if (deltaLig == this.direction && Math.abs(colDistance) == 1) {
                ret = true;
            }
        }
        // avec prise
        else {
            if (Math.abs(colDistance) == 2) {
                ret = true;
            }
        }
    }

    // Vérifier si une rafle est possible
    if (ret && isPieceToCapture) {
        List<Coord> targetCoords = getTargetCoordsInMultiJumpCase();
        ret = targetCoords.contains(targetCoord);
    }

    return ret;
}


Dans cette version modifiée de `isMoveOk`, Je vérifie si `isPieceToCapture` est `true` (ce qui signifie qu'une capture est en cours) et si la coordonnée cible se trouve dans la liste des coordonnées cibles pour les rafles. Si c'est le cas, J’autorise le déplacement.

J’ai suivi les mêmes étapes pour la classe QueenModel.




Pour afficher les scores et gérer la fin de partie dans votre jeu de pions, j’ai suivi les étapes suivantes :

1. J’ai ajouté une variable de score dans une nouvelle classe, GameModel :

private int scorePlayer1;
private int scorePlayer2;
```

Ces variables peuvent être initialisées à 0 au début du jeu.

2. Je met à jour les scores chaque fois qu'une capture est effectuée dans la fonction moveCapturePromote de la classe Model :

// si le placement est le gal (en diagonale selon algo pion ou dame)
				booléen isPieceToCapture = toCapturePieceCoord != null;
				si (this.isMovePiecePossible(toMovePieceCoord, targetSquareCoord, isPieceToCapture)) {

					// d placement effectif de la pi ce
					ce.movePiece(toMovePieceCoord, targetSquareCoord);
					isMoveDone = true;

					// suppression effective de la pi ce prise 
					this.remove(toCapturePieceCoord);
					si (this.currentGamerColor == PieceSquareColor.BLACK) {
						gameModel.scorePlayer1++;
					} autre {
						gameModel.scorePlayer2++;
					}
					gameModel.displayScores();
					if (gameModel.isGameOver() == true){
						gameModel.displayWinner();
					}
					



3. J’ai ajouté dans GameModel une méthode pour afficher les scores :


public void displayScores() {
    System.out.println("Scores:");
    System.out.println("Player 1: " + scorePlayer1);
    System.out.println("Player 2: " + scorePlayer2);
}


4. je gère la fin de partie en vérifiant si un joueur a atteint un score maximal. Pour cela j’ai ajouté une méthode dans votre classe de jeu principal pour vérifier si la partie est terminée :


public boolean isGameOver() {
    // Définissez ici le score maximal pour la victoire
    int maxScore = 10;
    
    return scorePlayer1 >= maxScore || scorePlayer2 >= maxScore;
}

Cette méthode retourne `true` si l'un des joueurs a atteint le score maximal défini, indiquant ainsi que la partie est terminée.

