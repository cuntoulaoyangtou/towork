<template>
	<view class="content">

		<view><text class="colorRed">*</text>姓名<input type="text" v-model="user.username" class="input-box" maxlength="20"
			 placeholder="真实姓名" required placeholder-class="iplace" /></view>
		<view><text class="colorRed">*</text>工号<input type="text" v-model="user.uno" class="input-box" placeholder="员工工号"
			 required placeholder-class="iplace" /></view>
		<view><text class="colorRed">*</text>年龄 <input type="number" v-model="user.age" class="input-box" placeholder="年龄"
			 required placeholder-class="iplace" /></view>
		<view><text class="colorRed">*</text>性别
			<radio-group class="radio-box" v-model="user.sex" @change="changeRadio" key="sex" id="sex">
				<radio value="true" color="#226CDA" :checked="user.sex===true">男</radio>
				<radio value="false" :checked="user.sex===false">女</radio>
			</radio-group>
		</view>
		<view><text class="colorRed">*</text>家庭住址<input type="text" v-model="user.address" class="input-box" required
			 placeholder="详细住址" placeholder-class="iplace" /></view>
		<view><text class="colorRed">*</text>所在车间<input type="text" v-model="user.department" class="input-box" required
			 placeholder="所在车间" placeholder-class="iplace" /></view>
		<view><text class="colorRed">*</text>所在班次<input type="text" v-model="user.grade" class="input-box" required
			 placeholder="所在班次" placeholder-class="iplace" /></view>
		<view><button type="primary" class="btn" @click="submit" :loading="loading">提交</button></view>
		<mpopup ref="mpopup" :isdistance="true"></mpopup>
	</view>
</template>

<script>
	import {
		mapState,
		mapMutations
	} from 'vuex';
	import mpopup from '../../components/xuan-popup/xuan-popup.vue'
	export default {
		computed: mapState(['hasLogin', 'userInfo']),
		components: {
			mpopup
		},
		data() {
			return {
				user: {},
				loading: false
			}
		},
		onLoad() {
			if (!this.hasLogin) {
				uni.redirectTo({
					url: "/pages/login/login"
				})
			}
		},
		methods: {
			...mapMutations(['login']),
			changeRadio(val) {
				this.user.sex = val.detail.value;
			},
			submit() {

				if (this.required(this.user)) {
					//提交给后台
					this.loading = true;

					let that = this;
					this.$req.post("/towork/api/user/change", this.user).then(res => {
						if (res.data.code === 'SUCCESS') {
							that.login(res.data.data)
							uni.switchTab({
								url: "/pages/index/index"
							})
						}else{
							that.$refs.mpopup.open({
								type: 'warn',
								content: res.data.message,
								timeout: 2000,
							});
						}
						that.loading = false;
					}).catch(err => {
						that.$refs.mpopup.open({
							type: 'warn',
							content: '网络超时',
							timeout: 2000,
						});
						that.loading = false;
					})
				} else {
					this.loading = false;
				}
			},
			required(user) {
				let msg = "";
				if (!user.username) {
					msg = "请填写用户名"
				} else if (!user.uno) {
					msg = "请填写工号"
				} else if (!user.age) {
					msg = "请填写年龄"
				} else if (!user.sex) {
					msg = "请填写性别"
				} else if (!user.address) {
					msg = "请填写家庭住址"
				} else if (!user.department) {
					msg = "请填写所在车间"
				} else if (!user.grade) {
					msg = "请填写所在班次"
				} else {
					return true;
				}
				this.$refs.mpopup.open({
					type: 'warn',
					content: msg,
					timeout: 2000,
				});
				return false;
			}
		}
	}
</script>

<style lang="scss">
	.content {

		.bgColor {
			position: fixed;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			background-image: linear-gradient(to bottom, #226CDA, #4272DB);
			z-index: -1;
		}

		.iplace {
			color: #CCCCCC;
		}

		.input-box {
			line-height: 60upx;
			height: 60upx;
			margin: 20upx;
			border: 1px solid $uni-border-color;
			padding: 0 20upx;
		}

		.btn {
			height: 60upx;
			line-height: 60upx;
			margin: 50upx 20upx 0;
			background-color: $uni-color-primary;
		}

		.radio-box {
			margin: 20upx;

			radio {
				padding: 0 50upx;
			}
		}
	}
</style>
