<template>
  <v-row fluid justify="center">
    <div class="outline">
      <v-col md="20">
        <Player :position="positions" playersFilter="4" />
      </v-col>
    </div>
  </v-row>
</template>

<script>
import Player from "./Player.vue";
export default {
  components: { Player },
  data: () => {
    return {
      positions: {
        position: "GOL",
      },
    };
  },
};
</script>
<style scoped>
.outline {
  border: solid 5px #fff;
}
</style>
