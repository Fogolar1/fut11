/**
 * plugins/index.js
 *
 * Automatically included in `./src/main.js`
 */

// Plugins
import FlagIcons from "vue-flag-icon";
import router from "../router";
import vuetify from "./vuetify";
import { loadFonts } from "./webfontloader";

export function registerPlugins(app) {
  loadFonts();
  app.use(vuetify).use(router).use(FlagIcons);
}
