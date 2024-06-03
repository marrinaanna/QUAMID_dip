package ru.iteco.fmhandroid.page;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.data.dataHelper.*;
import androidx.test.espresso.ViewInteraction;
import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class About {

    public ViewInteraction tradeMarkImage = onView(withId(R.id.trademark_image_view));
    public ViewInteraction version = onView(withId(R.id.about_version_title_text_view));
    public ViewInteraction versionValue = onView(withId(R.id.about_version_value_text_view));
    public ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_label_text_view));
    public ViewInteraction privacyPolicyLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction termsOfUse = onView(withId(R.id.about_terms_of_use_label_text_view));
    public ViewInteraction termsOfUseLink = onView(withId(R.id.about_privacy_policy_value_text_view));
    public ViewInteraction companyInfo = onView(withId(R.id.about_company_info_label_text_view));
    public ViewInteraction returnButton = onView(withId(R.id.about_back_image_button));

    public void checkAboutScreenLoaded() {
        Allure.step("Проверка загрузки экрана /'About/'");
        elementWaiting(withText("Version:"), 10000);
        tradeMarkImage.check(matches(isDisplayed()));
        version.check(matches(isDisplayed()));
        versionValue.check(matches(isDisplayed()));
        privacyPolicy.check(matches(isDisplayed()));
        privacyPolicyLink.check(matches(isDisplayed()));
        termsOfUse.check(matches(isDisplayed()));
        termsOfUseLink.check(matches(isDisplayed()));
        companyInfo.check(matches(isDisplayed()));
        returnButton.check(matches(isDisplayed()));
    }


}