import axios from 'axios'
//import forge from 'node-forge'
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
    name: 'home',
    data() {
        return {
            email: '',
            picture: ''
        }
    },
    created: function() {
        var self = this;
        var email = window.sessionStorage.getItem("email");
        AXIOS.get('/user/'+email).then(function(response) {
            self.picture = "data:image/png;base64, " + response.data.profilePicture;
        });
    },
    methods: {
        
    }
}
