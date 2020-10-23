package com.yuvrajpatel.thescoretask.utilities

import com.yuvrajpatel.thescoretask.model.Team

class Utility {

    enum class SORT {
        ALPHABETICALLY,
        WINS,
        LOSSES
    }

    /**
     * Sort team list alphabetically
     * @param currentTeamsList - current team list
     * @return alphabetically sorted team list
     */
    fun sortListAlphabetically(currentTeamsList: List<Team>): List<Team> {
        return currentTeamsList.sortedBy {
            it.full_name
        }
    }

    /**
     * Sort team list by wins
     * @param currentTeamsList - current team list
     * @return team list sorted by wins
     */
    fun sortListByWins(currentTeamsList: List<Team>): List<Team> {
        return currentTeamsList.sortedByDescending {
            it.wins
        }
    }

    /**
     * Sort team list by losses
     * @param currentTeamsList - current team list
     * @return team list sorted by losses
     */
    fun sortListByLosses(currentTeamsList: List<Team>): List<Team> {
        return currentTeamsList.sortedByDescending {
            it.losses
        }
    }
}