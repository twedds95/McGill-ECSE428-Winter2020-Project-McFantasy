<template>
  <div class="addPlayers">
    <div>
      <h2>Manage Players on your Team</h2>
      <img v-bind:src="picture" width="200" height="200" />
      <br />
      <br />
      <select id="team-select" v-model="selectedTeam">
        <option disabled value>Select Team To Add Players</option>
        <option v-for="(team, i) in teams" v-bind:key="`team-${i}`">{{team.name}}</option>
      </select>
      <table id="playersInTeam-table" class="tableCentered">
        <tr>
          <th>Player</th>
          <th>Goals</th>
          <th>Assists</th>
          <th>Points</th>
          <th>Rating</th>
          <th>Position</th>
          <th></th>
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
          <td>
            <button id="addPlayer-button" v-on:click="removePlayer(player.name)">Remove</button>
          </td>
        </tr>
      </table>
      <table id="playersInTeam-table" class="tableCentered">
        <tr>
          <th>Player</th>
          <th>Goals</th>
          <th>Assists</th>
          <th>Points</th>
          <th>Rating</th>
          <th>Position</th>
          <th></th>
        </tr>

        <tr
          v-for="(player, i) in players"
          v-bind:id="player.name"
          v-bind:key="`player-${i}`"
        >
          <td v-bind:id="player.name">{{player.name}}</td>
          <td v-bind:id="player.name">{{player.totalGoals}}</td>
          <td v-bind:id="player.name">{{player.totalAssists}}</td>
          <td v-bind:id="player.name">{{player.points}}</td>
          <td v-bind:id="player.name">{{player.rating}}</td>
          <td v-bind:id="player.name">{{player.position}}</td>
          <td>
            <button id="addPlayer-button" v-on:click="addPlayer(player.name)">Add</button>
          </td>
        </tr>
      </table>
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

<script src="./javascript/addplayers.js"></script>
