package mostsimpleannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.CONSTRUCTOR,
       ElementType.ANNOTATION_TYPE,ElementType.PACKAGE,ElementType.FIELD,
       ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface Simple {
    String value() default "-default-";
}
