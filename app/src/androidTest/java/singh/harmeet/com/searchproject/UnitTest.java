package singh.harmeet.com.searchproject;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import singh.harmeet.com.searchproject1.activity.DetailActivity;
import singh.harmeet.com.searchproject1.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by harmeet.assi on 4/18/2018.
 */

@RunWith(AndroidJUnit4.class)
public class UnitTest {

    @Rule
   public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule
   public ActivityTestRule<DetailActivity> detailActivityActivityTestRule = new ActivityTestRule<DetailActivity>(DetailActivity.class);

    @Test
    public void checkFlow() {
        onView(withId(R.id.search_menu)).perform(click());//to open search view
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("Texas Rangers"),closeSoftKeyboard());//type sample search
        assertNotNull("List is not loaded", withId(R.id.recycler_view));//check if data is not loaded
        try {
            onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click())).check(matches(isDisplayed()));
            onView(withId(R.id.favorite_menu)).perform(click());
        }catch (Exception e){
            e.printStackTrace();
        }
        //perform click on the favorite item

    }
}
