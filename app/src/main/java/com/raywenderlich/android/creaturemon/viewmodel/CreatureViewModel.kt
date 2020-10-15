package com.raywenderlich.android.creaturemon.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.raywenderlich.android.creaturemon.model.*

class CreatureViewModel(private val creatureGenerator: CreatureGenerator = CreatureGenerator()) : ViewModel(){

   private val creatureLiveData = MutableLiveData<Creature>()

    fun getCreatureLiveData(): LiveData<Creature> = creatureLiveData

    var name = ""
    var intelligence = 0
    var strength = 0
    var endurance = 0
    var drawable = 0

    lateinit var creature: Creature

    fun updateCreature(){
        var creatureAttributes = CreatureAttributes(intelligence, strength, endurance)
        creature = creatureGenerator.generateCreature(creatureAttributes, name, drawable)
        creatureLiveData.postValue(creature)
    }

    fun attributeSelected(attributeType: AttributeType, position : Int){
        when(attributeType){
            AttributeType.INTELLIGENCE ->
                intelligence = AttributeStore.INTELLIGENCE[position].value
            AttributeType.STRENGTH ->
                strength = AttributeStore.STRENGTH[position].value
            AttributeType.ENDURANCE ->
                endurance = AttributeStore.ENDURANCE[position].value
        }
        updateCreature()
    }

    fun drawableSelected(drawable: Int){
        this.drawable = drawable
        updateCreature()
    }
}