
package com.raywenderlich.android.creaturemon.model.room

import android.os.AsyncTask
import com.raywenderlich.android.creaturemon.app.CreaturemonApplication
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureRepository

class RoomRepository : CreatureRepository {
  private val creatureDao: CreatureDao = CreaturemonApplication.database.creatureDao()

  private class InsertAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      return null
    }
  }

  private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      return null
    }
  }
}