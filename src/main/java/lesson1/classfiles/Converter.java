package lesson1.classfiles;

/**
 * Converter
 *
 * @author anton
 * @since 19/08/19
 */
public interface Converter<T,R> {
  R convert(T value);
}