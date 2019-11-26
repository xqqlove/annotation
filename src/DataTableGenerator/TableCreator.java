package DataTableGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("argument:annotated classes");
            System.exit(0);
        }
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable daTable = cl.getAnnotation(DBTable.class);
            if (daTable == null) {
                System.out.println("No DBTable annotation in class " + className);
                continue;
            }
            String tableName = daTable.name();
//            System.out.println(tableName);
            if (tableName.length() < 1) {
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnDefs = new ArrayList<>();
            for (Field field : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] annos = field.getDeclaredAnnotations();
                if (annos.length<1) continue;
                if (annos[0] instanceof SQLString){

                }
                if (annos[0] instanceof SQLInteger){

                }
            }
        }
    }
}
