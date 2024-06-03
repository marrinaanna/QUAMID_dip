package ru.iteco.fmhandroid.page;


import static androidx.test.espresso.Espresso.onView;
        import static androidx.test.espresso.action.ViewActions.click;
        import static androidx.test.espresso.assertion.ViewAssertions.matches;
        import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
        import static androidx.test.espresso.matcher.ViewMatchers.withId;
        import static androidx.test.espresso.matcher.ViewMatchers.withText;
        import static ru.iteco.fmhandroid.data.dataHelper.childAtPosition;
        import static ru.iteco.fmhandroid.data.dataHelper.elementWaiting;
        import static ru.iteco.fmhandroid.data.dataHelper.withIndex;

        import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
        import static org.hamcrest.Matchers.allOf;
        import static org.hamcrest.Matchers.is;

        import androidx.test.espresso.ViewInteraction;

        import io.qameta.allure.kotlin.Allure;
        import ru.iteco.fmhandroid.R;

public class News {
    Controlpanel controlPanelPage = new Controlpanel();
    public ViewInteraction newsScreenName = onView(withText("News"));
    public ViewInteraction sortNewsButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction allNewsList = onView(withId(R.id.all_news_cards_block_constraint_layout));
    public int newsList = R.id.news_list_recycler_view;
    public ViewInteraction editButton = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction dateNewsField = onView(withIndex(withId(R.id.news_item_date_text_view), 0));
    public ViewInteraction dateNewsFieldAfterSort = onView(withIndex(withId(R.id.news_item_date_text_view), 0));
    public ViewInteraction titleFirstNews = onView(withIndex(withId(R.id.news_item_title_text_view), 0));
    public ViewInteraction descriptionFirstNews = onView(withIndex(withId(R.id.news_item_description_text_view), 0));

    public void checkNewsScreenLoaded() {
        Allure.step("Проверка загрузки экрана с новостями");
        elementWaiting(withText("News"), 10000);
        newsScreenName.check(matches(isDisplayed()));
        sortNewsButton.check(matches(isDisplayed()));
        filterNewsButton.check(matches(isDisplayed()));
        allNewsList.check(matches(isDisplayed()));
    }

    public void checkListNewsLoaded() {
        Allure.step("Проверка отображения списка новостей");
        elementWaiting(withId(newsList), 10000);
    }

    public void checkDescriptionView(String text) {
        Allure.step("Проверка отображения описания с текстом");
        onView(allOf(withId(R.id.news_item_title_text_view), withText(text))).
                check(matches(isDisplayed()));
    }

    public void goToControlPanel() {
        Allure.step("Переход  в ControlPanel");
        editButton.perform(click());
        controlPanelPage.checkControlPanelScreenLoaded();
    }

}