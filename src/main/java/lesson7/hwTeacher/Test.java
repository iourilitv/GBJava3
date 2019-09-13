package lesson7.hwTeacher;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Constraint(validatedBy = PriorityValidator.class)//ограничения
// (валидатор-проверяльщик сторонняя библиотека(набор интерфейсов))
//для подключения валидатора нужно в pom.xml подключить зависимость javax.validation
public @interface Test {
    int MIN_PRIORITY = 1;//константа минимальный приоритет
    int MAX_PRIORITY = 10;//константы максимальный приоритет
    int priority() default 5;
}
