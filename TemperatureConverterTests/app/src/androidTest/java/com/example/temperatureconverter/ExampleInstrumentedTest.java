package com.example.temperatureconverter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.temperatureconverter", appContext.getPackageName());
    }

    @RunWith(AndroidJUnit4.class)
    public static class IntentTest {

        @Rule
        public ActivityTestRule<MainActivity> mActivityRule =
                new ActivityTestRule<>(MainActivity.class);

        @Test
        public void ensureTextChangesWork() {
            // Type text and then press the button.
            onView(withId(R.id.inputValue))
                    .perform(typeText("35"), closeSoftKeyboard());

            // Check that the text was typed.
            onView(withId(R.id.inputValue)).check(matches(withText("35")));
        }
    }
}
