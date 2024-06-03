package ru.iteco.fmhandroid.page;


import static androidx.test.espresso.Espresso.onView;
        import static androidx.test.espresso.action.ViewActions.click;
        import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
        import static androidx.test.espresso.action.ViewActions.replaceText;
        import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
        import static androidx.test.espresso.assertion.ViewAssertions.matches;
        import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
        import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
        import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
        import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
        import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
        import static androidx.test.espresso.matcher.ViewMatchers.withHint;
        import static androidx.test.espresso.matcher.ViewMatchers.withId;
        import static androidx.test.espresso.matcher.ViewMatchers.withParent;
        import static androidx.test.espresso.matcher.ViewMatchers.withText;
        import static org.hamcrest.Matchers.allOf;
        import static org.hamcrest.Matchers.is;
        import static org.hamcrest.Matchers.not;
        import android.view.View;
        import androidx.test.espresso.ViewInteraction;
        import org.hamcrest.core.IsInstanceOf;
        import io.qameta.allure.kotlin.Allure;
        import ru.iteco.fmhandroid.R;

import static ru.iteco.fmhandroid.data.dataHelper.*;

public class Main{

    //top
    public ViewInteraction menuButton = onView(withId(R.id.main_menu_image_button));
    public ViewInteraction tradeMark = onView(withId(R.id.trademark_image_view));
    public ViewInteraction ourMissionButton = onView(withId(R.id.our_mission_image_button));
    public ViewInteraction logOutButton = onView(withId(R.id.authorization_image_button));

    //Text menu
    public ViewInteraction menuMain = onView(withText("Main"));
    public ViewInteraction menuClaims = onView(withText("Claims"));
    public ViewInteraction menuNews = onView(withText("News"));
    public ViewInteraction menuAbout = onView(withText("About"));
    public ViewInteraction logOut = onView(withText("Log out"));

    // news
    public ViewInteraction news = onView(withText("News"));
    public ViewInteraction newsUnit = onView(withId(R.id.news_list_recycler_view));
    public ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
    public ViewInteraction singleNews = onView(allOf(withId(R.id.news_list_recycler_view),
            childAtPosition(withId(R.id.all_news_cards_block_constraint_layout), 0)));
    public ViewInteraction singleNewsAfterOpen = onView(
            allOf(withId(R.id.news_list_recycler_view),
                    childAtPosition(
                            withId(R.id.all_news_cards_block_constraint_layout),
                            0)));
    public ViewInteraction unitNewsButton = onView(
            allOf(withId(R.id.expand_material_button),
                    childAtPosition(
                            withClassName(is("android.widget.LinearLayout")), childAtPosition(
                                    withClassName(is("android.widget.LinearLayout")),
                                    withId(R.id.container_list_news_include_on_fragment_main),
                                    0),
                            4)));

    // Claims

    public void checkMainScreenLoaded() {
        Allure.step("Проверка загрузки основного экраа");
        elementWaiting(withText("News"), 10000);
    }

    public void isMainScreen() {
        Allure.step("Проверка полей основного экраа");
        tradeMark.check(matches(isDisplayed()));
        news.check(matches(isDisplayed()));
        newsUnit.check(matches(isDisplayed()));
    }

    public void logOut() {
        Allure.step("Логаут");
        logOutButton.perform(click());
        logOut.check(matches(isDisplayed()));
        logOut.perform(click());
    }

    public void goToNews() {
        Allure.step("Переход в раздел новостей");
        menuButton.check(matches(isDisplayed()));
        menuButton.perform(click());
        menuNews.check(matches(isDisplayed()));
        menuNews.perform(click());
    }

    public void goOurMission() {
        Allure.step("Переход в раздел \"Наша миссия\"");
        ourMissionButton.check(matches(isDisplayed()));
        ourMissionButton.perform(click());
    }

    public void descriptionNewsIsDisplayed(int position) {
        Allure.step("Проверка что описание отображается");
        onView(withIndex(withId(R.id.news_item_description_text_view), position)).check(matches(isDisplayed()));
    }

    public void descriptionNewsIsNotDisplayed(int position) {
        Allure.step("Проверка что описание  не отображается");
        onView(withIndex(withId(R.id.news_item_description_text_view), position)).check(matches(not(isDisplayed())));
    }
}