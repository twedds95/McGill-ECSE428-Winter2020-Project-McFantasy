import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Signup from '@/components/Signup'
import Home from '@/components/Home'
import JoinLeague from '@/components/JoinLeague'
import AddPlayers from '@/components/AddPlayers'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/joinLeague/:user',
      name: 'JoinLeague',
      component: JoinLeague
    },
    {
      path: '/addPlayers/:user/:teamID',
      name: 'AddPlayers',
      component: AddPlayers
    },
  ]
})
