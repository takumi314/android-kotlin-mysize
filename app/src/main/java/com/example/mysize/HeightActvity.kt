package com.example.mysize

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_height.*

class HeightActvity : AppCompatActivity() {

    // 共通プレファレンス用の参照キー

    private val PREF_KEY_HEIGHT = "HEIGHT"

    // Life cycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)

        // スピナーでいずれかの選択肢タップのイベントを監視します
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
                    // itemが非nullならば値をセットする
                    item?.let {
                        if (it.isNotEmpty()) height.text = it
                    }
                }
            }

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val heightValue = pref.getInt(PREF_KEY_HEIGHT, 160) // デファウト値: 160
        height.text = heightValue.toString()
        // シークバーに共有プレファレンスから取得した値を設定する
        seekBar.progress = heightValue

        // シークバー操作時のイベントを監視します
        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                // シークバーの値を変更した時にコールされる
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    process: Int,
                    fromUser: Boolean
                ) {
                    height.text = process.toString()
                }

                // Not necessary to implement
                override
                fun onStartTrackingTouch(seekBar: SeekBar?) { }

                override
                fun onStopTrackingTouch(seekBar: SeekBar?) { }
            })
    }

    // アクティビティが非表示なる時にコールされる
    override fun onPause() {
        super.onPause()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            putInt(PREF_KEY_HEIGHT, height.text.toString().toInt())
        }
    }

}
