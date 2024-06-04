package ru.iteco.fmhandroid.tests;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import org.hamcrest.Description;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import ru.iteco.fmhandroid.R;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.page.OurMission;
import ru.iteco.fmhandroid.ui.AppActivity;
import static org.hamcrest.Matchers.anyOf;

import android.view.View;
import android.content.res.Resources;

import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class OurMissionUnitTest {
    OurMission ourMissionPage = new OurMission();
    screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyAboutScreen();
    }

    @Test
    @DisplayName("OurMission: Развернуть сернуть описание")
    public void test() {
        int numberTopic = 1;
        String testString = "Нет шаблона и стандарта, есть только дух, " +
                "который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий.";
        ourMissionPage.missionList.check(matches(isDisplayed()));
        ourMissionPage.missionList.perform(actionOnItemAtPosition(numberTopic, click()));
        ourMissionPage.secondTopic.check(matches(isDisplayed()));
        ourMissionPage.missionList.perform(actionOnItemAtPosition(numberTopic, click()));
        onView(withRecyclerView(R.id.our_mission_item_list_recycler_view).atPosition(numberTopic))
                .check(matches(hasDescendant(anyOf(withText(testString)))));
    }

    public RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public class RecyclerViewMatcher {
        private final int recyclerViewId;

        public RecyclerViewMatcher(int recyclerViewId) {
            this.recyclerViewId = recyclerViewId;
        }

        public Matcher<View> atPosition(final int position) {
            return atPositionOnView(position, -1);
        }

        public Matcher<View> atPositionOnView(final int position, final int targetViewId) {

            return new TypeSafeMatcher<View>() {
                Resources resources = null;
                View childView;

                public void describeTo(Description description) {
                    String idDescription = Integer.toString(recyclerViewId);
                    if (this.resources != null) {
                        try {
                            idDescription = this.resources.getResourceName(recyclerViewId);
                        } catch (Resources.NotFoundException var4) {
                            idDescription = String.format("%s (resource name not found)",
                                    new Object[]{Integer.valueOf
                                            (recyclerViewId)});
                        }
                    }

                    description.appendText("with id: " + idDescription);
                }

                public boolean matchesSafely(View view) {

                    this.resources = view.getResources();

                    if (childView == null) {
                        RecyclerView recyclerView =
                                (RecyclerView) view.getRootView().findViewById(recyclerViewId);
                        if (recyclerView != null && recyclerView.getId() == recyclerViewId) {
                            childView = recyclerView.findViewHolderForAdapterPosition(position).itemView;
                        } else {
                            return false;
                        }
                    }

                    if (targetViewId == -1) {
                        return view == childView;
                    } else {
                        View targetView = childView.findViewById(targetViewId);
                        return view == targetView;
                    }

                }
            };
        }
    }
}