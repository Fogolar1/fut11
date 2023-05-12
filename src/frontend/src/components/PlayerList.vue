<template>
  <v-card>
    <template v-slot:loader>
      <v-progress-linear
        color="dark-blue"
        indeterminate
        :loading="loading"
      ></v-progress-linear>
    </template>
    <v-text-field
      clearable
      label="Nome do jogador"
      @keyup="this.filterPlayers()"
      v-model="this.filterName"
      class="filter"
    ></v-text-field>
    <div
      class="playerItem"
      v-for="player in filter"
      :key="player.id"
      @click="this.setPlayerOnPosition(player)"
    >
      <v-row fluid style="border-bottom: 1px solid #000">
        <img
          v-bind:src="'data:image/png;base64,' + this.treatImage(player.image)"
          v-if="player.image"
          style="width: 206px; height: 170px"
        />
        <img src="@/assets/genericPlayer.png" style="width: 206px" v-else />
        <div>
          <h2>{{ player.name }}</h2>
          <div style="font-size: 2rem">
            <flag :iso="player.countryFlagCode" :squared="false" />
          </div>
          <img
            v-bind:src="
              'data:image/png;base64,' + this.treatImage(player.teamImage)
            "
            class="teamIcon"
          />
          <h3>{{ player.positionName }}</h3>
        </div>
      </v-row>
    </div>
  </v-card>
</template>

<script>
import axios from "axios";
export default {
  props: {
    playersFilter: String,
    position: Object,
  },
  data: () => {
    return {
      players: [],
      filter: [],
      filterName: "",
      loading: true,
    };
  },
  mounted() {
    axios
      .get("http://localhost:8080/players/list?position=" + this.playersFilter)
      .then((response) => {
        this.players = response.data;
        this.filter = this.players;
        this.loading = false;
      });
  },
  methods: {
    treatImage(image) {
      return atob(image);
    },
    setPlayerOnPosition(playerToSet) {
      this.position.value = playerToSet.webName;
      this.position.image = atob(playerToSet.teamImage);
      this.$emit("close");
    },
    filterPlayers() {
      this.filter = this.players.filter((player) => {
        return (
          player.name.toLowerCase().includes(this.filterName.toLowerCase()) ||
          player.webName.toLowerCase().includes(this.filterName.toLowerCase())
        );
      });
    },
  },
};
</script>

<style scoped>
.playerItem {
  margin-top: 20px;
}

.playerItem :hover {
  background-color: #ababab;
  cursor: pointer;
}

.teamIcon {
  height: 40px;
  width: 40px;
  aspect-ratio: 3/2;
  object-fit: contain;
}

img {
  aspect-ratio: 3/2;
  object-fit: contain;
}

.filter {
  max-height: 56px;
}
</style>
