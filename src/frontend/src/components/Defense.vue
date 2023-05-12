<template>
  <v-row fluid justify="center">
    <div v-for="(position, index) in positions" :key="position.position">
      <v-col
        md="20"
        style="padding-bottom: 0px"
        v-if="!position.hidden || index === 3"
      >
        <Player
          :position="position"
          playersFilter="3"
          :class="{ invisible: position.hidden }"
        />
        <div class="container" v-if="index === 3">
          <div class="semiCircle"></div>
        </div>
      </v-col>
    </div>
  </v-row>
</template>

<script>
import Player from "./Player.vue";
import { restartView } from "@/mixins/mixinOverflow";
export default {
  mixins: [restartView],
  components: { Player },
  props: {
    formation: Number,
  },
  watch: {
    formation(novo, anterior) {
      this.displayDefenders(novo);
    },
  },
  methods: {
    displayDefenders(amount) {
      let filledPositions = this.restartView(7);

      let positionCounter = 0;
      if (amount === 5) {
        for (let i = 0; i < 7; i += 3) {
          this.positions[i].hidden = false;
          this.fillFilledPositions(filledPositions, positionCounter);
          positionCounter++;
          if (i === 3) {
            for (let j = -1; j < 2; j += 2)
              this.positions[i + j].hidden = false;
            this.fillFilledPositions(filledPositions, positionCounter);
            positionCounter++;
          }
        }
      } else {
        if (amount === 3) {
          for (let i = 2; i < 5; i++) {
            this.positions[i].hidden = false;
            this.fillFilledPositions(filledPositions, positionCounter);
            positionCounter++;
          }
        } else {
          for (let i = 1; i < 6; i++) {
            if (i === 3) continue;
            this.fillFilledPositions(filledPositions, positionCounter);
            positionCounter++;
            this.positions[i].hidden = false;
          }
        }
      }
    },
    fillFilledPositions(filledPositions, positionCounter) {
      if (filledPositions[positionCounter]) {
        this.positions[positionCounter].value =
          filledPositions[positionCounter].value;
        this.positions[positionCounter].image =
          filledPositions[positionCounter].image;
      }
    },
  },
  data: () => {
    return {
      players: [],
      positions: [
        {
          position: "ADE",
          hidden: false,
        },
        {
          position: "LE",
          hidden: false,
        },
        {
          position: "ZAG",
          hidden: false,
        },
        {
          position: "ZAG",
          hidden: false,
        },
        {
          position: "ZAG",
          hidden: false,
        },
        {
          position: "LD",
          hidden: false,
        },
        {
          position: "ADD",
          hidden: false,
        },
      ],
    };
  },
  mounted() {
    this.displayDefenders(this.formation);
  },
};
</script>

<style scoped>
.semiCircle {
  width: 3rem;
  height: 1.1rem;
  border: 5px solid #fff;
  border-radius: 10rem 10rem 0 0;
  border-bottom: none;
}

.container {
  visibility: visible !important;
  display: flex;
  justify-content: center;
  width: 100%;
  display: flex;
  position: relative;
  bottom: 0;
}
</style>
