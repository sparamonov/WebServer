package practice;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CreatedBy(author = "Seggas", date = "01.04.18")

@Retention(RetentionPolicy.RUNTIME)
public @interface CreatedBy {
    String author();
    String date();
}
