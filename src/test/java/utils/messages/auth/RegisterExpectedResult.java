package utils.messages.auth;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RegisterExpectedResult {
    public final String SUCCESS_REGISTER = "Регистрация завершена";
    public final String REPEAT_REGISTER = "Error: Учетная запись с такой почтой уже зарегистировавана. Пожалуйста авторизуйтесь.";
    public final String MISSING_PASSWORD_REGISTER = "Error: Введите пароль для регистрации.";

}
