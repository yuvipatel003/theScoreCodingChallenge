package com.yuvrajpatel.thescoretask.ui.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuvrajpatel.thescoretask.model.Team
import com.yuvrajpatel.thescoretask.retrofit.ApiInterface
import com.yuvrajpatel.thescoretask.utilities.Utility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class SharedViewModel : ViewModel() {

    private val TAG = SharedViewModel::class.java.simpleName
    private val mTeamsList = MutableLiveData<List<Team>>()
    private val mCurrentTeam = MutableLiveData<Team>()

    val teamsList: LiveData<List<Team>>
        get() = mTeamsList

    val currentTeam: LiveData<Team>
        get() = mCurrentTeam

    init {
        Log.d(TAG, "ViewModel init block")
        getTeamsList()
    }

    /**
     * get list of teams
     * make a network call using ApiInterface(retrofit) through Co-routine
     */
    private fun getTeamsList() {
        Log.d(TAG, "getTeamsList()")
        val apiInterface = ApiInterface.create().getTeamList()

        // Network call using Co-routine
        CoroutineScope(Dispatchers.IO).launch {
            val data: Response<List<Team>> = apiInterface.execute()
            val listOfTeams = data.body()
            Log.d("Data : ", listOfTeams.toString())
            setTeamsList(listOfTeams)
        }
    }

    /**
     * Update teams list
     * updating teams list on main thread
     * as teamsList is not accessible on background thread
     * @param list - List of teams (from network call response)
     */
    private suspend fun setTeamsList(list: List<Team>?) {
        Log.d(TAG, "suspend setCommitsList()")
        withContext(Dispatchers.Main) {
            setList(list)
        }
    }

    /**
     * set teams list
     * @param list - List of teams
     */
    private fun setList(list: List<Team>?) {
        Log.d(TAG, "setList()")
        mTeamsList.value = list
    }

    /**
     * set current selected team for detail fragment
     * @param team - instance of Team
     */
    fun setCurrentTeam(team: Team) {
        Log.d(TAG, "setCurrentTeam()")
        mCurrentTeam.value = team
    }

    /**
     * Sort list and set sorted list in MutableList
     * @param sortBy - type of Sort (Alphabetically, By wins, By losses)
     */
    fun sortList(sortBy: Utility.SORT) {
        Log.d(TAG, "sortList()")
        val utility = Utility()
        when (sortBy) {
            Utility.SORT.ALPHABETICALLY -> setList(utility.sortListAlphabetically(teamsList.value!!.toList()))

            Utility.SORT.WINS -> setList(utility.sortListByWins(teamsList.value!!.toList()))
            Utility.SORT.LOSSES -> setList(utility.sortListByLosses(teamsList.value!!.toList()))
        }
    }

}