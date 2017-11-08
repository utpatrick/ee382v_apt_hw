package com.example.temperatureconverter;

/**
 * Created by Patrick on 11/7/2017.
 */
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void ensureTextCanType() {
        onView(withId(R.id.inputValue)).perform(typeText("35"), closeSoftKeyboard());
        onView(withId(R.id.inputValue)).check(matches(withText("35")));
    }

    @Test
    public void ensureCelCheck(){
        onView(withId(R.id.radio0)).perform(click());
        onView(withId(R.id.radio0)).check(matches(isChecked()));
    }

    @Test
    public void ensureFahCheck(){
        onView(withId(R.id.radio1)).perform(click());
        onView(withId(R.id.radio1)).check(matches(isChecked()));
    }

    @Test
    public void triggerIntentSuccess() {
        onView(withId(R.id.inputValue)).perform(typeText("212"), closeSoftKeyboard());
        onView(withId(R.id.radio0)).perform(click());
        onView(withId(R.id.button1)).perform(click());
        intended(allOf(
                hasComponent(ResultPage.class.getName()),
                hasExtraWithKey("INPUT")));

        // check result in new intent
        onView(withId(R.id.result)).check(matches(withText("212.0F is 100.0C")));
    }

    @Test
    public void triggerIntentBackToMain() {
        onView(withId(R.id.inputValue)).perform(typeText("212"), closeSoftKeyboard());
        onView(withId(R.id.radio0)).perform(click());
        onView(withId(R.id.button1)).perform(click());

        // click on button on new intent
        onView(withId(R.id.button)).perform(click());
        intended(allOf(
                hasComponent(MainActivity.class.getName())));
    }
}
