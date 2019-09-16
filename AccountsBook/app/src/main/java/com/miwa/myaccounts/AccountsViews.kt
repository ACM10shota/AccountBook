package com.miwa.myaccounts

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.miwa.my.R

class AccountsViews : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listViewMenu)
        val addBtn = findViewById<FloatingActionButton>(R.id.addBtn)
        //メニュー一覧
        val menuList = mutableListOf("5月の収支","6月の収支","7月の収支","8月の収支","9月の収支")

        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, menuList)

        listView.adapter = adapter

        listView.onItemClickListener = ListItemClickListener()

        addBtn.setOnClickListener {
            val intent = Intent(this@AccountsViews, EditAccounts::class.java)
            startActivity(intent)
        }

    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener  {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val month = position
            val intent = Intent(this@AccountsViews, AccountsGraph::class.java)
            intent.putExtra("MONTH", month)
            startActivity(intent)
        }
    }
}
