<template>
  <div class="joinLeague">
    <div>
      <h2>Join a League</h2>
      <img v-bind:src="picture" width="200" height="200" />
      <br />
      <br />
      <select id="team-select" v-model="selectedTeam">
              <option disabled value>select Your Team to Join League</option>
              <option v-for="(team, i) in teams" v-bind:key="`team-${i}`">{{team.name}}</option>
            </select>
      <table id="joinLeague-table" class="tableCentered">
      <tr>
        <th>      Name        </th>
        <th>Number of Teams</th>
        <th>Manager</th>
        <th></th>
      </tr>

      <tr v-for="(league, i) in leagues" v-bind:id="league.name" v-bind:key="`league-${i}`">
        <td v-bind:id="league.name">{{league.name}}</td>
        <td v-bind:id="league.name">{{league.team.length}}</td>
        <td v-bind:id="league.name">{{league.user.name}}</td>
        <td>
          <button id="join-league-button" v-on:click="joinLeague(league.name)">Join</button>
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

<script src="./javascript/joinleague.js"></script>
