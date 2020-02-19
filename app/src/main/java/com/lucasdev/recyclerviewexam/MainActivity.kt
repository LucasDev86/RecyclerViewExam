package com.lucasdev.recyclerviewexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.lucasdev.recyclerviewexam.models.Contact
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adapter = ContactRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dataList: MutableList<Contact> = arrayListOf()
        // Data
        for (i in 0 until 100) {
            dataList.add(Contact("아무개 ${i + 1} 호"))
        }

        recyclerView.adapter = adapter
        adapter.setItems(dataList)
        adapter.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_remove ->
                if (adapter.removeItems()) {
                    Toast.makeText(applicationContext, "몇 건이 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                    return true
                } else {
                    Toast.makeText(applicationContext, "삭제할 아이템을 삭제해주세요.", Toast.LENGTH_SHORT)
                        .show()
                    return true
                }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
