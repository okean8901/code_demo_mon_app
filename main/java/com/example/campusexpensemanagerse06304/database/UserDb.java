package com.example.campusexpensemanagerse06304.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.campusexpensemanagerse06304.model.Users;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserDb extends SQLiteOpenHelper {

    private static final String DB_NAME = "campus_expenses";
    private static final String TABLE_NAME = "users";
    private static final int DB_VERSION = 1;
    //create cols for table

    private static final String ID_COL  = "id";
    private static final String USER_COL = "username";
    private static final String PASS_COL = "password";
    private static final String EMAIL_COL = "email";
    private static final String PHONE_COL = "phone";
    private static final String ROLE_ID_COL = "role_id";
    private static final String CREATED_AT_COL = "created_at";
    private static final String UPDATED_AT_COL = "updated_at";
    private static final String DELETE_AT_COL = "delete_at";

    public UserDb(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table
        String query = "CREATE TABLE " + TABLE_NAME + " ( "
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USER_COL + " VARCHAR(60) NOT NULL, "
                + PASS_COL + " VARCHAR(200) NOT NULL, "
                + EMAIL_COL + " VARCHAR(60) NOT NULL, "
                + PHONE_COL + " VARCHAR(30) NOT NULL, "
                + ROLE_ID_COL + " INTEGER, "
                + CREATED_AT_COL + " DATETIME, "
                + UPDATED_AT_COL + " DATETIME, "
                + DELETE_AT_COL + " DATETIME ) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public long insertUserAccount(String username, String password, String email, String phone, int role_id){

        //xử lý lấy thời gian hiện tại
        @SuppressLint({"NewApi", "LocalSuppress"}) DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        @SuppressLint({"NewApi", "LocalSuppress"}) ZonedDateTime now = ZonedDateTime.now();
        @SuppressLint({"NewApi", "LocalSuppress"}) String currentDateTime = dtf.format(now);


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COL, username);
        values.put(PASS_COL, password);
        values.put(EMAIL_COL, email);
        values.put(PHONE_COL, phone);
        values.put(ROLE_ID_COL, 1);
        long insert = db.insert(TABLE_NAME, null, values);
        db.close();
        return insert;

    }
    //check user login
    @SuppressLint("Range")
    public Users checkLoginUser(String username, String password){
        Users users = new Users();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            //select id, username, email, phone, role_id from users where username = ? and password = ?
            String[] cols = {ID_COL, USER_COL, EMAIL_COL, PHONE_COL, ROLE_ID_COL};
            String condition = USER_COL + " = ? AND " + PASS_COL + " = ?";
            String[] params = {username, password};
            Cursor cursor = db.query(TABLE_NAME, cols, condition, params, null, null, null);
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                users.setId(cursor.getInt(cursor.getColumnIndex(ID_COL)));
                users.setUsername(cursor.getString(cursor.getColumnIndex(USER_COL)));
                users.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL_COL)));
                users.setPhone(cursor.getString(cursor.getColumnIndex(PHONE_COL)));
                users.setRole_id(cursor.getInt(cursor.getColumnIndex(ROLE_ID_COL)));

            }
            cursor.close();
            db.close();

        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return users;
    }
    public boolean checkExistUsernameAndEmail(String username, String email){
        boolean checking = false;
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String[] cols = {ID_COL, USER_COL, EMAIL_COL};
            String condition = USER_COL + " = ? AND " + EMAIL_COL + " = ?";
            String[] params = {username, email };
            Cursor cursor = db.query(TABLE_NAME, cols, condition, params, null, null, null);
            if (cursor.getCount() > 0){
                checking = true;
            }
            cursor.close();
            db.close();


        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
        return checking;
    }
    public int changePassword(String username, String email, String newPassword){
        int check = -1;
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(PASS_COL, newPassword);
            String condition = USER_COL + " = ? AND " + EMAIL_COL + " = ?";
            String[] params = {username, email};
            check = db.update(TABLE_NAME, values, condition, params);
            db.close();

        }catch(RuntimeException e){
            throw new RuntimeException(e);
        }return check;
    }
}
