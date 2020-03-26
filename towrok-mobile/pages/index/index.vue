<template>
	<view>
		<!-- #ifdef APP-PLUS -->
		<view class="status_bar">
			<view class="top_view"></view>
		</view>
		<!-- #endif -->
		<view class="header">
			<view class="company">华为云企业复工平台</view>
		</view>
		<view class="tab">
			<view @click="tohealth('/pages/userinfo/face')">
				<view class="icon health">&#xe605;</view>
				<view>健康打卡</view>
			</view>
			<view @click="tohealth('/pages/other/intimate')">
				<view class="icon touch">&#xe60c;</view>
				<view>密切接触</view>
			</view>
			<view @click="goto('https://voice.baidu.com/act/newpneumonia/newpneumonia')">
				<view class="icon ep">&#xe608;</view>
				<view>疫情数据</view>
			</view>
			<view @click="btn('/pages/other/together')">
				<view class="icon se">&#xe609;</view>
				<view>同程查询</view>
			</view>
		</view>
		<view class="desc">
			<view class="card-data">
				<view class="box">
					<view class="title f-title">国内确诊</view>
					<view class="number mainColor" v-text="desc.confirmedCount"></view>
					<view class="add-number">较上日<text v-text="desc.confirmedIncr||'待公布'" :class="desc.confirmedIncr?'mainColor':''"></text></view>
				</view>
				<view class="box">
					<view class="title">境外输入</view>
					<view class="number mainColor" v-text="desc.suspectedCount"></view>
					<view class="add-number">较上日<text v-text="desc.suspectedIncr||'待公布'" :class="desc.suspectedIncr?'mainColor':''"></text></view>
				</view>
				<view class="box">
					<view class="title">治愈</view>
					<view class="number cured" v-text="desc.curedCount"></view>
					<view class="add-number">较上日<text v-text="desc.curedIncr||'待公布'" :class="desc.curedIncr?'cured':''"></text></view>
				</view>
				<view class="box">
					<view class="title">死亡</view>
					<view class="number dead" v-text="desc.deadCount"></view>
					<view class="add-number">死亡<text v-text="desc.deadIncr||'待公布'" :class="desc.deadIncr?'dead':''"></text></view>
				</view>
			</view>
			<view class="card-data">
				<view class="box">
					<view class="title f-title">海外确诊</view>
					<view class="number mainColor" v-text="desc.foreignStatistics.confirmedCount"></view>
				</view>
				<view class="box">
					<view class="title">当前确诊</view>
					<view class="number mainColor" v-text="desc.foreignStatistics.currentConfirmedCount"></view>
				</view>
				<view class="box">
					<view class="title">治愈</view>
					<view class="number cured" v-text="desc.foreignStatistics.curedCount"></view>
				</view>
				<view class="box">
					<view class="title">死亡</view>
					<view class="number dead" v-text="desc.foreignStatistics.deadCount"></view>
				</view>
			</view>
			<view class="card-data">
				<view class="box">
					<view class="title f-title">全球确诊</view>
					<view class="number mainColor" v-text="desc.globalStatistics.confirmedCount"></view>
				</view>
				<view class="box">
					<view class="title">当前确诊</view>
					<view class="number mainColor" v-text="desc.globalStatistics.currentConfirmedCount"></view>
				</view>
				<view class="box">
					<view class="title">治愈</view>
					<view class="number cured" v-text="desc.globalStatistics.curedCount"></view>
				</view>
				<view class="box">
					<view class="title">死亡</view>
					<view class="number dead" v-text="desc.globalStatistics.deadCount"></view>
				</view>
			</view>
		</view>
		<view class="news">
			<view class="card-title">疫情最新动态</view>
			<view>
				<view v-for="(item,index) in news" :key="index" @click="goto(item.sourceUrl)" class="news-card">
					<view class="newsitem">
						<view class="title">{{item.title}}</view>
						<!-- <view class="summary">{{item.summary}}</view> -->
						<view class="news-bottom"><text class="pubDateStr">{{item.pubDateStr}}</text><text>来源：{{item.infoSource}}</text></view>
					</view>
				</view>
			</view>
		</view>
		<mpopup ref="mpopup" :isdistance="true"></mpopup>
	</view>
</template>

