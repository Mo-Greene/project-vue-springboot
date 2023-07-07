import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import {loadFonts} from './plugins/webfontloader'
import { createPinia } from "pinia";
import vue3Cookie from 'vue3-cookies';

loadFonts()

const app = createApp(App)
app.use(createPinia())
app.use(vue3Cookie)
app.use(router)
app.use(vuetify)
app.mount('#app')
