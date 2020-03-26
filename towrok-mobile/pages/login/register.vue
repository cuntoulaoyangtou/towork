<template>
	<view class="page">
		<view class="bgColor"></view>
		<view class="header">
			注册
		</view>
		<view class="input-box">
			<view><text class="icon">&#xe602;</text><input v-model="userinfo.phone" class="username" type="text" confirm-type="next" placeholder="请输入手机号" placeholder-class="iplace" @blur="vphone"></view>
			<view><view :animation="animationData" v-show="verifyphone" class="colorRed font-lg verify">手机号格式不正确</view></view>
			<view><text class="icon">&#xe604;</text><input v-model="userinfo.password" class="passwrod" type="text" :password="showPassword" confirm-type="done" placeholder="请输入密码" placeholder-class="iplace" @blur="vpassword"><text class="icon icon-end" @click="shwoPwd">&#xe603;</text></view>
			<view><view :animation="animationData" v-show="verifypassword" class="colorRed font-lg verify">密码8位以上</view></view>
		</view>
		<view class="login">
			<text class="icon" @click="doregister" >&#xe601;</text>
		</view>
		<view class="other">
			<navigator class="left" url="./login" open-type="redirect">去登录</navigator>
			<!-- <navigator class="right" url="./forget" open-type="redirect">忘记密码</navigator> -->
		</view>
		<mpopup  ref="mpopup" :isdistance="true"></mpopup>  
	</view>
</template>

<script>
	import {
	    mapMutations
	} from 'vuex';
	import mpopup from '../../components/xuan-popup/xuan-popup.vue'
	export default {
		components:{
			mpopup
		},
		data() {
			return {
				showPassword:true,
				userinfo:{
					phone:'',
					password:""
				},
				verifyphone:false,
				verifypassword:false,
				animationData: {},
				loading:true
			}
		},
		onShow() {
			  var animation = uni.createAnimation({
			                duration: 100,
			                timingFunction: 'linear',
			            })
			            this.animation = animation
		},
		methods: {
			shwoPwd(){
				this.showPassword = !this.showPassword;
			},
			doregister(){
				this.vphone();
				this.vpassword();
				let that = this;
				if(!this.verifyphone && !this.verifypassword){
					if(that.loading){
						that.loading = false;
						uni.showLoading({
							title:'正在注册',
						})
						const phone = this.userinfo.phone;
						const password = this.$mod.md5(this.userinfo.password);
						this.$req.post("/towork/api/user/doregister",{"phone":phone,"password":password}).then(res=>{
							if(res.data.code === "SUCCESS"){
								uni.setStorageSync("towork-token",res.data.data);
								//登录
								uni.hideLoading()
								that.$refs.mpopup.open({
									type:'success',
									content:'正在登录',
									timeout:2000,
								});
								that.$req.get("/towork/api/user/getuserinfo", {
									key: res.data.data
								}).then(res => {
									console.log(res);
									if (res.data.code === "SUCCESS") {
										that.login(res.data.data);
										if(that.initUserInfo(res.data.data)){
											setTimeout(function(){
												uni.switchTab({
													url: "/pages/userinfo/userinfo"
												})
											},2000)
										}
									}
								})
							}else{
								uni.hideLoading();
								that.$refs.mpopup.open({
									type:'err',
									content:res.data.message,
									timeout:2000,
								});
							}
							that.loading = true;
						}).catch(err=>{
							uni.hideLoading();
							that.$refs.mpopup.open({
								type:'warn',
								content:'注册失败',
								timeout:2000,
							});
							console.log(err);
							that.loading = true;
						})
						
					}
					
				}else{
					// 动画
					this.rotateAndScale()
				}
			},
			vphone(){
				if(!this.$mod.verifyPhone(this.userinfo.phone)){
					this.verifyphone = true;
				}else{
					this.verifyphone = false;
				}
			},
			vpassword(){
				if(!this.$mod.verifyPassword(this.userinfo.password)){
					this.verifypassword = true;
				}else{
					this.verifypassword = false;
				}
			},
			rotateAndScale() {
				// 定义动画内容
				let num = 10;
				let that = this;
				let set = setInterval(function(){
					that.animation.translateX(num%2==0?8:-8).step();
					// 导出动画数据传递给data层
					that.animationData = that.animation.export();
					num--;
					if(num<0){
						clearInterval(set);
					}
				},100)
			},
			initUserInfo(user){
				if(!user.face_id){
					uni.showLoading({
						title:"跳转人脸识别"
					});
					uni.redirectTo({
						url:"/pages/userinfo/face?flag=true",
						success() {
							uni.hideLoading();
						}
					})
					return false;
				}
				if(!user.username){
					uni.showLoading({
						title:"跳转信息编辑"
					})
					uni.redirectTo({
						url:"/pages/userinfo/userform",
						success() {
							uni.hideLoading();
						}
					})
					return false;
				}
				return true;
			},
			...mapMutations(['login'])
		}
	}
</script>

<style lang="scss">
	.header {
		color: #FFFFFF;
		font-size: 86upx;
		font-weight: bold;
		padding-top: 210upx;
		margin-left: 80upx;
		padding-bottom: 80upx;
	}

	.bgColor {
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		background-image: linear-gradient(to bottom, #226CDA, #4272DB);
		z-index: -1;
	}


	.input-box {
		.verify{
			margin: 0upx;
		}
		view{
			display: flex;
			margin: 20upx 60upx;
			height: 80upx;
			text{
				position: absolute;
				font-size: 52upx;
				color: #FFFFFF;
				line-height: 80upx;
				padding-left: 15upx;
			}
			.icon-end{
				right: 80upx;
			}
			input{
				border: 1upx solid #FFFFFF;
				padding:0 80upx;
				border-radius: 5upx;
				line-height: 80upx;
				width: 700upx;
				height: 80upx;
				caret-color: #FFFFFF;
				color: #FFFFFF;
			}
		}
	}

	.iplace {
		color: #CCCCCC;
	}
	.login{
		text-align: center;
		text{
			color: #FFFFFF;
			font-size: 100upx;
		}
	}
	.other{
		display: flex;
		justify-content: center;
		margin-top: 50upx;
		text-align: center;
		color: #FFFFFF;
		font-size: $uni-font-size-lg;
		// .left{
		// 	padding-right: 30upx;
		// 	border-right: 5upx solid #FFFFFF;
		// }
		// .right{
		// 	padding-left: 30upx;
		// }
	}
</style>

