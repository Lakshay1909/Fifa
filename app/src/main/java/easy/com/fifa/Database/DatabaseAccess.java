package easy.com.fifa.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 6/10/2018.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public  List<Formresponse> getformresponse() {
        List<Formresponse> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Fifalist", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Formresponse f=new Formresponse(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            list.add(f);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
