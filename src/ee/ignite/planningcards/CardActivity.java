package ee.ignite.planningcards;

import static java.util.Collections.singletonMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class CardActivity extends Activity {

	private static String[] CARDS = new String[] { "1", "2", "3", "5", "8",
			"13", "?", "C", "~" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cards);
        configureCards();
	}

	private void configureCards() {
		GridView cardGrid = (GridView) findViewById(R.id.card_grid);

        List<Map<String, String>> gridData = new ArrayList<Map<String,String>>();

        for (String cardValue : CARDS) {
        	gridData.add(singletonMap("value", cardValue));
        }

		cardGrid.setAdapter(new SimpleAdapter(
				this,
				gridData,
				R.layout.card,
				new String[] { "value" },
				new int[] { R.id.card_value }));

		final Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.choose_card);
		cardGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				v.startAnimation(hyperspaceJump);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.card, menu);
		return true;
	}

}
