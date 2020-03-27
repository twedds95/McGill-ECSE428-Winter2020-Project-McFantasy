import axios from 'axios';
import Vue from 'vue';
import VuejsDialog from 'vuejs-dialog';
import VuejsDialogMixin from 'vuejs-dialog/dist/vuejs-dialog-mixin.min.js'; // only needed in custom components
import 'vuejs-dialog/dist/vuejs-dialog.min.css';

Vue.use(VuejsDialog);

// var img = require('../../static/mcfantasylogo.png')
var config = require('../../../config')
let frontendUrlConfig = function () {

    if (process.env.NODE_ENV === 'production') {
        return 'https://' + config.build.host + ':' + config.build.port
    }
    else {
        return 'http://' + config.dev.host + ':' + config.dev.port
    }
}
let backendUrlConfig = function () {
    if (process.env.NODE_ENV === 'production') {
        return 'https://' + config.build.backendHost + ':' + config.build.backendPort
    }
    else {
        return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort
    }
}

var frontendUrl = frontendUrlConfig()
var backendUrl = backendUrlConfig()

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

function TeamDto(teamID, name, wins, losses, ties, points, totalRating, user) {
    this.teamID = teamID;
    this.name = name;
    this.wins = wins;
    this.losses = losses;
    this.ties = ties;
    this.points = points;
    this.totalRating = totalRating;
    this.user = user;
    this.players = [];
}

function PlayerDto(position, name, jerseyNumber, rating, totalGoals, totalAssists, points) {
    this.position = position;
    this.name = name;
    this.jerseyNumber = jerseyNumber;
    this.rating = rating;
    this.totalGoals = totalGoals;
    this.totalAssists = totalAssists;
    this.points = points;
}

function LeagueDto(name, user) {
    this.name = name;
     this.user = user;
}

function AppUserDto(name, email, profilePicture){
    this.name = name
    this.email = email
    this.profilePicture = profilePicture
}

export default {
    name: 'home',
    data() {
        return {
            errorMessage: '',
            email: '',
            picture: '',
            name: '',
            teams: [],
            leagues: [],
            selectedTeam: '',
            selectedLeague: '',
            playersInTeam: [],
            teamsInLeague: []
        }
    },
    created: function () {
        var self = this;
        var email = window.sessionStorage.getItem("email");
        let params = {
            userEmail: email
        };
        AXIOS.get('/user/' + email).then(function (response) {
            self.name = response.data.name;
            self.email = response.data.email;
            self.picture = "data:image/png;base64, " + response.data.profilePicture;
        });
        AXIOS.get('/leaguesForUser/', { params: params })
            .then(response => {
                if (!response.data || response.data.length <= 0) return;
                this.leagues = response.data;
                console.log(response.data);
            })
            .catch(e => {
                e = e.response.data.message ? e.response.data.message : e;
                console.log(e);
                this.errorMessage = e;
            });
        AXIOS.get('/teamsForUser/', { params : params })
            .then(response => {
                if (!response.data || response.data.length <= 0) return;
                this.teams = response.data;
                console.log(response.data);
            })
            .catch(e => {
                e = e.response.data.message ? e.response.data.message : e;
                console.log(e);
                this.errorMessage = e;
            });
    },
    methods: {
        getStandings: function () {
            AXIOS.get('/leagueStandings/'.concat(this.selectedLeague))
                .then(response => {
                    if (!response.data || response.data.length <= 0) return;
                    this.teamsInLeague = response.data;
                })
                .catch(e => {
                    e = e.response.data.message ? e.response.data.message : e;
                    console.log(e);
                    this.errorMessage = e;
                });
        },

        getTeamPlayers: function () {
            let params = {
                userEmail: this.email
            };
            AXIOS.get('/playersOnTeam/'.concat(this.selectedTeam), { params : params })
                .then(response => {
                    if (!response.data || response.data.length <= 0) return;
                    this.playersInTeam = response.data;
                })
                .catch(e => {
                    e = e.response.data.message ? e.response.data.message : e;
                    console.log(e);
                    this.errorMessage = e;
                });

        },

        createLeagueRedirect: function () {
            this.$dialog
                .prompt({
                    title: "Create New League",
                    body: "Enter the League Name for Your New League",
                }, {
                    okText: 'Create League',
                    promptHelp: 'Type in the box below and click "[+:okText]"'
                })
                .then(dialog => {
                    // Triggered when proceed button is clicked
                    let params = {
                        user: this.email,
                    };
                    AXIOS.post('/newLeague/'.concat(dialog.data), {}, { params: params })
                        .then(response => {

                        })
                        .catch(e => {
                            e = e.response.data.message ? e.response.data.message : e;
                            this.errorMessage = e;
                            console.log(e);
                            this.$dialog.alert(this.errorMessage);
                        });
                })
                .catch(() => {
                    // Triggered when dialog is dismissed by user
                    console.log('Prompt dismissed');
                });
        },

        joinLeagueRedirect: function () {
            this.$router.push({ name: 'JoinLeague', params: { user: this.email } });
        },

        createTeamRedirect: function () {
            this.$dialog
                .prompt({
                    title: "Create New Team",
                    body: "Enter the Team Name for Your New Team",
                }, {
                    okText: 'Create Team',
                    promptHelp: 'Type in the box below and click "[+:okText]"'
                })
                .then(dialog => {
                    // Triggered when proceed button is clicked
                    let params = {
                        user: this.email
                    };
                    AXIOS.post('/newTeam/'.concat(dialog.data), {}, { params: params })
                        .then(response => {
                            console.log(response.data)
                            this.$router.push({ name: 'AddPlayers', params: { user: this.email, teamID: response.data.teamID } });
                        })
                        .catch(e => {
                            e = e.response.data.message ? e.response.data.message : e;
                            this.errorMessage = e;
                            console.log(e);
                            this.$dialog.alert(this.errorMessage);
                        });
                })
                .catch(() => {
                    // Triggered when dialog is dismissed by user
                    console.log('Prompt dismissed');
                });
        },

        addPlayersredirect: function () {
            let team = this.teams.find(x => x.name === this.selectedTeam);
            this.$router.push({ name: 'AddPlayers', params: { user: this.email, teamID: team.teamID } });
        }

    }

}
