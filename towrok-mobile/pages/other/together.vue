<template>
	<view>
		<view class="select">
			<view class="header">
				患者同乘查询
			</view>
			<view class="form content">
				<view>到达地点<input class="input-box" type="text" v-model="trip.arrive" placeholder="全部" /></view>
				<view>出行日期
					<view class="uni-list-cell">
						<view class="uni-list-cell-db">
							<picker mode="date" :value="trip.date" @change="bindDateChange">
								<view class="uni-input">{{trip.date||'全部'}}</view>
							</picker>
						</view>
					</view>
				</view>
				<view>车次<input class="input-box" type="text" v-model="trip.no" placeholder="全部" /></view>
				<view>乘坐的交通工具
					<view class="uni-list-cell">
						<view class="uni-list-cell-db">
							<picker mode="selector" :value="trip.type" :range="types" range-key="type" @change="bindChange">
								<view class="uni-input">{{types[trip.type].type}}</view>
							</picker>
						</view>
					</view>
				</view>
				<view><button @click="inquire(false)" type="primary">查询</button></view>
			</view>
			
		</view>

		<view class="data content">
			<view>
				<view v-for="(item,index) in datas" :key="index">
					<view class="data-card">
						<view class="header">
							<text class="date" v-text="new Date(item.date).format('MM-dd')"></text>
							<text class="type" v-text="item.type>4?'其他':types[item.type].type"></text>
							<text class="no" v-text="item.no"></text>
						</view>
						<view class="body">
							<view v-if="item.memo">
								<text>事件：<text v-text="item.memo"></text></text>
							</view>
							<view v-if="item.pos_start">
								<view>出发地：<text v-text="item.pos_start"></text></view>
								<view>到达：<text v-text="item.pos_end"></text></view>
							</view>
						</view>
						<view class="foot">
							<view><text>发布时间:</text><text>{{new Date(item.created_at).format("MM月dd日 hh:mm ")}}</text></view>
							<view><text>线索来源:</text><text class="colorBlue" v-text="item.who" @click="nav(item.source)"></text></view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="next-page" v-if="datas.length" @click="nextPage">下一页</view>
		<mpopup  ref="mpopup" :isdistance="true"></mpopup>  
	</view>
</template>

<script>
	import mpopup from '../../components/xuan-popup/xuan-popup.vue'
	export default {
		components:{
			mpopup
		},
		data() {
			return {
				trip: {
					arrive: '',
					date: "",
					no: '',
					type: 0,
					page: 1
				},
				datas: [],
				types: [{
						type: "全部"
					}, {
						type: "飞机"
					},
					{
						type: "火车"
					},
					{
						type: "地铁"
					},
					{
						type: "汽车"
					}
				]

			};
		},
		methods: {
			bindDateChange(e) {
				this.trip.date = e.detail.value;
			},
			bindChange(e) {
				this.trip.type = e.detail.value;
			},
			nav(url) {
				uni.navigateTo({
					url: `/pages/web/web?url=${url}`,
				})
			},
			inquire(val) {
				if (!val) {
					this.trip.page = 1;
				}
				console.log(this.trip.page);
				this.$req.get("/towork/api/epidemic/gettrip", this.trip).then(res => {
					console.log(res);
					this.datas.splice(0, this.datas.length);
					if (res.data.code === "SUCCESS") {
						if(res.data.data.code === 130){
							this.$refs.mpopup.open({
								type:'warn',
								content:'请勿频繁刷新',
								timeout:2000,
							});
						}else{
							res.data.data.newslist.forEach(item => {
								this.datas.push(item);
							})
						}
					}
				})
			},
			nextPage() {
				this.trip.page += 1;
				this.inquire(true);
				uni.createSelectorQuery().select(".select").boundingClientRect((res) => {
					let scrollTop = res.height;
					uni.pageScrollTo({
						duration: 300,
						scrollTop: scrollTop
					})
				}).exec()

			}
		}
	}
</script>

<style lang="scss">
	.select {
		.header {
			font-size: $uni-font-size-max;
			font-weight: bold;
			height: 200upx;
			padding-left: 60upx;
			line-height: 200upx;
			background-image: linear-gradient(to bottom, $uni-bg-color-main, #FFFFFF);
			color: #FFFFFF;

		}
	}

	.input-box {
		border: 1px solid $uni-border-color;
		height: 80upx;
		line-height: 80upx;
		padding: 0 10upx;
		margin: 20upx 0;
	}

	.uni-list-cell {
		border: 1px solid $uni-border-color;
		height: 80upx;
		line-height: 80upx;
		padding: 0 10upx;
		margin: 20upx 0;
	}

	.next-page {
		text-align: center;
		padding: 40upx;
	}

	.data {
		margin-top: 30upx;
		border-bottom: 1px solid $uni-border-color;

		.data-card {
			padding: 10upx;
			border-top: 1px solid $uni-border-color;

			.header {
				.date {
					font-weight: bold;
					padding-right: 20upx;
					border-right: 5upx solid #000;
				}

				.type {
					padding: 0 20upx;
				}
			}

			.foot {
				color: $uni-text-color-grey;

				view {
					display: flex;
					justify-content: space-between;
				}
			}
		}
	}
</style>
