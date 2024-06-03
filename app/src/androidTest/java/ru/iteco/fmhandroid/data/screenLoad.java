package ru.iteco.fmhandroid.data;


import ru.iteco.fmhandroid.page.Auth;
import ru.iteco.fmhandroid.page.Controlpanel;
import ru.iteco.fmhandroid.page.Main;
import ru.iteco.fmhandroid.page.News;
import ru.iteco.fmhandroid.page.OurMission;

public class screenLoad {
    Auth authPage = new Auth();
    Main mainScreenPage = new Main();
    News newsPage = new News();
    Controlpanel controlPanelPage = new Controlpanel();
    OurMission ourMissionPage = new OurMission();

    public void readyAuthScreen() {
        try {
            authPage.checkLoadScreen();
            authPage.isAuthScreen();
        }
        catch (Exception e) {
            mainScreenPage.logOut();
            authPage.isAuthScreen();
        }
    }

    public void readyControlPanelScreen() {
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToNews();
            newsPage.goToControlPanel();
        } finally {
            controlPanelPage.checkControlPanelScreenLoaded();
        }
    }
    public void readyMainScreen() {
        try {
            mainScreenPage.checkMainScreenLoaded();
        } catch (Exception e) {
            authPage.login();
        } finally {
            mainScreenPage.checkMainScreenLoaded();
        }
    }
    public void readyNewsScreen() {
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goToNews();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goToNews();
        } finally {
            newsPage.checkNewsScreenLoaded();
        }
    }
    public void readyAboutScreen() {
        try {
            mainScreenPage.checkMainScreenLoaded();
            mainScreenPage.goOurMission();
        } catch (Exception e) {
            authPage.login();
            mainScreenPage.goOurMission();
        } finally {
            ourMissionPage.checkOurMissionScreenLoaded();
        }
    }
}