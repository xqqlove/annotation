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
                if (annos.length < 1) continue;
                if (annos[0] instanceof SQLInteger) {
                    SQLInteger sInt = (SQLInteger) annos[0];
                    if (sInt.name().length() < 1) {
                        columnName = field.getName().toUpperCase();
                    } else {
                        columnName = sInt.name();
                    }
                    columnDefs.add(columnName + " INT" + getConstraints(sInt.constraints()));

                }
                if (annos[0] instanceof SQLString) {
                    SQLString sString = (SQLString) annos[0];
                    if (sString.name().length() < 1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints()));
                }
                StringBuilder createCommond=new StringBuilder(" CREATE TABLE "+tableName+"(");
                for (String conumnDef:columnDefs)
                    createCommond.append("\n "+conumnDef+",");
                String tableCreate=createCommond.substring(0,createCommond.length()-1)+");";
                System.out.println("Table Creation SQL for "+ className+" is:\n"+tableCreate);
            }
        }
    }

    public static String getConstraints(Constraints con) {
        String constraints = "";
        if (!con.allowNull())
            constraints += " NOT NULL";
        if (con.primaryKey())
            constraints += " PRIMARY KEY";
        if (con.unique())
            constraints += " UNIQUE";
        return constraints;
    }
}
