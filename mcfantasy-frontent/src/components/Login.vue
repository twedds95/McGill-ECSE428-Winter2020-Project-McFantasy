<template class="back">
  <div id="login">
    <div class="site-section site-hero inner">
      <div class="container-fluid" id="top-container">
        <div class="container text-center" id="img-container">
          <img
            src="..\assets\mcfantasylogo.png"
            width="400"
            height="400"
            class="img"
          />
        </div>
      </div>

      <br />
      <br />
      <br />
      <br />
      <div class="half left">
        <div class="container" align="center">
          <div class="row align-items-center" align="center">
            <div class="col-md-0" align="center" style="padding-left:10%">
              <h1 class="d-block mb-0"></h1>
            </div>
          </div>
        </div>
      </div>

      <div class="half right" data-aos="fade-up">
        <div class="container" align="center">
          <div class="login-grid" style="padding-right:0%">
            <input class="login-text" type="text" placeholder="Email" v-model="email" />
            <input class="login-text" type="password" placeholder="Password" v-model="password" />
            <input
              @click="login(email, password)"
              type="submit"
              value="Login"
              class="btn btn-primary py-2 px-4 text-white"
            />
            <br />
            <span v-if="errorLogin" style="color:red">Error: {{errorLogin}}</span>
          </div>
        </div>
      </div>

      <br />
      <input
        @click="signup()"
        type="submit"
        value="Sign Up"
        class="btn btn-primary py-2 px-4 text-white"
      />
    </div>
  </div>
</template>

<style>
.back {
  background-color: #000000;
  color: #000000;
  width: 100%;
  min-height: 100%;
}

.img {
    padding: 10px;
}

.btn {
    background-color: #FF0000;
    border: #FFFFFF;
}

.btn-primary {
    background-color: #FF0000;
    border: #FFFFFF;
  }
  .btn-primary:hover {
    background-color: #FFC72C;
    border-color: #FFC72C;
  }
</style>

<script src="./javascript/login.js"></script>