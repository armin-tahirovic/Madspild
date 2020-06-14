package com.example.madspild.firebase;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.madspild.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

  @Rule
  public ActivityTestRule<FirebaseActivity> mActivityTestRule = new ActivityTestRule<>(FirebaseActivity.class);

  @Test
  public void espressoTest() {
    ViewInteraction supportVectorDrawablesButton = onView(
      allOf(withId(R.id.email_button), withText("Log ind med mail"),
        childAtPosition(
          allOf(withId(R.id.btn_holder),
            childAtPosition(
              withId(R.id.container),
              0)),
          0)));
    supportVectorDrawablesButton.perform(scrollTo(), click());

    ViewInteraction textInputEditText = onView(
      allOf(withId(R.id.email),
        childAtPosition(
          childAtPosition(
            withId(R.id.email_layout),
            0),
          0)));
    textInputEditText.perform(scrollTo(), replaceText("armin2013@live.dk"), closeSoftKeyboard());

    ViewInteraction appCompatButton = onView(
      allOf(withId(R.id.button_next), withText("Næste"),
        childAtPosition(
          allOf(withId(R.id.email_top_layout),
            childAtPosition(
              withClassName(is("android.widget.ScrollView")),
              0)),
          2)));
    appCompatButton.perform(scrollTo(), click());

    ViewInteraction textInputEditText2 = onView(
      allOf(withId(R.id.password),
        childAtPosition(
          childAtPosition(
            withId(R.id.password_layout),
            0),
          0)));
    textInputEditText2.perform(scrollTo(), replaceText("armintest"), closeSoftKeyboard());

    ViewInteraction appCompatButton2 = onView(
      allOf(withId(R.id.button_done), withText("Log ind"),
        childAtPosition(
          childAtPosition(
            withClassName(is("android.widget.ScrollView")),
            0),
          4)));
    appCompatButton2.perform(scrollTo(), click());

    ViewInteraction appCompatEditText = onView(
      allOf(withId(R.id.edProduct), withText("Vare"),
        childAtPosition(
          allOf(withId(R.id.frameLayout),
            withParent(withId(R.id.view_pager))),
          2),
        isDisplayed()));
    appCompatEditText.perform(replaceText("apples"));

    ViewInteraction appCompatEditText2 = onView(
      allOf(withId(R.id.edProduct), withText("apples"),
        childAtPosition(
          allOf(withId(R.id.frameLayout),
            withParent(withId(R.id.view_pager))),
          2),
        isDisplayed()));
    appCompatEditText2.perform(closeSoftKeyboard());

    ViewInteraction appCompatButton3 = onView(
      allOf(withId(R.id.addProduct), withText("Tilføj"),
        childAtPosition(
          allOf(withId(R.id.frameLayout),
            withParent(withId(R.id.view_pager))),
          1),
        isDisplayed()));
    appCompatButton3.perform(click());

    ViewInteraction tabView = onView(
      allOf(withContentDescription("Køleskab"),
        childAtPosition(
          childAtPosition(
            withId(R.id.tabs),
            0),
          1),
        isDisplayed()));
    tabView.perform(click());

    ViewInteraction appCompatEditText3 = onView(
      allOf(withId(R.id.navnVare), withText("Varenavn"),
        childAtPosition(
          allOf(withId(R.id.koleskabLayout),
            withParent(withId(R.id.view_pager))),
          2),
        isDisplayed()));
    appCompatEditText3.perform(replaceText("wine"));

    ViewInteraction appCompatEditText4 = onView(
      allOf(withId(R.id.navnVare), withText("wine"),
        childAtPosition(
          allOf(withId(R.id.koleskabLayout),
            withParent(withId(R.id.view_pager))),
          2),
        isDisplayed()));
    appCompatEditText4.perform(closeSoftKeyboard());

    ViewInteraction appCompatEditText5 = onView(
      allOf(withId(R.id.datePicker),
        childAtPosition(
          allOf(withId(R.id.koleskabLayout),
            withParent(withId(R.id.view_pager))),
          5),
        isDisplayed()));
    appCompatEditText5.perform(click());

    ViewInteraction appCompatButton4 = onView(
      allOf(withId(android.R.id.button1), withText("OK"),
        childAtPosition(
          childAtPosition(
            withClassName(is("android.widget.ScrollView")),
            0),
          3)));
    appCompatButton4.perform(scrollTo(), click());

    pressBack();

    ViewInteraction appCompatButton5 = onView(
      allOf(withId(R.id.tilføjVare), withText("Tilføj vare"),
        childAtPosition(
          allOf(withId(R.id.koleskabLayout),
            withParent(withId(R.id.view_pager))),
          1),
        isDisplayed()));
    appCompatButton5.perform(click());

    ViewInteraction imageView = onView(
      allOf(withId(R.id.imageAPI),
        childAtPosition(
          allOf(withId(R.id.koleskabLayout),
            withParent(withId(R.id.view_pager))),
          5),
        isDisplayed()));
    imageView.check(matches(isDisplayed()));

    ViewInteraction appCompatButton6 = onView(
      allOf(withId(R.id.button2), withText("SIGN OUT"),
        childAtPosition(
          childAtPosition(
            withClassName(is("androidx.coordinatorlayout.widget.CoordinatorLayout")),
            0),
          2),
        isDisplayed()));
    appCompatButton6.perform(click());
  }

  private static Matcher<View> childAtPosition(
    final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
          && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }
}
