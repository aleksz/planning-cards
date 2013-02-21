package ee.ignite.planningcards;

import static java.util.Collections.singletonMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CardsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cards);
        configureCards();
	}

	private void configureCards() {
		GridView cardGrid = (GridView) findViewById(R.id.card_grid);

        List<Map<String, String>> gridData = new ArrayList<Map<String,String>>();

        for (String cardValue : getResources().getStringArray(R.array.fibo)) {
        	gridData.add(singletonMap("value", cardValue));
        }

		cardGrid.setAdapter(new SimpleAdapter(
				this,
				gridData,
				R.layout.card_icon,
				new String[] { "value" },
				new int[] { R.id.card_value }));

		cardGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				TextView valueView = (TextView) v.findViewById(R.id.card_value);
				Intent intent = new Intent(CardsActivity.this, CardActivity.class);
				intent.putExtra("value", valueView.getText());
				startActivity(intent);
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
