<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@font-face {
		font-family: 'icon';
		src: url('~@/static/font/iconfont.eot');
		src: url('~@/static/font/iconfont.eot?#iefix') format('embedded-opentype'),
			url('~@/static/font/iconfont.woff2') format('woff2'),
			url('~@/static/font/iconfont.woff') format('woff'),
			url('~@/static/font/iconfont.ttf') format('truetype'),
			url('~@/static/font/iconfont.svg#towork') format('svg');
	}

	.icon {
		font-family: "icon" !important;
		font-size: 16px;
		font-style: normal;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
	}

	.content {
		padding: 20upx;
		font-size: $uni-font-size-lg;
	}

	.colorRed {
		color: red;
	}
	.colorBlue{
		color: #4F00FF;
	}

	.font-lg {
		font-size: $uni-font-size-lg;
	}
</style>
