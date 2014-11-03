package com.example.root.birdnest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MySQLite extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "BirdDB";

    // Defining DB
    // Btable
    // | id | Sci Name  | Family Name | Local Name | Malay Name | Location | Family  | Image   | Sound |
    // |  0 |           |             |            |            |          |         |         |
    // |  1 |           |             |            |            |          |         |         |
    //

    public static final String TABLE_NAME = "BTable";
    public static final String ID = "id";
    public static final String SCIENTIFIC_NAME = "SciName";
    public static final String FAMILY_NAME = "FamilyName";
    public static final String LOCAL_NAME = "LocalName";
    public static final String MALAY_NAME = "MalayName";
    public static final String FAMILY = "Family";
    public static final String LOCATION = "Location";
    public static final String BIRD_IMAGES = "Image";
    public static final String BIRD_SOUND = "Sound";

    public static final String COLUMN_NAME_TITLE = "location";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MySQLite.TABLE_NAME + " (" +
                    MySQLite.ID + " INTEGER PRIMARY KEY," +
                    MySQLite.SCIENTIFIC_NAME + TEXT_TYPE + COMMA_SEP +
                    MySQLite.FAMILY_NAME + TEXT_TYPE + COMMA_SEP +
                    MySQLite.LOCAL_NAME + TEXT_TYPE + COMMA_SEP +
                    MySQLite.MALAY_NAME + TEXT_TYPE + COMMA_SEP +
                    MySQLite.FAMILY + TEXT_TYPE + COMMA_SEP +
                    MySQLite.LOCATION + TEXT_TYPE + COMMA_SEP +
                    MySQLite.BIRD_IMAGES + TEXT_TYPE + COMMA_SEP+
                    MySQLite.BIRD_SOUND+ TEXT_TYPE+

                    " )";

    public MySQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create table
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        // Drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS "+MySQLite.TABLE_NAME);

        this.onCreate(db);
    }


    // Add Database
    public void addBird(Bird bird){
        // Notify that new Bird is successfully added
        Log.d("addBird", bird.toString());

        // Select database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Add content into database
        values.put(MySQLite.SCIENTIFIC_NAME, bird.ScientificName);
        values.put(MySQLite.FAMILY_NAME, bird.FamilyName);
        values.put(MySQLite.LOCAL_NAME, bird.LocalName);
        values.put(MySQLite.MALAY_NAME, bird.MalayName);
        values.put(MySQLite.FAMILY, bird.Family);
        values.put(MySQLite.BIRD_IMAGES, bird.Image);
        values.put(MySQLite.BIRD_SOUND, bird.Sound);
        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    // Get All Bird
    public List<Bird> getAllBird(){
        List<Bird> birds = new LinkedList<Bird>();

        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // Loop through the database
        Bird bird = null;
        if (cursor.moveToFirst()){
            do {
                bird = new Bird();
                bird.setId(Integer.parseInt(cursor.getString(0)));
                bird.setName(cursor.getString(1));
                bird.setLocation(cursor.getString(2));
                bird.setImage(cursor.getString(3));
                bird.setSound(cursor.getString(4));
                birds.add(bird);
            } while(cursor.moveToNext());

        }

        Log.d("getAllBirds()", birds.toString());

        return birds;
    }

    // Clean Database
    public void cleanDB (){
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("DELETE FROM " + MySQLite.TABLE_NAME);

    }


    // Read Database and returns class associated with the query
    public List<Bird> getBird(String Query, String Column){
        List<Bird> birds = new LinkedList<Bird>();
        SQLiteDatabase db=this.getReadableDatabase();
        // Db exec : Search Table for Entry Related to Query of a Specified Column
        String DbCommand ="SELECT  * FROM " + TABLE_NAME+" WHERE "+ Column + "=" + "\"" + Query+ "\"" ;
        Cursor cursor = db.rawQuery(DbCommand,null);

        Bird bird = null;
        if (cursor.moveToFirst()){
            do {
                bird = new Bird();
                bird.setId(Integer.parseInt(cursor.getString(0)));
                bird.setName(cursor.getString(1));
                bird.setLocation(cursor.getString(2));
                bird.setImage(cursor.getString(3));
                bird.setSound(cursor.getString(4));
                birds.add(bird);
            } while(cursor.moveToNext());

        }

        Log.d("getBird(" + Query+", " + Column + ")", birds.toString());

        return birds;
    }


}
