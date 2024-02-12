package bonfiglio.scozzari.ing_soft.theatersoftware.utils;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class ObjectUpdater <T> {

    public T updateObject(T object, T newObject) throws IllegalAccessException {

        if (object instanceof Updatable) {

            Field[] fields = newObject.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);

                Object fieldValue = field.get(newObject);

                if (fieldValue != null && !fieldValue.equals(field.get(object))) {
                    field.set(object, fieldValue);
                }
            }

            ((Updatable) object).setUpdateAt(LocalDateTime.now());

        }

        return object;

    }

}
