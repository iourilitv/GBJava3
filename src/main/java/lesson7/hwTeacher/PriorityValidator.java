package lesson7.hwTeacher;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
//собственный класс для проверки//здесь для информации
public class PriorityValidator implements ConstraintValidator<Test, Integer> {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= Test.MIN_PRIORITY && value <= Test.MAX_PRIORITY;
    }
}
