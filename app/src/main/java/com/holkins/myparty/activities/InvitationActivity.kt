package com.holkins.myparty.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.beust.klaxon.Klaxon
import com.holkins.myparty.R
import com.holkins.myparty.data.QuestAdapter
import com.holkins.myparty.model.Party
import com.holkins.myparty.model.Person
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_invitation.*

class InvitationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitation)

        val actionbar = supportActionBar
        actionbar?.title = "Вечеринка"
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)

        val text = resources.openRawResource(R.raw.party)
            .bufferedReader().use { it.readText() }

        val party = Klaxon().parse<Party>(text)
        val guests = party?.guests

        Picasso.get()
            .load(party?.picture)
            .placeholder(R.drawable.placeholder)
            .fit()
            .into(partyImageView)

        Picasso.get()
            .load(party?.ownerPictureURL)
            .placeholder(R.drawable.placeholder)
            .into(ownerImageView)

        titleTextView.text = party?.title
        ownerTextView.text = getString(R.string.owner_text, party?.ownerName)

        val myAdapter = guests?.let {
            QuestAdapter(it, object : QuestAdapter.Callback {
                override fun onItemClicked(item: Person) {

                }
            })
        }
        val layoutManager = GridLayoutManager(this, 1)
        guestsRecyclerView.adapter = myAdapter
        guestsRecyclerView.layoutManager = layoutManager
        guestsRecyclerView.hasFixedSize()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
