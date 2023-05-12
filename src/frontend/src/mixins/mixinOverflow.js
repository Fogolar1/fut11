const displayOverflow = {
  methods: {
    displayOverflow(overflow) {
      let j = 0;
      for (let i = 0; i < 3; i++) {
        if (!this.positions[i].value && !this.positions[i].hidden) {
          this.positions[i].image = overflow[j].image;
          this.positions[i].value = overflow[j].value;
          j++;
          if (!overflow[j]) break;
        }
      }
    },
  },
};

const checkOverflow = {
  methods: {
    checkOverFlow(amount, filledPositions) {
      if (filledPositions.length > amount) {
        for (let i = amount; i < filledPositions.length; i++) {
          this.$emit(
            "overflow",
            filledPositions[i].value,
            filledPositions[i].image
          );
        }
      }
    },
  },
};

const restartView = {
  methods: {
    restartView(max) {
      let filledPositions = new Array();

      for (let i = 0; i < max; i++) {
        if (this.positions[i].value)
          filledPositions.push(Object.assign({}, this.positions[i]));
        this.positions[i].hidden = true;
        this.positions[i].value = "";
        this.positions[i].image = "";
      }
      return filledPositions;
    },
  },
};

export { displayOverflow, checkOverflow, restartView };
