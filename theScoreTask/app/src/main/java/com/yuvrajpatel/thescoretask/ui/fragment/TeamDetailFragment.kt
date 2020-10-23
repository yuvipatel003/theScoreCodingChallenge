package com.yuvrajpatel.thescoretask.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuvrajpatel.thescoretask.R
import com.yuvrajpatel.thescoretask.databinding.FragmentTeamDetailsBinding
import com.yuvrajpatel.thescoretask.model.Team
import com.yuvrajpatel.thescoretask.ui.adapter.TeamPlayersRecyclerViewAdapter
import com.yuvrajpatel.thescoretask.ui.vm.SharedViewModel

class TeamDetailFragment : Fragment() {

    private val mTAG = TeamDetailFragment::class.java.simpleName
    private lateinit var mBindingTeamDetailsFragment: FragmentTeamDetailsBinding

    private lateinit var mTeamPlayersRecyclerViewAdapter: TeamPlayersRecyclerViewAdapter
    private lateinit var mModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(mTAG, "onCreateView()")
        // Inflate the layout for this fragment
        mBindingTeamDetailsFragment = DataBindingUtil.inflate(
            inflater, R.layout.fragment_team_details,
            container, false
        )
        return mBindingTeamDetailsFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(mTAG, "onViewCreated()")
        mModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        mModel.currentTeam.observe(viewLifecycleOwner, Observer {
            setRecyclerViewTeamPlayers(it)
        })
    }


    /**
     * set recyclerView
     * set layout manager and adapter for recyclerView
     */
    @SuppressLint("SetTextI18n")
    private fun setRecyclerViewTeamPlayers(team: Team) {

        mBindingTeamDetailsFragment.textViewTeamName.text = team.full_name
        mBindingTeamDetailsFragment.textViewTeamWins.text =
            view?.resources?.getString(R.string.str_wins) + team.wins
        mBindingTeamDetailsFragment.textViewTeamLosses.text =
            view?.resources?.getString(R.string.str_wins) + team.losses

        // Set RecyclerViewDetails
        Log.d(mTAG, "setRecyclerViewTeamPlayers()")
        mBindingTeamDetailsFragment.recyclerViewTeamDetailPlayers.layoutManager =
            LinearLayoutManager(this.context)

        mTeamPlayersRecyclerViewAdapter = TeamPlayersRecyclerViewAdapter(team.players)
        mBindingTeamDetailsFragment.recyclerViewTeamDetailPlayers.adapter =
            mTeamPlayersRecyclerViewAdapter
        mTeamPlayersRecyclerViewAdapter.notifyDataSetChanged()
    }
}