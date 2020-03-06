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
    name: 'login',
    data() {
        return {
            email: '',
            password: '',
            errorLogin: '',
            response: '',
        }
    },
    methods: {
        signup() {
            this.$router.push('SignUp');
        },
        login(email, password) {
            var errorMsg = "";
            if (email == '') {
                errorMsg = "Invalid email"
                //alert("a");
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            if (password == '') {
                errorMsg = "Invalid password"
                console.log(errorMsg)
                this.errorLogin = errorMsg
                return
            }
            let params = {
                password: password
            }
            AXIOS.post(`/login/` + email, {}, {params: params})
                .then(response => {
                    console.log(this.response)
                    // JSON responses are automatically parsed.
                    this.response = response.data
                    window.sessionStorage.setItem("email", email)
                    this.errorLogin = ''
                    // PROBLEMS HERE
                    if (response.status !== 200) {
                        this.errorLogin = response.data
                        console.log(this.response)
                    } else {
                        this.$router.push('Home');
                    }
                })
                .catch(e => {
                    errorMsg = e.message
                    console.log(errorMsg)
                    this.errorLogin = errorMsg
                });
        }
    }
}