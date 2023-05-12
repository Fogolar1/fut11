<template>
  <v-row fluid justify="center">
    <div v-for="position in positions" :key="position.position">
      <v-col md="20" v-if="!position.hidden">
        <Player :position="position" playersFilter="1" />
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
  methods: {
    displayForwards(amount) {
      let filledPositions = this.restartView(5);

      let positionCounter = 0;
      if (amount === 3 || amount === 2) {
        for (let i = 3 - amount; i < 5; i += 2) {
          if (filledPositions[positionCounter]) {
            this.positions[i].value = filledPositions[positionCounter].value;
            this.positions[i].image = filledPositions[positionCounter].image;
          }
          this.positions[i].hidden = false;
          positionCounter++;
        }
      } else if (amount === 1) {
        this.positions[2].hidden = false;
        if (filledPositions.length > 0) {
          this.positions[i].value = filledPositions[0].value;
          this.positions[i].image = filledPositions[0].image;
        }
      }
    },
  },
  watch: {
    formation(novo, anterior) {
      this.displayForwards(novo);
    },
  },
  data: () => {
    return {
      players: [],
      positions: [
        {
          position: "PE",
          hidden: true,
        },
        {
          position: "ATA",
          hidden: false,
        },
        {
          position: "ATA",
          hidden: false,
        },
        {
          position: "ATA",
          hidden: false,
        },
        {
          position: "PD",
          hidden: false,
        },
      ],
    };
  },
  mounted() {
    this.displayForwards(this.formation);
  },
};
</script>

<style></style>
