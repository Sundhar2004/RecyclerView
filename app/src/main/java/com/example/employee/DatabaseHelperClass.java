package com.example.employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private  static final String DATABASE_NAME="employee_database";
    private static final String TABLE_NAME="EMPLOYEE";

    public static final String ID="id";
    public static final String NAME="name";
    public static final String EMAIL="email";
    private SQLiteDatabase sqLiteDatabase;

 private  static  final String CREATE_TABLE= "create table " + TABLE_NAME +"("
         +ID+ " INTEGER PRIMARY KEY ,"+ NAME + " TEXT NOT NULL," +EMAIL+ " TEXT NOT NULL);";

    public DatabaseHelperClass( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
            DB.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL(" DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(DB);

    }
    public void addEmployee(EmployeeModelClass employeeModelClass){

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME, employeeModelClass.getName());
        contentValues.put(DatabaseHelperClass.EMAIL, employeeModelClass.getEmail());

        SQLiteDatabase DB=this.getWritableDatabase();
        DB.insert(DatabaseHelperClass.TABLE_NAME,null,contentValues);
    }
    public List<EmployeeModelClass> getEmployeeList(){
        String sql=" select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<EmployeeModelClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                storeEmployee.add(new EmployeeModelClass(id,name,email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;

    }
    public void updateEmployee(EmployeeModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.NAME,employeeModelClass.getName());
        contentValues.put(DatabaseHelperClass.EMAIL,employeeModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " =?", new String[]{String.valueOf(employeeModelClass.getId())});
    }
    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,ID + " = ? ", new String[]{String.valueOf(id)});
    }

}
