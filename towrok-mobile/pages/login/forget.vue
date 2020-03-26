<template>
	<view class="page">
		<view class="bgColor"></view>
		<view class="header">
			忘记密码
		</view>
		<view class="input-box">
			<view><text class="icon">&#xe602;</text><input class="username" type="text" confirm-type="next" placeholder="请输入用户名" placeholder-class="iplace"></view>
			<view><text class="icon">&#xe604;</text><input class="passwrod" type="text" :password="showPassword" confirm-type="done" placeholder="请输入密码" placeholder-class="iplace"><text class="icon icon-end" @click="shwoPwd">&#xe603;</text></view>
		</view>
		<view class="login">
			<text class="icon">&#xe601;</text>
		</view>
		<view class="other">
			<navigator class="left" url="./login" open-type="redirect">去登录</navigator>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				showPassword:true
			}
		},
		methods: {
			shwoPwd(){
				this.showPassword = !this.showPassword;
			}
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
		view{
			display: flex;
			margin: 60upx;
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
		margin-top: 50upx;
		text-align: center;
		color: #FFFFFF;
		font-size: $uni-font-size-lg;
	}
</style>

