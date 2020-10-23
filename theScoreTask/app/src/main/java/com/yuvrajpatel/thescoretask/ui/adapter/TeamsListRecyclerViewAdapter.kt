package com.yuvrajpatel.thescoretask.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yuvrajpatel.thescoretask.R
import com.yuvrajpatel.thescoretask.model.Team
import com.yuvrajpatel.thescoretask.ui.OnItemClickInterface
import kotlinx.android.synthetic.main.recyclerview_teams_list_item.view.*

class TeamsListRecyclerViewAdapter(private val teamList: List<Team>?) :
    RecyclerView.Adapter<TeamsListRecyclerViewAdapter.TeamViewHolder>() {

    private val mTAG = TeamsListRecyclerViewAdapter::class.java.simpleName
    private lateinit var mItemClickInterface: OnItemClickInterface

    /**
     *  This function is returning the view for each item in the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.recyclerview_teams_list_item, parent, false)
        return TeamViewHolder(listItem)
    }

    fun setItemClickListener(listener: OnItemClickInterface) {
        mItemClickInterface = listener
    }

    /**
     * Get size of the list
     * @return list size
     */
    override fun getItemCount(): Int {
        if (teamList != null)
            return teamList.size
        return 0
    }

    /**
     *  This function is binding the data on the list
     */
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        teamList?.get(position)?.let { holder.bind(it) }

        holder.itemView.constraintLayout_recyclerView_team_list_item.setOnClickListener {
            Log.d(mTAG, "single item (Team) selected")
            openTeamDetailFragment(it, teamList?.get(position))
        }
    }

    /**
     * open TeamDetail Fragment(Team Details)
     * @param view View
     * @param team selected team
     */
    private fun openTeamDetailFragment(view: View, team: Team?) {
        Log.d(mTAG, "openTeamDetailFragment()")
        mItemClickInterface.onItemClick(team!!)
        view.findNavController().navigate(R.id.action_teamListFragment_to_teamDetailFragment)
    }

    class TeamViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val mTAG =
            TeamsListRecyclerViewAdapter::class.java.simpleName + TeamViewHolder::class.java.simpleName

        /**
         * bind items for single view of recyclerView
         */
        @SuppressLint("SetTextI18n")
        fun bind(team: Team) {
            Log.d(mTAG, "bind()")
            view.textView_teamName.text = team.full_name
            view.textView_teamWins.text =
                view.resources.getString(R.string.str_wins) + view.resources?.getString(R.string.str_space) + team.wins
            view.textView_teamLosses.text =
                view.resources.getString(R.string.str_wins) + view.resources?.getString(R.string.str_space) + team.losses
        }
    }
}