<script>
	import mpopup from '../../components/xuan-popup/xuan-popup.vue'
	import {
		mapState
	} from 'vuex';
	export default {
		computed: mapState(['hasLogin', 'userInfo']),
		components: {
			mpopup
		},
		data() {
			return {
				news: [],
				desc: {
					foreignStatistics: {},
					globalStatistics: {}
				},
				loading: false,
			}
		},
		onLoad() {
			this.getNews();
		},
		onPullDownRefresh() {
			//监听下拉刷新动作的执行方法，每次手动下拉刷新都会执行一次
			this.getNews();
			setTimeout(function() {
				uni.stopPullDownRefresh(); //停止下拉刷新动画
			}, 1000);
		},
		methods: {
			getNews() {
				this.news.splice(0, this.news.length)
				this.$req.get("/towork/api/epidemic/getnews").then(res => {
					if (res.data.code === 'SUCCESS') {
						if (res.data.data.newslist[0].news) {
							res.data.data.newslist[0].news.forEach(item => {
								this.news.push(item)
							})
						}
						this.desc = res.data.data.newslist[0].desc;
					}
				})
			},
			btn(url) {
				if (!this.loading) {
					this.loading = true;
					let that = this;
					uni.navigateTo({
						url: url,
						complete() {
							that.loading = false;
						}
					})
				}

			},
			tohealth(url) {
				if (!this.loading) {
					let that = this;
					if (this.hasLogin) {
						if (this.initUserInfo()) {
							uni.navigateTo({
								url: url,
								complete() {
									that.loading = false;
								}
							})
						}
					} else {
						this.loading = false;
						this.$refs.mpopup.open({
							type: 'warn',
							content: '请先登录',
							timeout: 2000,
						});
						setTimeout(function() {
							uni.navigateTo({
								url: "/pages/login/login",
								complete() {
									that.loading = false;
								}
							})
						}, 1500)
					}
				}
			},
			goto(url) {
				if (!this.loading) {
					this.loading = true;
					let that = this;
					uni.navigateTo({
						url: `../web/web?url=${url}`,
						complete() {
							that.loading = false;
						}
					})
				}
			},
			initUserInfo() {
				this.$req.get("/towork/api/user/getuserinfo", {
					key: uni.getStorageSync("towork-token")
				}).then(res => {
					if (res.data.code === "SUCCESS") {
						let user = res.data.data;
						if (!user.face_id) {
							uni.showLoading({
								title: "跳转人脸识别"
							});
							uni.navigateTo({
								url: "/pages/userinfo/face?flag=true",
								success() {
									uni.hideLoading();
								}
							})
							return false;
						}
						if (!user.username) {
							uni.showLoading({
								title: "跳转信息编辑"
							})
							uni.navigateTo({
								url: "/pages/userinfo/userform",
								success() {
									uni.hideLoading();
								}
							})
							return false;
						}
					}
				})

				return true;
			},
		}
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
		color: #FFFFFF;
		padding-top: 80upx;
		background-image: linear-gradient(to bottom, $uni-bg-color-main, #FFFFFF);

		.company {
			padding: 60upx;
			font-weight: bold;
			font-size: $uni-font-size-max;
		}
	}

	.tab {
		display: flex;
		justify-content: space-around;
		padding: 10upx 0;
		text-align: center;
		font-size: $uni-font-size-lg;

		.health {
			font-size: 100upx;
			color: #EA7503;
		}

		.touch {
			font-size: 100upx;
			color: #E8020F;
		}


		.ep {
			font-size: 100upx;
			color: #1B69FD;
		}

		.se {
			font-size: 100upx;
			color: #6499FE;
		}
	}

	.desc {
		margin: 25upx;

		.card-data {
			margin: 20upx 0;
			padding: 20upx 0;
			border-radius: 10upx;
			display: flex;
			justify-content: space-around;
			box-shadow: 3px 3px 3px 3px #F1F1F1, -3px 3px 3px 3px #F1F1F1;

			.box {
				text-align: center;

				.title {
					font-size: $uni-font-size-base;

				}

				.f-title {
					font-weight: bold;
				}

				.add-number {
					font-size: $uni-font-size-sm;

					text {
						color: $uni-text-color-grey;
					}

					.mainColor {
						color: $uni-text-color-main;
					}

					.cured {
						color: #30C7AE;
					}
				}

				.dead {
					color: $uni-text-color-grey;
				}

				.cured {
					color: #30C7AE;
				}

				.mainColor {
					color: $uni-text-color-main;
				}
			}
		}
	}

	.news {
		margin: 25upx;
		padding-top: 10upx;
		box-shadow: 3px 3px 3px 3px #F1F1F1, -3px 3px 3px 3px #F1F1F1;

		.card-title {
			color: #EF5008;
			font-weight: bold;
			line-height: 50upx;
			padding: 10upx;
		}

		.news-card {
			border-top: 1rpx solid $uni-border-color;

			.newsitem {
				padding: 10upx 10upx;

				.title {

					font-size: $uni-font-size-lg;

				}

				.summary {
					font-size: $uni-font-size-lg;
					color: $uni-text-color;

				}

				.news-bottom {
					font-size: $uni-font-size-base;
					padding-top: 20upx;
					display: flex;
					justify-content: space-between;
					color: $uni-text-color-grey;
				}
			}
		}

	}
</style>
