
package com.raywenderlich.android.creaturemon.model

data class Creature(
        val creatureAttributes: CreatureAttributes = CreatureAttributes(),
        val hitPoints: Int = 0,
        val name: String = "",
        val drawable: Int = 0
)