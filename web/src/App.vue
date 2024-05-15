<template>
  <NavBar/>
  <router-view/>
</template>

<script>
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
// import $ from 'jquery'

export default {
  components: {
    NavBar,
  },
  setup() {

  }
}
</script>

<style>
body {
  background-image: url("./assets/bg-purple.jpg");
  /* background-image: url("./assets/bg-blue3.jpg"); */
  background-size: cover;
}
</style>

