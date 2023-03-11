package me.euris.mall.util;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import me.euris.mall.exception.ParamException;
import org.apache.commons.collections4.MapUtils;

import java.util.*;

/**
 * @Author Euris Lee
 * @Version 1.0.0
 * @Description TODO
 * @CreateTime 03-10-2023 19:31:00
 */
public class ValidatorUtil {

    private static ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set validateInfo = validator.validate(t, groups);
        if (validateInfo.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap map = Maps.newLinkedHashMap();
            Iterator iterator = validateInfo.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = (ConstraintViolation) iterator.next();
                map.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return map;
        }
    }

    public static Map<String, String> validate(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        if (!iterator.hasNext()) {
            return Collections.emptyMap();
        }
        Object object = iterator.next();
        Map map = validate(object, new Class[0]);
        while (map.isEmpty()) {
            object = iterator.next();
            map = validate(object, new Class[0]);
        }
        return map;
    }

    public static Map<String, String> validate(Object first, Object... objects) {
        if (objects != null && objects.length > 0) {
            return validate(Lists.asList(first, objects));
        } else {
            return validate(first, new Class[0]);
        }
    }

    public static void check(Object param) throws ParamException {
        Map<String, String> map = ValidatorUtil.validate(param);
        if (MapUtils.isNotEmpty(map)) {
            throw new ParamException(map.toString());
        }
    }

}
