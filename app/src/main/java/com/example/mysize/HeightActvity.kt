package com.example.mysize

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_height.*

class HeightActvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        // スピナーでいずれかの選択肢がタップされた時
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                // 項目がされずにビューが閉じられた時の処理
                override fun onNothingSelected(parent: AdapterView<*>?) { }
                // 項目が選択された時の処理
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val spinner = parent as? Spinner
                    val item = spinner?.selectedItem as? String
                    item?.let {
                        if (it.isNotEmpty()) height.text = it
                    }
                }
            }

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val heightValue = pref.getInt("HEIGHT", 160)
//        val heightActivity.text = heightValue.toString()
    }
}
