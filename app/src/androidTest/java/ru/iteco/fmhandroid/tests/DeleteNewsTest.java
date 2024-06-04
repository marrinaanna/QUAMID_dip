package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.assertNotEquals;

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
import ru.iteco.fmhandroid.page.Filters;
import ru.iteco.fmhandroid.page.Main;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class DeleteNewsTest {
    News newsPage = new News();

    static Controlpanel controlPanelPage = new Controlpanel();
    static screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule2 =
            new ActivityScenarioRule<>(AppActivity.class);
    @Before
    public  void readyScreen() {
        readyScreen.readyNewsScreen();
    }

    @Test
    @DisplayName("Control Panel: Удаление новости")
    public void testCpDeleteNews() {
        newsPage.goToControlPanel();
        String title = dataHelper.Text.getText(controlPanelPage.newsItemTitle);
        controlPanelPage.deleteNewsButton.perform(click());
        controlPanelPage.cancelButton.check(matches(isDisplayed()));
        controlPanelPage.okButton.check(matches(isDisplayed()));
        controlPanelPage.okButton.perform(click());
        controlPanelPage.checkListNewsLoaded();
        String title2 = dataHelper.Text.getText(controlPanelPage.newsItemTitleAfterDelete);
        assertNotEquals(title, title2);
    }
}
