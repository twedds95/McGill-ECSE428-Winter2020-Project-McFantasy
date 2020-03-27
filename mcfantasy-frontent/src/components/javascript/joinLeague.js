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
export default {
    name: 'joinleague',
    data() {
        return {
            errorMessage: '',
            email: '',
            picture: '',
            name: '',
            teams: [],
            leagues: [],
            selectedTeam: '',
            teamsInLeague: []
        }
    },
    created: function () {
        var self = this;
        var email = window.sessionStorage.getItem("email");
        AXIOS.get('/user/' + email).then(function (response) {
            self.name = response.data.name;
            self.email = response.data.email;
            self.teams = response.data.team;
            self.picture = "data:image/png;base64, " + response.data.profilePicture;
        });
        let params = {
            userEmail: email
        };
        AXIOS.get('/teamsForUser/', { params : params })
            .then(response => {
                if (!response.data || response.data.length <= 0) return;
                console.log(this.email);
                this.teams = response.data;
                console.log(this.teams);
            })
            .catch(e => {
                e = e.response.data.message ? e.response.data.message : e;
                console.log(e);
                this.errorMessage = e;
            });
    },
    methods: {

    }

}
