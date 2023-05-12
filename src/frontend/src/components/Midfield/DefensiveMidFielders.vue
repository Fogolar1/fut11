<template>
  <v-row fluid justify="center">
    <div
      v-for="position in positions"
      :class="{ invisible: position.hidden }"
      :key="position.position"
    >
      <v-col md="20" :class="{ invisible: position.hidden }">
        <Player :position="position" playersFilter="2" />
      </v-col>
    </div>
  </v-row>
</template>

<script>
import Player from "../Player.vue";
import {
  checkOverflow,
  displayOverflow,
  restartView,
} from "@/mixins/mixinOverflow";
export default {
  mixins: [checkOverflow, displayOverflow, restartView],
  components: { Player },
  props: {
    defMidsFormation: Number,
    parentOverFlow: Object,
  },
  watch: {
    defMidsFormation(novo, anterior) {
      this.displayDefMidfielders(novo);
    },
    parentOverFlow(novo, anterior) {
      setTimeout(() => this.displayOverflow(novo), 20);
    },
  },
  methods: {
    displayDefMidfielders(amount) {
      let filledPositions = this.restartView(3);

      this.checkOverFlow(amount, filledPositions);
      for (let i = 0; i < amount; i++) {
        if ((amount === 2 && i === 1) || amount === 1) {
          i++;
        }
        this.positions[i].hidden = false;
      }
    },
  },
  data: () => {
    return {
      players: [],
      positions: [
        {
          position: "VOL",
          hidden: false,
        },
        {
          position: "VOL",
          hidden: false,
        },
        {
          position: "VOL",
          hidden: false,
        },
      ],
    };
  },
  mounted() {
    this.displayDefMidfielders(this.defMidsFormation);
  },
};
</script>

<style></style>
