package com.yuvrajpatel.thescoretask.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yuvrajpatel.thescoretask.R
import com.yuvrajpatel.thescoretask.model.Player

import kotlinx.android.synthetic.main.recyclerview_team_player_list_item.view.*

class TeamPlayersRecyclerViewAdapter(private val playerList: List<Player>?) :
    RecyclerView.Adapter<TeamPlayersRecyclerViewAdapter.PlayerViewHolder>() {

    private val mTAG = TeamPlayersRecyclerViewAdapter::class.java.simpleName

    /**
     *  This function is returning the view for each item in the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        Log.d(mTAG, "onCreateViewHolder()")
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem =
            layoutInflater.inflate(R.layout.recyclerview_team_player_list_item, parent, false)
        return PlayerViewHolder(listItem)
    }

    /**
     * Get size of the list
     * @return list size
     */
    override fun getItemCount(): Int {
        if (playerList != null)
            return playerList.size

        return 0
    }

    /**
     *  This function is binding the data on the list
     */
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        Log.d(mTAG, "onBindViewHolder()")
        playerList?.get(position)?.let { holder.bind(it) }
    }


    class PlayerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val mTAG =
            TeamPlayersRecyclerViewAdapter::class.java.simpleName + PlayerViewHolder::class.java.simpleName

        /**
         * bind items for single view of recyclerView
         */
        @SuppressLint("SetTextI18n")
        fun bind(player: Player) {
            Log.d(mTAG, "bindItems()")
            view.textView_teamPlayer_name.text =
                player.first_name + view.resources?.getString(R.string.str_space) + player.last_name
            view.textView_teamPlayer_position.text =
                view.resources?.getString(R.string.str_position) + view.resources?.getString(R.string.str_space) + player.position
            view.textView_teamPlayer_number.text =
                view.resources?.getString(R.string.str_number) + view.resources?.getString(R.string.str_space) + player.number.toString()
        }
    }
}