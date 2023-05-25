package com.naim.timer.screens.game

import com.naim.timer.model.DataWords

class TimerSettings {
    companion object{
        var countDownInterval = 30000L
        var teamName = "Team 1"
        var teamName2 = "Team 2"
        var selectedCategory :String? = null
        var selectedCategory2 :String? = null
        var selectedCategory3 :String? = null
        lateinit var allCategory : Map<String, DataWords>

    }
}