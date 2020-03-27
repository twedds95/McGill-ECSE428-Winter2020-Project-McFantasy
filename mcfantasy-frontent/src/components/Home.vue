<template>
  <div class="home">
    <div>
      <h2>Welcome to Your McFantasy Profile!</h2>
      <img v-bind:src="picture" width="200" height="200" />
      <br />
      <span
      id="home-error"
      v-if="errorMessage"
      style="color:red"
    >Error: {{errorMessage}}</span>
      <br />
    </div>
    <div class="row">
      <div class="col-md-5">
        <div>
          <h3>League</h3>
          <h4>
            <select id="league-select" @change="getStandings()" v-model="selectedLeague">
              <option disabled value>View Your League</option>
              <option v-for="(league, i) in leagues" v-bind:key="`league-${i}`">{{league.name}}</option>
            </select>
          </h4>
          <table id="teamsInLeague-table" class="tableCentered">
            <tr>
              <th>Team</th>
              <th>Points</th>
              <th>Wins</th>
              <th>Losses</th>
              <th>Ties</th>
              <th>Manager</th>
            </tr>

            <tr
              v-for="(teamInLeague, i) in teamsInLeague"
              v-bind:id="teamInLeague.teamID"
              v-bind:key="`teamInLeague-${i}`"
            >
              <td v-bind:id="teamInLeague.teamID">{{teamInLeague.name}}</td>
              <td v-bind:id="teamInLeague.teamID">{{teamInLeague.points}}</td>
              <td v-bind:id="teamInLeague.teamID">{{teamInLeague.wins}}</td>
              <td v-bind:id="teamInLeague.teamID">{{teamInLeague.losses}}</td>
              <td v-bind:id="teamInLeague.teamID">{{teamInLeague.ties}}</td>
              <td v-bind:id="teamInLeague.teamID">{{teamInLeague.user.name}}</td>
            </tr>
          </table>
          <div>
            <button id="createLeagueButton" @click="createLeagueRedirect()">Create New League</button>
            <button v-bind:disabled="this.teams.length===0" id="joinLeagueButton" @click="joinLeagueRedirect()">Join a League</button>
          </div>
        </div>
      </div>
      <div class="col-md-2">
        <div>
          <h3>Upcoming Games</h3>
          <!-- schedule of games in that league for next week -->
          <!-- full schedule for that team -->
        </div>
      </div>
      <div class="col-md-5">
        <div>
          <h3>Team</h3>
          <h4>
            <select id="team-select" @change="getTeamPlayers()" v-model="selectedTeam">
              <option disabled value>View Your Team</option>
              <option v-for="(team, i) in teams" v-bind:key="`team-${i}`">{{team.name}}</option>
            </select>
          </h4>
          <table id="playersInTeam-table" class="tableCentered">
            <tr>
              <th>Player</th>
              <th>Goals</th>
              <th>Assists</th>
              <th>Points</th>
              <th>Rating</th>
              <th>Position</th>
            </tr>

            <tr
              v-for="(playerInTeam, i) in playersInTeam"
              v-bind:id="playerInTeam.name"
              v-bind:key="`playerInTeam-${i}`"
            >
              <td v-bind:id="playerInTeam.name">{{playerInTeam.name}}</td>
              <td v-bind:id="playerInTeam.name">{{playerInTeam.totalGoals}}</td>
              <td v-bind:id="playerInTeam.name">{{playerInTeam.totalAssists}}</td>
              <td v-bind:id="playerInTeam.name">{{playerInTeam.points}}</td>
              <td v-bind:id="playerInTeam.name">{{playerInTeam.rating}}</td>
              <td v-bind:id="playerInTeam.name">{{playerInTeam.position}}</td>
            </tr>
          </table>
          <div>
            <button id="createTeamButton" @click="createTeamRedirect()">Create New Team</button>
            <button v-bind:disabled="selectedTeam==''" id="addPLayerToTeam" @click="addPlayersredirect()">Add Players</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
</script>


<style scoped>
h1,
h2 {
  font-weight: normal;
  color: #ffc72c;
}

.tableCentered {
  margin-left: auto;
  margin-right: auto;
  text-indent: 20px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>

<script src="./javascript/home.js"></script>
