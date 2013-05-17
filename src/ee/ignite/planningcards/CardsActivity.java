package ee.ignite.planningcards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CardsActivity extends Activity {

	private class CardIconAdapter extends SimpleAdapter {

		public CardIconAdapter(Context context,
				List<? extends Map<String, ?>> data, int resource,
				String[] from, int[] to) {
			super(context, data, resource, from, to);
		}

		@Override
		public void setViewText(TextView v, String text) {
			super.setViewText(v, text);
			if (!"C".equalsIgnoreCase(text)) {
				v.setVisibility(View.VISIBLE);
			}
		}

		@Override
		public void setViewImage(ImageView v, int value) {
			super.setViewImage(v, value);
			v.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cards);
		configureCards();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		configureCards();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, CardsPreferenceActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void configureCards() {
		GridView cardGrid = (GridView) findViewById(R.id.card_grid);
		List<Map<String, Object>> gridData = new ArrayList<Map<String, Object>>();

		for (String cardValue : getResources().getStringArray(CardsPreferenceHelper.getCardsDeckValues(this))) {

			Map<String, Object> singletonMap = new HashMap<String, Object>();

			if ("C".equals(cardValue)) {
				singletonMap.put("img", R.drawable.cup);
			}

			singletonMap.put("value", cardValue);

			gridData.add(singletonMap);
		}

		cardGrid.setAdapter(new CardIconAdapter(this, gridData,
				R.layout.card_icon, new String[] { "value", "img" }, new int[] {
						R.id.card_value, R.id.card_img }));

		cardGrid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				TextView valueView = (TextView) v.findViewById(R.id.card_value);
				Intent intent = new Intent(CardsActivity.this,
						CardActivity.class);
				intent.putExtra("value", valueView.getText());
				startActivity(intent);
			}

		});
	}
}
