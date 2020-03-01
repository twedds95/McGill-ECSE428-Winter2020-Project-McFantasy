<template>
  <div id="Signup">
    <div class="site-section site-hero inner">
      <div class="container-fluid" id="top-container">
        <div class="container text-center" id="img-container">
          <img
            src="..\assets\mcfantasylogo.png"
            width="300"
            height="300"
            class="img"
          />
        </div>
      </div>

      <br />
      <br />
      <div class="half left">
        <div class="container" align="center">
          <div class="row align-items-center" align="center">
            <div class="col-md-10" align="center" style="padding-left:10%">
              <h1 class="d-block mb-4 text-white">&nbsp; &nbsp; &nbsp; &nbsp; Sign Up</h1>
            </div>
          </div>
        </div>
      </div>

      <div class="stuff">  
        <table class="container-table">
            <tr class="item">
            <input class="item" type="text" placeholder="Name" v-model="name" />
            </tr>
            <tr class="item">
            <input class="item" type="text" placeholder="Username" v-model="username" />
            </tr>
            <input class="item" type="text" placeholder="Email" v-model="email" />
            <tr class="item">
            <input class="item" type="password" placeholder="Password" v-model="password" />
            </tr>
            <tr class="item">
                <input type="file" class="special"
                    id="pic" name="pic"
                    accept="image/png, image/jpeg, image/pdf"> 
            </tr>
            <tr class="item">
            <input
                @click="signup(name, username, userId, email, password)"
                type="submit"
                value="Sign Up"
                class="btn btn-diff py-2 px-4 text-white"
            />
            </tr>
            <tr class="item">
                <input
                    @click="login()"
                    type="submit"
                    value="Back to Login"
                    class="btn btn-diff py-2 px-4 text-white"
                />
            </tr>
            <br />
          <span v-if="errorSignup" style="color:red">Error: {{ errorSignup }}</span>
          <span v-if="response" style="color:green">Success: {{ response }}</span>
        </table>
      </div>
    </div>
  </div>
</template>

<style>
.img {
    padding: 10px;
    margin: 10px;
}

.btn {
    background-color: #FF0000;
    border: #FFFFFF;
    margin: 1px;
}

.stuff {
    background-color: #FFC72C;
    width: 30%;
    margin-left: auto;
    margin-right: auto;
}

.btn-diff {
    background-color: #FF0000;
    border: #FFFFFF;
  }
.btn-diff:hover {
    background-color: #ffcccb;
    border-color: #ffcccb;
}

.container-table {
    width: 40%;
    margin-left: auto;
    margin-right: auto;
    border-collapse: separate;

    item {
        padding: 15px;
        background-color: #000000;
    }

    special {
        background-color: #FFFFFF;
    }
}

</style>

<script src="./javascript/signup.js"></script>