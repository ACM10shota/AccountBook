package com.miwa.myaccounts

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.miwa.my.R
import android.widget.ArrayAdapter


class EditAccounts : AppCompatActivity() {
    val accountDbHandler = AccountDBHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_accounts)

        val saveBtn = findViewById<Button>(R.id.saveBtn)
        //入力月
        val month = findViewById<EditText>(R.id.month)
        //収入
        val revenu = findViewById<EditText>(R.id.revenu)
        //支出
        val spend = findViewById<EditText>(R.id.spend)

        //データベースの書き込みを許可
        val database = accountDbHandler.writableDatabase

        //コンボボックス
        val selectSpinner = findViewById<Spinner>(R.id.Spinner01);
        //コンボボックス用Adapter設定
        val adapter = ArrayAdapter.createFromResource(this, R.array.account_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        selectSpinner.setAdapter(adapter);

        //保存ボタン押下時
        saveBtn.setOnClickListener {
            //コンボボックスのインデックス取得
            val selectedIndex = selectSpinner.selectedItemPosition + 1

            //削除用SQL
            val sqlDelete = "DELETE FROM accounttable WHERE label = ?"
            val statementDlete = database.compileStatement(sqlDelete)
            statementDlete.bindLong(1, 1)
            //SQLの実行
            statementDlete.executeUpdateDelete()

            //挿入用SQL文
            val sqlInsert = "INSERT INTO accounttable (label, month, revenu, spend)VALUES(?, ?, ?, ?)"
            val statementInsert = database.compileStatement(sqlInsert)
            statementInsert.bindString(1, selectedIndex.toString())
            statementInsert.bindString(2, month.text.toString())
            statementInsert.bindString(3, revenu.text.toString())
            statementInsert.bindString(4, spend.text.toString())
            //ISNERTの実行
            statementInsert.executeInsert()

                finish()
        }
    }

    override fun onDestroy() {
        accountDbHandler.close()
        super.onDestroy()
    }
}
