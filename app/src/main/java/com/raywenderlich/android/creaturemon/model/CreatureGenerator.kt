package com.raywenderlich.android.creaturemon.model

class CreatureGenerator {

    fun generateCreature(creatureAttributes: CreatureAttributes, name: String = "", drawable: Int = 0): Creature{
        val hitPoints = 5 * creatureAttributes.intelligence +
                        3 * creatureAttributes.strength +
                        4 * creatureAttributes.endurance

        return Creature(creatureAttributes, hitPoints, name, drawable)
    }
}