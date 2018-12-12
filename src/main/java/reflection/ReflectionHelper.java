package reflection;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReflectionHelper {
    private static final Logger LOG = LogManager.getLogger(ReflectionHelper.class);

    public static Object createInstance(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (Exception e) {
            LOG.error("Ошибка создания экземпляра класса через Reflection", e);
            return null;
        }
    }

    public static void setField(Object object, String fieldName, String fieldValue) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            if (field.getType().equals(String.class))
                field.set(object, fieldValue);
            else if (field.getType().equals(int.class))
                field.set(object, Integer.decode(fieldValue));

            field.setAccessible(false);
        } catch (Exception e) {
            LOG.error("Ошибка записи значения в поле объекта через Reflection", e);
        }
    }
}