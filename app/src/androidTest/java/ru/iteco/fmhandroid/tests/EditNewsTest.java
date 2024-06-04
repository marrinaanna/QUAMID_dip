package ru.iteco.fmhandroid.tests;

import static androidx.test.espresso.action.ViewActions.click;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.data.screenLoad;
import ru.iteco.fmhandroid.data.someData;
import ru.iteco.fmhandroid.page.Controlpanel;
import ru.iteco.fmhandroid.page.CreatAndEditNews;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class EditNewsTest {
    News newsPage = new News();
    Controlpanel controlPanelPage = new Controlpanel();
    CreatAndEditNews createEditNewsPage = new CreatAndEditNews();
    someData dat = new someData();
    screenLoad readyScreen = new screenLoad();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void readyScreen() {
        readyScreen.readyNewsScreen();
    }


    String category2 = dat.category2;
    String title2 = dat.title2;
    String description2 = dat.description2;
    String date2 = dat.date2;
    String time2 = dat.time2;


    @Test
    @DisplayName("Control panel: Отредактировать новость")
    public void testEditTitleNews() {
        controlPanelPage.addNewsButton.perform(click());
        controlPanelPage.editNewsButton.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.fillInFormNews(category2, title2, date2, time2, description2);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
        controlPanelPage.editNewsButtonAfterEdit.perform(click());
        createEditNewsPage.checkEditNewsScreenLoaded();
        createEditNewsPage.checkNewsExists(category2, title2, date2, time2, description2);
        createEditNewsPage.saveNews();
        controlPanelPage.checkControlPanelScreenLoaded();
    }
}
