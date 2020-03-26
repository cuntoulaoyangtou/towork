<template>
	<view class="content">
		<view class="header">
			<view>今日密切接触人员填写</view>
		</view>
		<view>
			<view>
				<text><text class="colorRed">*</text> 密切接触人员</text>
				<textarea class="textarea" v-model="today.names" placeholder="张三 李四"></textarea>
			</view>
			<view><button type="primary" @click="submit" :loading="loading">提交信息</button></view>
		</view>
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
				today: {
					uid:0,
					names: '',
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
			this.today.uid = this.userInfo.uid
		},
		methods: {
		
			submit(){
				console.log("哈哈哈哈哈哈");
				if(!this.loading){
					this.loading = true;
					let that = this;
					
					if(this.today.names){
						this.$req.post("/towork/api/frens/add",this.today).then(res=>{
							if(res.data.code === 'SUCCESS'){
								that.$refs.mpopup.open({
									type:'success',
									content:"添加成功",
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
							console.log(err);
							that.$refs.mpopup.open({
								type:'warn',
								content:'网络超时',
								timeout:3000,
							});
							that.loading = false;
						})
						
					}else{
						this.$refs.mpopup.open({
							type:'warn',
							content:'请填写接触人员',
							timeout:3000,
						});
						this.loading = false;
					}
				}
			},
		}
	}
</script>

<style lang="scss">
	.content {
		padding: 0;
		font-size: $uni-font-size-lg;
		.header{
			padding: 30upx 0 30upx 20upx;
			font-size: $uni-font-size-max;
			background-image: linear-gradient(to bottom, $uni-bg-color-main, #FFFFFF);
			color: #FFFFFF;
			font-weight: bold;
		}
		view {
			padding: 20upx;
			view{
				padding: 10upx 0;
				.colorRed {
					color: red;
				}
			}	
			.textarea {
				margin-top: 10upx;
				padding: 10upx;
				width: 100%;
				border: 1upx solid $uni-border-color;
				background-color: #FFFFFF;
			}	
		}
	}
</style>
