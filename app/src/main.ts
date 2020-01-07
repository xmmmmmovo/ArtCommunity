import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import vuetify from './plugins/vuetify'
import store from '@/store'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import Vuetify from 'vuetify/lib'
import Vuelidate from 'vuelidate'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI)
Vue.use(Vuelidate)
Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App),
}).$mount('#app')
