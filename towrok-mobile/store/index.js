import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		userInfo: {},
		hasLogin: false
	},
	mutations: {
		login(state, provider) {
			state.hasLogin = true
			state.userInfo = provider
			/* state.uerInfo.username = provider.username
			state.uerInfo.phone = provider.phone
			state.uerInfo.uno = provider.uno; //工号
			state.uerInfo.age = provider.age;//年龄
			state.uerInfo.sex = provider.sex
			state.uerInfo.address = provider.address
			state.uerInfo.department = provider.department
			state.uerInfo.grade = provider.grade
			state.uerInfo.password = provider.password
			state.uerInfo.avatar = provider.avatar
			state.uerInfo.face_id = provider.face_id */
			
			
			
			uni.setStorage({
				key: 'userInfo',
				data: provider
			})
		},
		logout(state) {
			state.hasLogin = false
			state.uerInfo = {}
			uni.removeStorage({
				key: 'userInfo'
			});
		}
	}
})

export default store
