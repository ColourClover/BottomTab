package com.gengqiquan.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gengqiquan.bottombar.BottomTabItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = arrayListOf<BottomTabItem>()
        list.add(BottomTabItem(R.mipmap.ic_launcher_foreground, R.mipmap.ic_launcher_round, Color.BLACK, Color.RED, "首页"))
        list.add(BottomTabItem(R.mipmap.ic_launcher_foreground, R.mipmap.ic_launcher_round, Color.BLACK, Color.RED, "发现"))
        list.add(BottomTabItem(R.mipmap.ic_launcher_foreground, R.mipmap.ic_launcher_round, Color.BLACK, Color.RED, "消息"))
        list.add(BottomTabItem(R.mipmap.ic_launcher_foreground, R.mipmap.ic_launcher_round, Color.BLACK, Color.RED, "购物车"))
        list.add(BottomTabItem(R.mipmap.ic_launcher_foreground, R.mipmap.ic_launcher_round, Color.BLACK, Color.RED, "我的"))
        bottombar.setItems(list)
        bottombar.addDot("我的")
        bottombar.removeDot("我的")
        bottombar.addNumber("消息","9")
    }
}
