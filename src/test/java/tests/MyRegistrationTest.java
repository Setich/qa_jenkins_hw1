package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class MyRegistrationTest extends TestBase {

    @Test
    @DisplayName("Форма успешной регистрации")
    void fillFormTest() {
        step("Открытие формы регистрации", () ->  {
            open("automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });
        step("Заполнение полей формы регистрации", () -> {
                    $("#firstName").setValue("Aleksey");
                    $("#lastName").setValue("Set");
                    $("#userEmail").setValue("jaja@mail.ru");
                    $(byText("Male")).click();
                    $("#userNumber").setValue("0123456789");
                    $("#dateOfBirthInput").click();
                    $(".react-datepicker__month-select").selectOption("March");
                    $(".react-datepicker__year-select").selectOption("1990");
                    $("[aria-label$='March 13th, 1990']").click();
                    $("#subjectsInput").setValue("Physics").pressEnter();
                    $(byText("Music")).click();
                    $("#uploadPicture").uploadFromClasspath("img/123.txt");
                    $("#currentAddress").setValue("Hello qa.guru");
                    $("#state").click();
                    $(byText("Rajasthan")).click();
                    $("#city").click();
                    $(byText("Jaiselmer")).click();
                });
        step("Подтверждение регистрации", () -> {
            $("#submit").click();
        });
        step("Проверка введенных данных при регистрации", () -> {
            $(".table-responsive").shouldHave(text("Student Name Aleksey Set"),
                    text("Student Email jaja@mail.ru"), text("Gender Male"), text("Mobile 0123456789"),
                    text("Date of Birth 13 March,1990"), text("Subjects Physics"), text("Hobbies Music"),
                    text("Picture 123.txt"), text("Address Hello qa.guru"), text("State and City Rajasthan Jaiselmer"));
            $("#closeLargeModal").click();
        });
    }
}
