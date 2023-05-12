<template>
  <v-row fluid justify="center">
    <div
      v-for="(position, index) in positions"
      :class="{ invisible: position.hidden }"
      :key="position.position"
    >
      <v-col
        md="20"
        :class="{ invisible: position.hidden }"
        style="padding-bottom: 0px"
      >
        <div class="container" v-if="index === 1">
          <div class="semiCircle"></div>
          <Player
            :position="position"
            playersFilter="2"
            :class="{ invisible: position.hidden, middlePlayer: true }"
          />
        </div>
        <div v-else><Player :position="position" playersFilter="2" /></div>
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
    attMidsFormation: Number,
    parentOverFlow: Object,
  },
  data: () => {
    return {
      positions: [
        {
          position: "MEI",
          hidden: false,
        },
        {
          position: "MEI",
          hidden: false,
        },
        {
          position: "MEI",
          hidden: false,
        },
      ],
    };
  },
  watch: {
    attMidsFormation(novo, anterior) {
      this.displayAttMidfielders(novo);
    },
    parentOverFlow(novo, anterior) {
      setTimeout(() => this.displayOverflow(novo), 20);
    },
  },
  methods: {
    displayAttMidfielders(amount) {
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
  mounted() {
    this.displayAttMidfielders(this.attMidsFormation);
  },
};
</script>

<style scoped>
.midfield {
  border-top: 5px solid #fff;
}

.semiCircle {
  width: 242px;
  height: 120px;
  border: 5px solid #fff;
  border-top-left-radius: 484px;
  border-top-right-radius: 484px;
  border-bottom: none;
}

.container {
  visibility: visible !important;
  display: flex;
  justify-content: center;
  width: 100%;
}

.middlePlayer {
  position: absolute;
}
</style>
