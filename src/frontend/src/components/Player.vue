<template>
  <v-btn class="playerButton" width="220">
    <template v-slot:prepend v-if="!this.position.value">
      <v-icon icon="mdi-plus-circle"></v-icon>
    </template>
    <template v-slot:prepend v-else>
      <img
        v-bind:src="'data:image/png;base64,' + this.position.image"
        class="teamIcon"
      />
    </template>

    {{ this.position.value ? this.position.value : this.position.position }}
    <v-dialog
      v-model="dialog"
      activator="parent"
      class="selectorScreen"
      fullscreen
    >
      <v-toolbar dark color="primary">
        <v-btn icon dark @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>Jogadores</v-toolbar-title>
      </v-toolbar>
      <player-list
        :playersFilter="this.playersFilter"
        :position="this.position"
        @close="this.dialog = false"
      />
    </v-dialog>
  </v-btn>
</template>

<script>
import PlayerList from "./PlayerList.vue";
export default {
  components: { PlayerList },
  watch: {
    jogador(novo, anterior) {
      if (novo === null) {
        this.position.value = "";
        this.position.image = "";
      } else {
        let newPlayer = this.players.find((player) => player.id === novo);
        this.position.value = newPlayer.webName;
        this.position.image = atob(newPlayer.teamImage);
      }
    },
  },
  data: () => {
    return {
      jogador: "",
      dialog: false,
    };
  },
  props: {
    position: Object,
    playersFilter: String,
  },
};
</script>

<style scoped>
.playerButton {
  font-size: 1.2rem;

  margin-top: 30px;
  margin-bottom: 20px;
  margin-left: 40px;
  margin-right: 40px;
}

.selectorScreen {
  width: 100%;
}

.autoComplete {
  width: 100px;
}
.teamIcon {
  height: 24px;
  width: 24px;
  aspect-ratio: 3/2;
  object-fit: contain;
}
</style>
