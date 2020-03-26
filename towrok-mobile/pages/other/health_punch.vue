<template>
	<view class="content">
		<view>
			<view>姓名：{{userInfo.username}}</view>
			<view>员工编号：{{userInfo.uno}}</view>
		</view>
		<view class="heat">
			<view>
				<text class="colorRed">*</text>今日体温
			</view>
			<view><text class="icon" @click="minish">&#xe607;</text> <text v-model="health.temperature" v-text="health.temperature"></text><text>度</text><text
				 class="icon" @click="addition">&#xe606;</text></view>
		</view>
		<view>
			<text class="colorRed">*</text>是否发烧
			<view class="radio-box">
				<radio-group class="radio-groups" @change="changeSw($event,'ishot')">
					<radio value="true" name="na" id="id">是</radio>
					<radio value="false" name="na" id="id">否</radio>
				</radio-group>
			</view>
		</view>
		<view>
			<text class="colorRed">*</text>是否咳嗽
			<view class="radio-box">
				<radio-group class="radio-groups" @change="changeSw($event,'iskeke')">
					<radio value="true">是</radio>
					<radio value="false">否</radio>
				</radio-group>
			</view>
		</view>
		<view>
			<text class="colorRed">*</text>是否到过武汉
			<view class="radio-box">
				<radio-group class="radio-groups" @change="changeSw($event,'towuhan')">
					<radio value="true">是</radio>
					<radio value="false">否</radio>
				</radio-group>
			</view>
		</view>
		<view>
			<text class="colorRed">*</text>是否途径过武汉
			<view class="radio-box">
				<radio-group class="radio-groups" @change="changeSw($event,'crosswh')">
					<radio value="true">是</radio>
					<radio value="false">否</radio>
				</radio-group>
			</view>
		</view>
		<view>
			<text class="colorRed">*</text>身边密切接触的人是否到过武汉或者途径武汉
			<view class="radio-box">
				<radio-group class="radio-groups" @change="changeSw($event,'firends_tor_cwh')">
					<radio value="true">是</radio>
					<radio value="false">否</radio>
				</radio-group>
			</view>
		</view>
		<view>
			<text class="colorRed">*</text>是否在企业所在地
			<view class="radio-box">
				<radio-group class="radio-groups" @change="changeSw($event,'incomadd')">
					<radio value="true">是</radio>
					<radio value="false">否</radio>
				</radio-group>
			</view>
		</view>
		<view>
			<text>相关协助</text>
			<textarea class="textarea" v-model="health.other"></textarea>
		</view>
		<view><button type="primary" @click="submit" :loading="loading">提交信息</button></view>
		<mpopup  ref="mpopup" :isdistance="true"></mpopup>  
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex';
	import mpopup from '../../components/xuan-popup/xuan-popup.vue'
	export default {
		computed: mapState([ 'hasLogin','userInfo']),
		components:{
			mpopup
		},
		data() {
			return {
				health: {
					uid:0,
					temperature: 36.5,
					ishot: null,
					iskeke: null,
					towuhan: null,
					crosswh: null,
					firends_tor_cwh: null,
					incomadd: null,
					other: '',
				},
				loading:false
			}
		},
		onLoad() {
			if(!this.hasLogin){
				uni.redirectTo({
					url:"/pages/login/login"
				})
			}
			this.health.uid = this.userInfo.uid
		}
		,
		methods: {
			//减小
			minish() {
				this.health.temperature = ((this.health.temperature * 10 - 1) / 10).toFixed(1);
			},
			//加加
			addition() {
				this.health.temperature = ((this.health.temperature * 10 + 1) / 10).toFixed(1);
			},
			changeSw(event, val) {
				switch (val) {
					case 'ishot':
						this.health.ishot = event.detail.value;
						break;
					case 'iskeke':
						this.health.iskeke = event.detail.value;
						break;
					case 'towuhan':
						this.health.towuhan = event.detail.value;
						break;
					case 'crosswh':
						this.health.crosswh = event.detail.value;
						break;
					case 'firends_tor_cwh':
						this.health.firends_tor_cwh = event.detail.value;
						break;
					case 'incomadd':
						this.health.incomadd = event.detail.value;
						break;
				}
			},
			submit(){
				if(!this.loading){
					this.loading = true;
					let that = this;
					if(this.verify()){
						this.$req.post("/towork/api/heath/sign",this.health).then(res=>{
							console.log(res);
							if(res.data.code === 'SUCCESS'){
								that.$refs.mpopup.open({
									type:'success',
									content:res.data.data,
									timeout:4000,
								});
								setTimeout(function(){
									uni.switchTab({
										url:"/pages/index/index"
									})
									that.loading = false;
								},4000)
							}else{
								that.$refs.mpopup.open({
									type:'warn',
									content:res.data.message,
									timeout:3000,
								});
							}
							
							that.loading = false;
						}).catch(err=>{
							that.$refs.mpopup.open({
								type:'warn',
								content:'网络超时',
								timeout:3000,
							});
							this.loading = false;
						})
						
					}else{
						this.loading = false;
					}
				}
			},
			verify(){
				console.log(this.health);
				if(this.health.ishot === null || this.health.iskeke === null || this.health.towuhan===null || this.health.crosswh===null || this.health.firends_tor_cwh===null || this.health.incomadd=== null){
					this.$refs.mpopup.open({
						type:'warn',
						content:'请填写完整信息',
						timeout:2000,
					});
					return false;
				}
				return true
			}
		}
	}
</script>

<style lang="scss">
	.content {
		padding: 20upx;
		font-size: $uni-font-size-lg;

		view {
			padding: 10upx 0;

			.colorRed {
				color: red;
			}

			.radio-groups {
				padding: 10upx 0;
				display: flex;
				justify-content: space-around;
			}
		}

		.heat {
			display: flex;
			justify-content: space-between;
		}

		.textarea {
			margin-top: 10upx;
			width: 100%;
			border: 1upx solid $uni-border-color;
			background-color: #EEEEEE;
		}
	}
</style>
