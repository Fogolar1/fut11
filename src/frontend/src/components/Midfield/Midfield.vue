<template>
  <div>
    <AttackingMidField
      :attMidsFormation="getMidFormation[2]"
      @overflow="getValuesFromOverflow"
      :parentOverFlow="getOverFlow[1]"
    />
    <v-row fluid justify="center" class="midfield">
      <div v-for="(position, index) in positions" :key="position">
        <v-col
          md="20"
          style="padding-top: 0px"
          v-if="!position.hidden || index === 2"
        >
          <div class="container" v-if="index === 2">
            <div class="semiCircle"></div>
            <Player
              :position="position"
              playersFilter="2"
              :class="{ invisible: position.hidden, middlePlayer: true }"
            />
          </div>
          <div v-else>
            <Player :position="position" playersFilter="2" />
          </div>
        </v-col>
      </div>
    </v-row>
    <DefensiveMidFielders
      :defMidsFormation="getMidFormation[0]"
      @overflow="getValuesFromOverflow"
      :parentOverFlow="getOverFlow[0]"
    />
  </div>
</template>

<script>
import Player from "../Player.vue";
import AttackingMidField from "./AttackingMidField.vue";
import DefensiveMidFielders from "./DefensiveMidFielders.vue";
import { restartView } from "@/mixins/mixinOverflow";
export default {
  mixins: [restartView],
  components: { Player, AttackingMidField, DefensiveMidFielders },
  props: {
    hasWingers: Boolean,
    midFormation: Array,
  },
  watch: {
    midFormation(novo, anterior) {
      this.displayMidfielders(novo[1]);
    },
  },
  data: () => {
    return {
      players: [],
      positions: [
        {
          position: "ME",
          hidden: false,
          value: "",
        },
        {
          position: "MC",
          hidden: false,
          value: "",
        },
        {
          position: "MC",
          hidden: false,
          value: "",
        },
        {
          position: "MC",
          hidden: false,
          value: "",
        },
        {
          position: "MD",
          hidden: false,
          value: "",
        },
      ],
      overflow: [{}, {}],
    };
  },
  methods: {
    displayMidfielders(amount) {
      this.overflow = [undefined, undefined];
      let filledPositions = this.restartView(5);

      if (amount === 1) {
        this.positions[2].hidden = false;
        if (filledPositions[0]) {
          this.positions[2].value = filledPositions[0].value;
          this.positions[2].image = filledPositions[0].image;
          filledPositions.shift();
        }
      } else if (amount !== 0) {
        for (let i = 0; i < 5; i++) {
          if (amount !== 4 && (i === 0 || i === 4)) continue;
          if (amount === 4 && i === 2) continue;
          if (amount === 2 && i === 2) continue;
          this.positions[i].hidden = false;
          if (filledPositions[0]) {
            this.positions[i].value = filledPositions[0].value;
            this.positions[i].image = filledPositions[0].image;
            filledPositions.shift();
          }
        }
      }
      if (filledPositions.length > 0) {
        this.handleOverflow(filledPositions);
      }
    },
    handleOverflow(overflow) {
      let remaining = new Array();
      let morePos = 0;
      if (this.midFormation[0] < this.midFormation[2]) {
        morePos = 1;
      }
      this.overflow[morePos] = overflow.slice(
        0,
        this.midFormation[morePos === 1 ? 2 : 0]
      );
      remaining.push(overflow.splice(0, this.midFormation[morePos]));
      if (remaining) {
        this.overflow[Math.abs(morePos - 2)];
      }
    },
    getValuesFromOverflow(value, image) {
      for (let i = 0; i < 5; i++) {
        if (!this.positions[i].value && !this.positions[i].hidden) {
          this.positions[i].image = image;
          this.positions[i].value = value;
          break;
        }
      }
    },
  },
  mounted() {
    this.displayMidfielders(this.midFormation[1]);
  },
  computed: {
    getMidFormation() {
      return this.midFormation;
    },
    getOverFlow() {
      return this.overflow;
    },
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
  border-bottom-left-radius: 484px;
  border-bottom-right-radius: 484px;
  border-top: none;
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
