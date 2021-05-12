/**
 * @author les membres de l'equipe
 * @version 1.0
 */

public class Kuplet {
    private int score = 0;
    private Case[] tableau;
    private int k;

    /**
     * constructeur d'un Kuplet
     * @param n la longueur d'un Kuplet
     */
    public Kuplet(int n) {
        this.tableau = new Case[n];
        this.k = n;
    }

    /**
     * methode d'evaluation d'un Kuplet
     * @return le score du Kuplet
     */
    public int evaluate() {
        if (this.evaluateX() == 0 && this.evaluateO() == 1) {
            if (this.k <= 5) {
                this.score = 7;
            } else {
                this.score = 1;
            }
        } else if (this.evaluateX() == 0) {
            this.score = this.evaluateO();
        } else if (this.evaluateO() == 1) {
            this.score = this.evaluateX();
        } else {
            this.score = 0;
        }

        return this.score;
    }

    /**
     * calcule le score des Kuplets contenant X
     * @return le score du Kuplet
     */
    public int evaluateX() {
        int scoreX = 0;
        for(int i = 0; i < this.k; ++i) {
            if (this.tableau[i].getEtat() == 'X') {
                scoreX += 2;
            }
        }
        if (this.k <= 5) {
            switch(scoreX) {
            case 2:
                scoreX = 15;
            case 3:
            case 5:
            case 7:
            case 9:
            default:
                break;
            case 4:
                scoreX = 400;
                break;
            case 6:
                scoreX = 1800;
                break;
            case 8:
                scoreX = 100000;
                break;
            case 10:
                scoreX = 800001;
            }
        }
        return scoreX;
    }

    /**
     * methode de calcul d'un Kuplet contenant des O
     * @return le score du Kuplet
     */
    public int evaluateO() {
        int scoreO = 1;
        for(int i = 0; i < this.k; ++i) {
            if (this.tableau[i].getEtat() == 'O') {
                scoreO += 2;
            }
        }

        if (this.k <= 5) {
            switch(scoreO) {
            case 3:
                scoreO = 35;
            case 4:
            case 6:
            case 8:
            case 10:
            default:
                break;
            case 5:
                scoreO = 800;
                break;
            case 7:
                scoreO = 15000;
                break;
            case 9:
                scoreO = 800000;
                break;
            case 11:
                scoreO = 800002;
            }
        }

        return scoreO;
    }

    /**
     * getter 
     * @return le score du Kuplet
     */
    public int getScore() {
        return this.score;
    }

    /**
     * setter
     * @param le nouveau score du Kuplet
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * getter 
     * @return le tableau des Cases du Kuplet
     */
    public Case[] getTableau() {
        return this.tableau;
    }
    /**
     * setter
     * @param le nouveau tableau de Kuplets
     */
    public void setTableau(Case[] tableau) {
        this.tableau = tableau;
    }
}
