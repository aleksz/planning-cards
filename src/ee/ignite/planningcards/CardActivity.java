package ee.ignite.planningcards;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CardActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		setContentView(getViewPager());
	}

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(getViewPager());
    }

    private ViewPager getViewPager() {
        List<View> pages = new ArrayList<View>();
        int selectedCardIndex = 0;
        String[] allCardValues = getResources().getStringArray(
                CardsPreferenceHelper.getCardsDeckValues(this));

        for (int i = 0; i < allCardValues.length; i++) {

            String cardValue = allCardValues[i];

            if (cardValue.equals(getIntent().getStringExtra("value"))) {
                selectedCardIndex = i;
            }

            pages.add(buildOneCard(cardValue, LayoutInflater.from(this)));
        }

        ViewPager viewPager = new ViewPager(this);
        viewPager.setAdapter(new CardPagerAdapter(pages));
        viewPager.setCurrentItem(selectedCardIndex);
        return viewPager;
    }

    private View buildOneCard(String value, LayoutInflater inflater) {
		View cardLayout = inflater.inflate(R.layout.card, null);
		TextView valueView = (TextView) cardLayout.findViewById(R.id.card_value);

		if ("C".equalsIgnoreCase(value)) {
			ImageView imgView = (ImageView) cardLayout.findViewById(R.id.card_img);
			imgView.setImageResource(R.drawable.cup);
			imgView.setVisibility(View.VISIBLE);
		} else {
			valueView.setText(value);
			valueView.setVisibility(View.VISIBLE);
		}

		return cardLayout;
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fibo:
                PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("shirtSizes", false).commit();
                setContentView(getViewPager());
                return true;
            case R.id.menu_t_shirt:
                PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("shirtSizes", true).commit();
                setContentView(getViewPager());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
