
package com.raywenderlich.android.creaturemon.view.creature

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.raywenderlich.android.creaturemon.R
import com.raywenderlich.android.creaturemon.model.AttributeStore
import com.raywenderlich.android.creaturemon.model.AttributeType
import com.raywenderlich.android.creaturemon.model.AttributeValue
import com.raywenderlich.android.creaturemon.model.Avatar
import com.raywenderlich.android.creaturemon.view.avatars.AvatarAdapter
import com.raywenderlich.android.creaturemon.view.avatars.AvatarBottomDialogFragment
import com.raywenderlich.android.creaturemon.viewmodel.CreatureViewModel
import kotlinx.android.synthetic.main.activity_creature.*


class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener {

  private lateinit var viewModel : CreatureViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_creature)

    viewModel = ViewModelProviders.of(this).get(CreatureViewModel::class.java)

    configureUI()
    configureSpinnerAdapters()
    configureSpinnerListeners()
    configureEditText()
    configureClickListeners()
    configureLiveDataObservers()
  }

  private fun configureUI() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.add_creature)
    // TODO: hide label
  }

  private fun configureSpinnerAdapters() {
    intelligence.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE)
    strength.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH)
    endurance.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE)
  }

  private fun configureSpinnerListeners() {
    intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.INTELLIGENCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.STRENGTH, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.ENDURANCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
  }

  private fun configureEditText() {
    nameEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {}
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        viewModel.name = s.toString()
      }
    })
  }

  private fun configureClickListeners() {
    avatarImageView.setOnClickListener {
      val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
      bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
    }

    saveButton.setOnClickListener {
      // TODO: handle save button clicked
    }
  }

  override fun avatarClicked(avatar: Avatar) {
    viewModel.drawableSelected(avatar.drawable)
    hideTapLabel()
  }

  private fun hideTapLabel() {
    tapLabel.visibility = View.INVISIBLE
  }

  private fun configureLiveDataObservers(){
    viewModel.getCreatureLiveData().observe(this, Observer { creature ->
      creature?.let{
        hitPoints.text = creature.hitPoints.toString()
        avatarImageView.setImageResource(creature.drawable)
        nameEditText.setText(creature.name)
      }
    })
  }
}
