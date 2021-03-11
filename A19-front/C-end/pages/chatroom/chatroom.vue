<template>
	<view class="content">
		<uni-nav-bar title="自强客服在线" statusBar @clickLeft="goBack" :shadow="false" :fixed="true">
			<text slot="left" class="iconfont icon-fanhui"></text>
			<image slot="right" src="../../static/images/demo/demo6.jpg" mode="widthFix" style="width: 68rpx;height: 68rpx; border-radius: 16rpx;"></image>
		</uni-nav-bar>
		
		<scroll-view class="chat" scroll-y="true" :style="'height:' + scrollH + 'px;'" scroll-with-animation="true" :scroll-into-view="scrollToView">
			<view class="chat-main" :style="{paddingBottom: inputh+'px'}">
				<view class="chat-ls" v-for="(item,index) in msgs" :key="index" :id="'msg'+item.tip">
					<view class="chat-time" v-if="item.time != ''">{{changeTime(item.time)}}</view>
					<view class="msg-m msg-left" v-if="item.id != 'b'">
						<image :src="item.imgurl" class="user-img" mode="widthFix"></image>
						<view class="message" v-if="item.types == 0">
							<view class="msg-text">{{item.message}}</view>
						</view>
						<view class="message" v-if="item.types == 1">
							<image :src="item.message" class="msg-img" mode="widthFix" @tap="previewImage(item.message)"></image>
						</view>
					</view>
					<view class="msg-m msg-right" v-if="item.id == 'b'">
						<image :src="item.imgurl" class="user-img" mode="widthFix"></image>
						<view class="message" v-if="item.types == 0">
							<view class="msg-text">{{item.message}}</view>
						</view>
						<view class="message" v-if="item.types == 1">
							<image :src="item.message" class="msg-img" mode="widthFix" @tap="previewImage(item.message)"></image>
						</view>
					</view>
				</view>
			</view>
			<view class="padbt"></view>
		</scroll-view>
		<submit @inputs="inputs" @heights="heights"></submit>
	</view>
</template>

<script>
	import uniNavBar from '@/components/uni-ui/uni-nav-bar/uni-nav-bar.vue';
	import simData from '@/common/js/sim-data.js';
	import myFunc from '@/common/js/my-func.js';
	import submit from '@/components/message/submit.vue';
	
	export default {
		components: {
			uniNavBar,
			submit
		}, 
		data() {
			return {
				scrollToView:"",
				scrollH:500,
				navBarH:44,
				msgs:[],
				imgMsg:[],
				oldTime: new Date(),
				inputh:66.6,
			}
		},
		onLoad() {
			uni.getSystemInfo({
				success: res => {
					this.scrollH = res.windowHeight - res.statusBarHeight - this.navBarH;
				}
			});
			this.getMsg();
		},
		methods: {
			changeTime(date) {
				return myFunc.chatTime(date);
			},
			goBack(){
				uni.navigateBack({
					delta: 1
				});
			},
			getMsg() {
				let msg = simData.message();
				for (var i=0; i<msg.length;i++) {
					//时间间隔
					if(i <msg.length-1) {
						let t = myFunc.spaceTime(this.oldTime, msg[i].time);
						if (t) {
							this.oldTime = t;
						}
						msg[i].time = t;
					}
					if(msg[i].types == 1) {
						this.imgMsg.unshift(msg[i].message);
					}
					this.msgs.unshift(msg[i])
				}
				this.$nextTick(function(){
					this.scrollToView = 'msg' + this.msgs[i-1].tip;
				})
			},
			previewImage(e) {
				let index=0;
				for (let i = 0; i < this.imgMsg.length; i++) {
					if (this.imgMsg[i] == e) {
						index = i;
					}
				}
				
				uni.previewImage({
					current:index,
					urls: this.imgMsg,
					longPressActions: {
						itemList: ['保存图片'],
						success: function(data) {
							console.log('选中了第' + (data.tapIndex + 1) + '个按钮,第' + (data.index + 1) + '张图片');
						},
						fail: function(err) {
							console.log(err.errMsg);
						}
					}
				});
			},
			inputs(e) {
				let len = this.msgs.length;
				let data = {
					id:'b',
					imgurl:'/static/images/demo/demo6.jpg',
					message:e,
					types:0,
					time: new Date(),
					tip:len,
				};
				this.msgs.push(data);
				this.goBottom(); 
			},
			//输入框高度
			heights(e) {
				this.inputh = e;
				this.goBottom();
				// console.log('高度',e);
			},
			//滚动到底部
			goBottom() {
				this.scrollToView = '';
				this.$nextTick(function(){
					let len = this.msgs.length - 1;
					this.scrollToView = 'msg' + this.msgs[len].tip;
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		height: 100%;
	}
	.content {
		height: 100%;
		background: rgba(244,244,244,1);
	}
	.chat {
		.padbt {
			height: 30rpx;
			width: 100%;
		}
		.chat-main {
			padding-left: $uni-spacing-col-base;
			padding-right: $uni-spacing-col-base;
			// padding-top: 100rpx;
			padding-bottom: 120rpx;
			display: flex;
			flex-direction: column;
		}
		.chat-ls {
			.chat-time {
				font-size: $uni-font-size-sm;
				color: rgba(39,40,50,0.3);
				line-height: 34rpx;
				padding: 20rpx 0;
				text-align: center;
			}
			.msg-m {
				display: flex;
				padding: 20rpx 0;
				.user-img {
					flex: none;
					width: $uni-img-size-sm;
					height: $uni-img-size-sm;
					border-radius: $uni-border-radius-base;
				}
				.message {
					flex:none;
					max-width: 480rpx;
				}
				.msg-text {
					font-size: $uni-font-size-lg;
					color: $uni-text-color;
					line-height: 44rpx;
					padding: 18rpx 24rpx;
				}
				.msg-img {
					width: 400rpx;
					max-width: 400rpx;
					border-radius: $uni-border-radius-base;
				}
			}
			.msg-left {
				flex-direction: row;
				.msg-text {
					margin-left: 16rpx;
					background-color: #ffffff;
					border-radius: 0rpx 20rpx 20rpx 20rpx;
				}
				.msg-img {
					margin-left: 16rpx;
				}
			}
			.msg-right {
				flex-direction: row-reverse;
				.msg-text {
					margin-right: 16rpx;
					background-color: #fff260;
					border-radius: 20rpx 0rpx 20rpx 20rpx;
				}
				.msg-img {
					margin-right: 16rpx;
				}
			}
		}
	}
	
	
</style>
