package ooo.stefano.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = doge, 1 = gatto
    int activePlayer = 0;

    // stato 2 = non giocato
    int gameState[] = {2,2,2,2,2,2,2,2,2};

    // gioco attivo
    boolean gameIsActive = true;

    int winningPositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        int tappedCell = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCell] == 2 && gameIsActive) {

            gameState[tappedCell] = activePlayer;

            counter.setTranslationY(-1000);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.dogeface);
                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.cat);
                activePlayer = 0;

            }

            counter.animate().translationYBy(1000f).setDuration(300);

            // ciclo for per vedere se ci sono pattern di vincita
            for (int[] winningPosition : winningPositions) {
                // controllo se tutti e tre sono uguali + controllo che non sia "2" che e' lo stato unplayed
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[2]] != 2) {

                    // quando qualcuno ha vinto, rendo visibile messaggio e pulsante, blocco il gioco intanto
                    gameIsActive = false;

                    // imposto il nome del vincitore
                    String winnerName = "";

                    if(gameState[winningPosition[0]] == 0) {
                        winnerName = "Doge";
                    } else if (gameState[winningPosition[0]] == 1) {
                        winnerName = "Cat";
                    }
                    TextView winText = (TextView) findViewById(R.id.winText);

                    winText.setText(winnerName + " wins!");
                    // e ora imposto il layout come visibile
                    LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                    winnerLayout.setVisibility(View.VISIBLE);

                } else {
                    // controllo se e' pari

                    boolean gameOver = true;

                    for (int counterState : gameState) {
                        if (counterState == 2) gameOver = false;
                    }

                    // se e' pari lo mostro
                    if (gameOver) {

                        TextView winText = (TextView) findViewById(R.id.winText);

                        winText.setText("It's a draw!");
                        // e ora imposto il layout come visibile
                        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
                        winnerLayout.setVisibility(View.VISIBLE);

                    }

                }

            }

        }


    }

    public void playAgain(View view) {
        // resetto tutto
        // nascondo il winnerLayout
        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);

        // resetto i player e il gioco
        activePlayer = 0;
        gameIsActive = true;

        // resetto il gameState (devo fare un ciclo for per non dichiarare un nuovo array
        for (int i=0; i < gameState.length; i++ ) {
            gameState[i] = 2;
        }

        // nascondo cani e gatti usando dinamicamente il numero di elementi nel gridLayout
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
