<template>
  <div class="home">
    <h2>Welcome to Your McFantasy Profile!</h2>
    <img v-bind:src="picture"
    width="200"
    height="200"
    />
  </div>
</template>

<script>

</script>

<style scoped>
h1, h2 {
  font-weight: normal;
  color: #FFC72C;
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

<script src="./javascript/home.js"></script>
