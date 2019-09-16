package com.miwa.myaccounts

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class AccountDBHandler(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        /**
         * データベースファイル名の定数フィールド。
         */
        private const val DATABASE_NAME = "accounttable.db"
        /**
         * バージョン情報の定数フィールド。
         */
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(database: SQLiteDatabase?) {
        val stringBuilder = StringBuilder()
        //stringBuilder.append("CREATE TABLE accounttable(label TEXT PRIMARY KEY,month TEXT,revenu TEXT, spend TEXT);")

        stringBuilder.append("CREATE TABLE accounttable(")
        stringBuilder.append("label TEXT PRIMARYKEY,")
        stringBuilder.append("month TEXT,")
        stringBuilder.append("revenu TEXT,")
        stringBuilder.append("spend TEXT")
        stringBuilder.append(");")

        val sql = stringBuilder.toString()
        database?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}