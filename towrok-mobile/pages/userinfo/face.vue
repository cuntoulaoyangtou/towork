<template>
	<view>
		<view v-show="discernShow" class="discern colorRed"></view>
		<view>
			<image class="bg" src="/static/img/ai.png"></image>
		</view>
		<view :animation="animationData" class="animation">
			<image class="line" src="/static/img/line1.png"></image>
		</view>
		<view>
			<image class="face" :src="url"></image>
		</view>
		<view class="btn"><button @click="opimg" type="primary" :disabled="loading">录入人脸信息</button></view>
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
				url: '/static/img/face.jpg',
				discernShow: false,
				animationData: {},
				msg: "正在识别",
				loading: false,
				isface: false,
			};
		},
		onLoad(op) {
			console.log(op);
			if (op.flag) {
				this.isface = op.flag;
			}
		},
		onShow() {
			if (!this.hasLogin) {
				uni.redirectTo({
					url: "/pages/login/login",
				})
				return
			}

			var animation = uni.createAnimation({
				duration: 2000,
				timingFunction: 'linear',
			})
			this.animation = animation
		},
		methods: {
			...mapMutations(['login', 'logout']),
			opimg() {
				let that = this;
				this.loading = true;
				if (!that.discernShow) {
					uni.chooseImage({
						count: 1, //默认9
						sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
						sourceType: ['camera'], //从相册选择
						success: function(res) {
							that.url = res.tempFilePaths[0]
							that.msg = "正在识别";
							let dis = setInterval(function() {
								that.discernShow = !that.discernShow;
							}, 100)
							that.rotateAndScale()
							let fileurl = res.tempFilePaths[0]
							if (that.isface) {
								//初始化人脸信息
								uni.uploadFile({
									url: "http://121.37.249.253:5001/face/register/",
									filePath: fileurl,
									name: "file",
									success(res) {
										//识别返回数据
										if (res.statusCode === 200) {
											let face_data = JSON.parse(res.data);;
											console.log(face_data);
											if (face_data.face_found_in_image === 0) {
												that.$refs.mpopup.open({
													type: 'warn',
													content: '未发现人脸信息',
													timeout: 2000,
												});
											} else {
												that.$refs.mpopup.open({
													type: 'success',
													content: '人脸验证通过',
													timeout: 2000,
												});
												console.log("face_data.face_found_in_image", face_data.face_found_in_image);
												if (face_data.file_avalible) {
													console.log("face_data.file_avalible", face_data.file_avalible);
													//文件可用
													if (face_data.is_picture_of_self === 2 || face_data.is_picture_of_self === "2") {
														//开始上传文件
														that.msg = "上传脸部信息"
														that.$refs.mpopup.open({
															type: 'success',
															content: '正在上传脸部信息',
															timeout: 2000,
														});
														console.log("uuuuid", face_data.file_uuid);
														that.$req.post("/towork/api/user/change", {
															face_id: face_data.file_uuid
														}).then(res => {
															if (res.data.code === 'SUCCESS') {
																that.msg = "信息上传成功"
																that.$refs.mpopup.open({
																	type: 'success',
																	content: '上传脸部成功',
																	timeout: 2000,
																});
																that.logout();
																that.login(res.data.data);
																uni.redirectTo({
																	url: "/pages/userinfo/userform"
																})
																that.loading = false
																return;
															} else {
																that.$refs.mpopup.open({
																	type: 'warn',
																	content: res.data.message,
																	timeout: 2000,
																});
																setTimeout(function() {
																	uni.switchTab({
																		url: "/pages/index/index"
																	})
																}, 2000)
															}
															that.loading = false;
														}).catch(err => {
															that.$refs.mpopup.open({
																type: 'err',
																content: '网络超时',
																timeout: 2000,
															});
															setTimeout(function() {
																uni.switchTab({
																	url: "/pages/index/index"
																})
															}, 2000)
															that.loading = false;
														})
													} else {
														that.$refs.mpopup.open({
															type: 'warn',
															content: '信息无效',
															timeout: 2000,
														});
														setTimeout(function() {
															uni.switchTab({
																url: "/pages/index/index"
															})
														}, 2000)
													}
												} else {
													that.$refs.mpopup.open({
														type: 'warn',
														content: '文件不可用',
														timeout: 2000,
													});
													setTimeout(function() {
														uni.switchTab({
															url: "/pages/index/index"
														})
													}, 2000)
												}
											}
										}else{
											setTimeout(function() {
												uni.switchTab({
													url: "/pages/index/index"
												})
											}, 2000)
										}
										clearInterval(dis);
										that.loading = false;

									},
									fail() {
										clearInterval(dis);
										that.loading = false;
									}
								})


							} else {
								console.log(JSON.stringify(res));
								//脸部验证
								console.log("正在脸部验证");
								that.msg = "正在验证脸部信息"
								that.$refs.mpopup.open({
									type: 'success',
									content: '正在验证脸部信息',
									timeout: 2000,
								});
								uni.uploadFile({
									url: "http://121.37.249.253:5001/face/check/",
									filePath: fileurl,
									name: "file",
									formData: {
										uuid: that.userInfo.face_id
									},
									success(res) {
										if (res.statusCode === 200) {
											console.log("获得了人脸信息", res)
											let face_data = JSON.parse(res.data);;
											if (face_data.face_found_in_image === 0) {
												that.$refs.mpopup.open({
													type: 'warn',
													content: '未发现人脸信息',
													timeout: 2000,
												});
											} else {
												if (face_data.file_avalible) {
													//文件可用
													console.log("文件可用", face_data.is_picture_of_self);
													if (face_data.is_picture_of_self === 1 || face_data.is_picture_of_self === "1") {
														that.$refs.mpopup.open({
															type: 'success',
															content: '脸部验证通过',
															timeout: 2000,
														});
														uni.redirectTo({
															url: "/pages/other/health_punch",
														})
														return;
													}else{
														that.$refs.mpopup.open({
															type: 'warn',
															content: '不是本人',
															timeout: 2000,
														});
														setTimeout(function() {
															uni.switchTab({
																url: "/pages/index/index"
															})
														}, 2000)
													}
												} else {
													that.$refs.mpopup.open({
														type: 'warn',
														content: '文件不可用',
														timeout: 2000,
													});
													setTimeout(function() {
														uni.switchTab({
															url: "/pages/index/index"
														})
													}, 2000)
												}
											}
										}else{
											
											setTimeout(function() {
												uni.switchTab({
													url: "/pages/index/index"
												})
											}, 2000)
										}

										that.loading = false;
										clearInterval(dis);
									},
									fail(err) {
										console.log("err失败" + JSON.stringify(err));
										clearInterval(dis);
										that.$refs.mpopup.open({
											type: 'warn',
											content: '识别失败',
											timeout: 2000,
										});
										that.loading = false;
										setTimeout(function() {
											uni.switchTab({
												url: "/pages/index/index"
											})
										}, 2000)
									}
								})
							}

						},
						fail() {
							that.msg = ""
							that.$refs.mpopup.open({
								type: 'warn',
								content: '你取消了操作',
								timeout: 2000,
							});
							that.loading = false;
						}
					});
				}

			},
			rotateAndScale() {
				// 定义动画内容
				let num = 100;
				let that = this;
				let set = setInterval(function() {
					that.animation.translateY(num % 2 == 0 ? 300 : 0).step();
					// 导出动画数据传递给data层
					that.animationData = that.animation.export();
					num--;
					if (num < 0) {
						clearInterval(set);
					}
				}, 2000)
				return set;
			}
		},
	}
</script>

<style>
	.bg,
	.face {
		position: fixed;
	}

	.line {
		width: 750upx;
		height: 50upx;
	}

	.discern {
		position: fixed;
		text-align: center;
		top: 200upx;
		width: 750upx;
	}

	.animation {
		position: fixed;
		top: 210upx;
		z-index: -2;
	}

	.bg {
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		z-index: -1;
	}

	.face {
		position: fixed;
		top: 20%;
		left: 20%;
		width: 60%;
		height: 60%;
		z-index: -3;
	}

	.btn {
		position: fixed;
		width: 600upx;
		left: 75upx;
		bottom: 50upx;
	}
</style>
