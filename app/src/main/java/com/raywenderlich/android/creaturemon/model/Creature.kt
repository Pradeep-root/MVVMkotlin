
package com.raywenderlich.android.creaturemon.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "creature_table")
data class Creature(
        var creatureAttributes: CreatureAttributes = CreatureAttributes(),
        var hitPoints: Int = 0,
        @PrimaryKey @NonNull var name: String = "",
        var drawable: Int = 0
)