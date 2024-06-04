package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.dataHelper;
import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.data.someData;
import ru.iteco.fmhandroid.page.Controlpanel;
import ru.iteco.fmhandroid.page.CreatAndEditNews;
import ru.iteco.fmhandroid.page.Filters;
import ru.iteco.fmhandroid.page.Main;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilterNotActiveNewsTest {
    News newsPage = new News();
    static Controlpanel controlPanelPage = new Controlpanel();
    static Main mainScreenPage = new Main();
    Filters filterNewsPage = new Filters();
    static someData dat = new someData();
    static screenLoad readyScreen = new screenLoad();
    static CreatAndEditNews createEditNewsPage = new CreatAndEditNews();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule2 =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public  void readyScreen() {
        readyScreen.readyNewsScreen();
    }

    @Test
    @DisplayName("Control Panel: фильтр Not Active")
    public void testCpFilterNotActive() {
        newsPage.goToControlPanel();
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.switcher.perform(click());
        createEditNewsPage.saveNews();
        controlPanelPage.newsFilterButton.perform(click());
        filterNewsPage.checkFilterNewsScreenLoaded();
        filterNewsPage.checkBoxActive.perform(click());
        filterNewsPage.checkBoxActive.check(matches(isNotChecked()));
        filterNewsPage.filterButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String firstNewsStatus = dataHelper.Text.getText(controlPanelPage.newsStatus);
        assertEquals(firstNewsStatus, "Not active");
    }
}
