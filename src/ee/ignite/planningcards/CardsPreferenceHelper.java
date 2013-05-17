package ee.ignite.planningcards;

import android.content.Context;
import android.preference.PreferenceManager;

public class CardsPreferenceHelper {

	public static int getCardsDeckValues(Context context) {
		if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean("shirtSizes", false)) {
			return R.array.tShirt;
		} else {
			return R.array.fibo;
		}
	}
}
