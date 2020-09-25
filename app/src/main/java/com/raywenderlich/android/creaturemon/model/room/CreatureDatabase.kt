
package com.raywenderlich.android.creaturemon.model.room

import android.arch.persistence.room.RoomDatabase

abstract class CreatureDatabase : RoomDatabase() {
  abstract fun creatureDao(): CreatureDao
}