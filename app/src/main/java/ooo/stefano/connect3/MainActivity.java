package ooo.stefano.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 0 = doge, 1 = gatto
    int activePlayer = 0;

    // stato 2 = non giocato
    int gameState[] = {2,2,2,2,2,2,2,2,2};

    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        counter.setTranslationY(-1000);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.dogeface);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.cat);
            activePlayer = 0;
        }

        counter.animate().translationYBy(1000f).setDuration(300);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
