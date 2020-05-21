import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const state = {
    purchaseGoodsList: []
}
const mutations = {
    putPurchaseList(state,payload){
        state.purchaseGoodsList = payload.list;
    }
}
export default new Vuex.Store({
    state,
    mutations
})