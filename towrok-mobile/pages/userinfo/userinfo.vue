<template>
	<view>
		<!-- #ifdef APP-PLUS -->
		<view class="status_bar">
			<view class="top_view"></view>
		</view>
		<!-- #endif -->
		<view class="header" @click="tologin">
			<view class="avatar">
				<image :src="userInfo.avatar" v-if="userInfo.avatar"></image>
				<text class="icon" v-else>&#xe60a;</text>
			</view>
			<view class="nickname">
				<text v-text="userInfo.username||(hasLogin?'请填写个人资料':'去登录')">
				</text>
			</view>
		</view>
		<view class="content">
			<view class="getuserinfo">
				<view class="title">个人信息</view>
				<view class="body">
					<view>工号:<text v-text="userInfo.uno"></text></view>
					<view>年龄:<text v-text="userInfo.age"></text></view>
					<view>性别:<text>{{getSex(userInfo.sex)}}</text></view>
					<view>手机号:<text v-text="userInfo.phone"></text></view>
					<view>所在车间:<text v-text="userInfo.department"></text></view>
					<view>所在班次:<text v-text="userInfo.grade"></text></view>
					<view>家庭住址:<text v-text="userInfo.address"></text></view>
				</view>
			</view>
			<view class="bottom">
				<view @click="clear"><button>清除缓存</button></view>
				<view v-if="hasLogin"><button type="warn" @click="quit">退出登录</button></view>
			</view>
			<mpopup  ref="mpopup" :isdistance="true"></mpopup>  
		</view>
	</view>
	
</template>

<script>
	import {
		mapState,
		mapMutations
	} from 'vuex';
	import mpopup from '../../components/xuan-popup/xuan-popup.vue'
	export default {
		computed: mapState([ 'hasLogin','userInfo']),
		components:{
			mpopup
		},
		data() {
			return {
			}
		},
		methods: {
			...mapMutations(['logout']),
			getSex(sex){
				console.log(sex);
				if(sex === true){
					return '男'
				}else if(sex === false){
					return '女'
				}else{
					return ''
				}
			},
			tologin() {
				if (!this.hasLogin) {
					uni.navigateTo({
						url: "/pages/login/login"
					})
				} else {
					this.initUserInfo(this.userInfo);
				}
			},
			quit() {
				if(this.hasLogin){
					this.logout()
					this.$req.get("/towork/api/user/logout",{key:uni.getStorageSync("towork-token")})
					uni.navigateTo({
						url: "/pages/login/login"
					})
				}
			},
			initUserInfo(user){
				if(!user.face_id|''){
					uni.showLoading({
						title:"跳转人脸识别"
					});
					uni.navigateTo({
						url:"/pages/userinfo/face?flag=true",
						success() {
							uni.hideLoading();
						}
					})
					return false;
				}
				if(!user.username|''){
					uni.showLoading({
						title:"跳转信息编辑"
					})
					uni.navigateTo({
						url:"/pages/userinfo/userform",
						success() {
							uni.hideLoading();
						}
					})
					return false;
				}
				return true;
			},
			clear() {
				var self = this;
				plus.cache.calculate(function(size) { //size是多少个字节单位是b
					//你可以做下面相应的处理
					if (size < 1024) {
						self.xxx = size + 'B';
					} else if (size / 1024 >= 1 && size / 1024 / 1024 < 1) {
						self.xxx = Math.floor(size / 1024 * 100) / 100 + 'KB';
					} else if (size / 1024 / 1024 >= 1) {
						self.xxx = Math.floor(size / 1024 / 1024 * 100) / 100 + 'M';
					}
					//可以询问用户是否删除
					uni.showModal({
						title: '提示',
						content: '确定清除缓存吗?',
						success(res) {
							// 用户确定要删除
							if (res.confirm) {
								//使用plus.cache.clear 清除应用中的缓存数据 这里清除后还要二十几KB没有清除，达不到全部清除
								plus.cache.clear(function() {
									uni.showToast({
										title: '清除成功',
										icon: 'none',
										success() {
											//成功后处理
										}
									})
								});
							}
						}
					})
				});
			}
		},
	}
</script>

<style lang="scss">
	.status_bar {
		height: var(--status-bar-height);
		width: 100%;
		background-color: $uni-bg-color-main;
	}
	
	.top_view {
		height: var(--status-bar-height);
		width: 100%;
		position: fixed;
		background-color: $uni-bg-color-main;
		top: 0;
		z-index: 999;
	}
	.header {
		background-image: linear-gradient(to bottom, $uni-bg-color-main, #FFFFFF);
		
		display: flex;
		padding: 100upx 50upx 50upx 50upx;
	
		.avatar {
			image {
				height: 160upx;
				width: 160upx;
				border-radius: 50%;
			}
	
			text {
				font-size: 160upx;
				color: $uni-text-color-grey;
				background-color: #FFFFFF;
				border-radius: 50%;
			}
		}
	
		.nickname {
			text {
				line-height: 160upx;
				padding-left: 50upx;
				color: #FFFFFF;
				font-weight: 600;
				font-size: $uni-font-size-title;
			}
	
		}
	}
	.content {


		.getuserinfo {
			box-shadow: 3px 3px 3px 3px #F1F1F1, -3px 3px 3px 3px #F1F1F1;
			.title{
				padding: 10upx;
				font-weight: bold;
			}
			.body{
				padding: 20upx;

				view{
					padding: 10upx 0;
					display: flex;
					justify-content: space-between;
				}
			}
		}
		.bottom{
			padding-top: 20upx;
			view{
				padding: 10upx 0 ;
			}
		}
	}
</style>
