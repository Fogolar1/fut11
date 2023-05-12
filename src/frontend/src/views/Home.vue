<template>
  <div class="mainContainer">
    <v-row fluid justify="center">
      <v-col md="2">
        <div class="title">
          <v-text-field
            variant="plain"
            append-icon="mdi-pencil"
            v-model="title"
          ></v-text-field>
        </div>
      </v-col>
    </v-row>
    <v-row fluid justify="center">
      <v-col md="11">
        <div class="campo">
          <v-card variant="tonal" rounded-sm>
            <v-card-title style="width: 10%">
              <h2 class="formationTitle">Formação</h2>
              <v-select
                v-model="formation"
                :items="formations"
                item-title="name"
                variant="underlined"
                class="selectFormations"
                return-object
              ></v-select>
            </v-card-title>
            <Forward :formation="getFormation.array[4]" />
            <Midfield :midFormation="getFormation.array.slice(1, 4)" />
            <Defense :formation="getFormation.array[0]" />
            <Goalkeeper />
          </v-card>
        </div>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Goalkeeper from "@/components/Golkeeper.vue";
import Defense from "@/components/Defense.vue";
import Midfield from "@/components/Midfield/Midfield.vue";
import Forward from "@/components/Forward.vue";
export default {
  components: {
    Goalkeeper,
    Defense,
    Midfield,
    Forward,
  },
  data: () => {
    return {
      formations: [
        {
          name: "4-3-3",
          array: [4, 0, 3, 0, 3],
        },
        {
          name: "4-3-3 (2)",
          array: [4, 1, 2, 0, 3],
        },
        {
          name: "4-3-3 (3)",
          array: [4, 2, 1, 0, 3],
        },
        {
          name: "4-3-3 (4)",
          array: [4, 0, 2, 1, 3],
        },
        {
          name: "4-4-2",
          array: [4, 0, 4, 0, 2],
        },
        {
          name: "4-1-2-1-2",
          array: [4, 1, 2, 1, 2],
        },
        {
          name: "4-2-2-2",
          array: [4, 2, 0, 2, 2],
        },
        {
          name: "4-2-3-1",
          array: [4, 2, 0, 3, 1],
        },
        {
          name: "3-4-3",
          array: [3, 0, 4, 0, 3],
        },
        {
          name: "3-5-2",
          array: [3, 0, 4, 1, 2],
        },
        {
          name: "3-1-4-2",
          array: [3, 1, 4, 0, 2],
        },
        {
          name: "3-4-2-1",
          array: [3, 0, 4, 0, 3],
        },
        {
          name: "5-2-1-2",
          array: [5, 0, 2, 1, 2],
        },
        {
          name: "5-2-2-1",
          array: [5, 0, 2, 0, 3],
        },
        {
          name: "5-4-1",
          array: [5, 0, 4, 0, 1],
        },
        {
          name: "5-1-2-2",
          array: [5, 1, 2, 0, 2],
        },
      ],
      formation: {
        name: "4-3-3",
        array: [4, 0, 3, 0, 3],
      },
      title: "Meu Time",
    };
  },
  computed: {
    getFormation() {
      return this.formation;
    },
  },
};
</script>

<style lang="css" scoped>
.v-text-field :deep(input) {
  font-size: 2.5rem;
}

.campo {
  background: linear-gradient(
    to bottom,
    #5d9634,
    #5d9634 50%,
    #538c2b 50%,
    #538c2b
  );
  background-size: 100% 200px;
}

.selectFormations,
.formationTitle,
.title {
  color: #fff;
  font-weight: bolder;
}

.mainContainer {
  opacity: 1;
}
</style>

<style>
body {
  background: url("../assets/backgroundBlackWhite.jpeg") no-repeat center center
    fixed;
  background-size: cover;
  content: " ";
  display: block;
  box-shadow: inset 0 0 0 1000px rgba(88, 88, 88, 0.8);
}

.invisible {
  visibility: hidden;
}
</style>
