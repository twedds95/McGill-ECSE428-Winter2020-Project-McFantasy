import axios from 'axios'
//import forge from 'node-forge'
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
    name: 'signup',
    data() {
        return {
            email: '',
            name: '',
            userId: '',
            password: '',
            pic: '',
            errorSignup: '',
            response: '',
        }
    },
    created: function () {

    },
    methods: {
        login() {
            this.$router.push('/');
        },
        handleFile(){
            this.pic = this.$refs.pic.files[0];
        },
        signup(email, name, password) {
            var errorMsg = "";
            let params = {
                name: name,
                password: password,
            }
            let formData = new FormData();
            formData.append("picture", this.pic);

            AXIOS.post(`/user/` + email, formData, {headers: {'Content-Type': 'multipart/form-data'}, params: params})
                .then(response => {
                    // JSON responses are automatically parsed.
                    this.response = response.data
                    console.log(this.response)
                    if (this.response.name == null) {
                        this.errorSignup = 'Email is not valid!'
                        this.response = ''
                    } else {
                        this.response = 'User Created!'
                        this.errorSignup = ''
                    }
                    this.name = ''
                    this.email = ''
                    this.password = ''
                })
                .catch(e => {
                    errorMsg = e.message
                    console.log(errorMsg)
                    this.errorSignup = errorMsg
                    this.response = ''
                });

        }
    }
}
