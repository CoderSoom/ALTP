package com.demo.demoaltp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class DataManager {
    private var manager: SQLiteDatabase? = null

    companion object {
        val DATA_NAME = "latrieuphu"
    }

    private val context: Context

    constructor(context: Context) {
        this.context = context
        copy()
    }

    private fun copy() {
        val path =
            Environment.getDataDirectory().path + "/data/" +
                    context.packageName + "/" + DATA_NAME
        if (File(path).exists()) {
            return
        }

        //lay old_latrieuphu trong asset
        val ass = context.assets
        val input = ass.open(DATA_NAME)
        val out = FileOutputStream(path)
        val b = ByteArray(1024)
        var le = input.read(b)
        while (le >= 0) {
            out.write(b, 0, le)
            le = input.read(b)
        }
        out.close()
        input.close()
    }

    fun openDatabase() {
        if (manager != null && manager!!.isOpen) {
            return
        }
        val path =
            Environment.getDataDirectory().path + "/data/" +
                    context.packageName + "/" + DATA_NAME
        manager = SQLiteDatabase.openDatabase(
            path, null,
            SQLiteDatabase.OPEN_READWRITE
        )
    }

    fun closeDatabase() {
        if (manager != null && manager!!.isOpen()) {
            manager!!.close()
        }
    }

    var level: Int? = null
    var question = ""
    var caseA = ""
    var caseB = ""
    var caseC = ""
    var caseD = ""
    var truecase: Int? = null

    fun get15Question(sqlStrings: String) {
        openDatabase()
        val sqlString =
            "select * from (select * from Question order by random()) GROUP by level order by level"

        val cursor = manager!!.rawQuery(sqlString, null)
        cursor.moveToFirst()
        val indexA = cursor.getColumnIndex("casea")
        val indexB = cursor.getColumnIndex("caseb")
        val indexC = cursor.getColumnIndex("casec")
        val indexD = cursor.getColumnIndex("cased")
        val indexTruecase = cursor.getColumnIndex("truecase")
        val indexQuestion = cursor.getColumnIndex("question")
        val indexLevel = cursor.getColumnIndex("level")
        while (!cursor.isAfterLast) {
            question = cursor.getString(indexQuestion)
            caseA = cursor.getString(indexA)
            caseB = cursor.getString(indexB)
            caseC = cursor.getString(indexC)
            caseD = cursor.getString(indexD)
            truecase = cursor.getInt(indexTruecase)
            level = cursor.getInt(indexLevel)

            Log.d("DataManager", "get15Question level: " + level)
            Log.d("DataManager", "get15Question question: " + question)
            Log.d("DataManager", "get15Question caseA: " + caseA)
            Log.d("DataManager", "get15Question caseB: " + caseB)
            Log.d("DataManager", "get15Question caseC: " + caseC)
            Log.d("DataManager", "get15Question caseD: " + caseD)
            Log.d("DataManager", "get15Question truecase: " + truecase)
            Log.d("DataManager", "get15Question ========================: ")

            cursor.moveToNext()
        }

        cursor.close()
        closeDatabase()
    }

    fun insertHightSore(name: String, level: Int, money: String) {
        openDatabase()

        //doi voi inset va update phai mo ra mot Transaction
        val contentValues = ContentValues()
        //content gom 2 thanh phan : key + value (key ten cot)
        contentValues.put("name", name)
        contentValues.put("level", level)
        contentValues.put("money", money)
        manager!!.beginTransaction()
        val index = manager!!.insert("high_score", null, contentValues)
        manager!!.setTransactionSuccessful()
        manager!!.endTransaction()

        closeDatabase()
    }

    fun getAllHightScore() {
        openDatabase()
        val sql = "SELECT * FROM high_score ORDER BY level DESC, id DESC"
        var cursor = manager!!.rawQuery(sql, null, null)
        cursor.moveToFirst()
        val indexId = cursor.getColumnIndex("id")
        val indexName = cursor.getColumnIndex("name")
        val indexLevel = cursor.getColumnIndex("level")
        val indexMoney = cursor.getColumnIndex("money")
        while (!cursor.isAfterLast) {
            val id = cursor.getInt(indexId)
            val name = cursor.getInt(indexName)
            val level = cursor.getInt(indexLevel)
            val money = cursor.getInt(indexMoney)
            Log.d("Database", "id" + id)
            Log.d("Database", "level" + level)
            Log.d("Database", "name" + name)
            Log.d("Database", "money" + money)
            print("=============================")
            cursor.moveToNext()
        }
        cursor.close()
        closeDatabase()
    }

    fun createInsertTableHight() {
        openDatabase()
        manager!!.beginTransaction()
        val sql =
            "CREATE TABLE IF NOT EXISTS high_score(" +
                    "   id INTEGER NOT NULL, " +
                    "   name TEXT NOT NULL, " +
                    "   level INTEGER NOT NULL, " +
                    "   money TEXT NOT NULL, " +
                    "   PRIMARY KEY(id AUTOINCREMENT)" +
                    "   )"
        manager!!.execSQL(sql)
        manager!!.setTransactionSuccessful()
        manager!!.endTransaction()
        closeDatabase()
    }

    fun editHightcore(id: Int, level: Int) {
        openDatabase()
        manager!!.beginTransaction()
        val contentValues = ContentValues()
        contentValues.put("level", level)
        manager!!.update("high_score", null, "id =?", arrayOf(id.toString()))
        manager!!.setTransactionSuccessful()
        manager!!.endTransaction()
        closeDatabase()
    }

    fun delHightcore(id: Int) {
        openDatabase()
        manager!!.beginTransaction()

        manager!!.delete("high_score", "id =?", arrayOf(id.toString()))
        manager!!.setTransactionSuccessful()
        manager!!.endTransaction()
        closeDatabase()
    }

}