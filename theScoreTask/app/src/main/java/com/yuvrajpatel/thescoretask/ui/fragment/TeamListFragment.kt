package com.yuvrajpatel.thescoretask.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuvrajpatel.thescoretask.R
import com.yuvrajpatel.thescoretask.databinding.FragmentTeamsListBinding
import com.yuvrajpatel.thescoretask.model.Team
import com.yuvrajpatel.thescoretask.ui.OnItemClickInterface
import com.yuvrajpatel.thescoretask.ui.adapter.TeamsListRecyclerViewAdapter
import com.yuvrajpatel.thescoretask.ui.vm.SharedViewModel
import com.yuvrajpatel.thescoretask.utilities.Utility


class TeamListFragment : Fragment(),
    OnItemClickInterface {

    private val TAG = TeamListFragment::class.java.simpleName
    private lateinit var mBindingTeamsListFragment: FragmentTeamsListBinding

    private lateinit var mTeamsListRecyclerViewAdapter: TeamsListRecyclerViewAdapter
    private lateinit var mModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView()")
        // Inflate the layout for this fragment
        mBindingTeamsListFragment =
            DataBindingUtil.inflate(inflater, R.layout.fragment_teams_list, container, false)
        setHasOptionsMenu(true)
        return mBindingTeamsListFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated()")
        mModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        mModel.teamsList.observe(viewLifecycleOwner, Observer {
            setRecyclerViewTeams(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d(TAG, "onCreateOptionsMenu()")
        inflater.inflate(R.menu.menu_fragment_team_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "onOptionsItemSelected()")
        when (item.itemId) {
            R.id.sortlist_alphabetically -> {
                mModel.sortList(Utility.SORT.ALPHABETICALLY)
                return true
            }
            R.id.sortlist_by_wins -> {
                mModel.sortList(Utility.SORT.WINS)
                return true
            }
            R.id.sortlist_by_losses -> {
                mModel.sortList(Utility.SORT.LOSSES)
                return true
            }
        }
        return false
    }

    /**
     * set recyclerView
     * set layout manager and adapter for recyclerView
     */
    private fun setRecyclerViewTeams(listOfTeams: List<Team>) {

        Log.d(TAG, "setRecyclerViewTeams()")
        mBindingTeamsListFragment.recyclerViewTeamList.layoutManager =
            LinearLayoutManager(this.context)

        mTeamsListRecyclerViewAdapter = TeamsListRecyclerViewAdapter(listOfTeams)
        mBindingTeamsListFragment.recyclerViewTeamList.adapter = mTeamsListRecyclerViewAdapter
        mTeamsListRecyclerViewAdapter.notifyDataSetChanged()
        mTeamsListRecyclerViewAdapter.setItemClickListener(this)

    }

    override fun onItemClick(team: Team) {
        Log.d(TAG, "onItemClick() ${team.full_name}")
        mModel.setCurrentTeam(team)
    }
}