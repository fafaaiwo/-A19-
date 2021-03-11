<template>
	<view>
		<uni-nav-bar title="消息(300)" statusBar :shadow="false" :fixed="true"></uni-nav-bar>
		
		<view class="main">
			<view class="friends">
				<view class="friend-list">
					<view class="friend-list-l">
						<text class="tip">1</text>
						<image src="../../static/images/demo/demo6.jpg" mode=""></image>
					</view>
					<view class="friend-list-r">
						<view class="top">
							<view class="name">物流信息</view>
							<view class="time">20:35</view>
						</view>
						<view class="msg-content">您的订单开始发货！</view>
					</view>
				</view>
			</view>
			<view class="friends">
				<view class="friend-list" v-for="(item, index) in friends" :key="item.id" @tap="openChatroom">
					<view class="friend-list-l">
						<text class="tip">{{item.tip}}</text>
						<image :src="item.imgurl" mode=""></image>
					</view>
					<view class="friend-list-r">
						<view class="top">
							<view class="name">{{item.name}}</view>
							<view class="time">{{changeTime(item.time)}}</view>
						</view>
						<view class="msg-content">{{item.news}}</view>
					</view>
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	import uniNavBar from '@/components/uni-ui/uni-nav-bar/uni-nav-bar.vue';
	import simData from '@/common/js/sim-data.js'
	import myFunc from '@/common/js/my-func.js'
	export default {
		components: {
			uniNavBar
		},
		data() {
			return {
				friends:[]
			}
		},
		onLoad() {
			this.getFriends()
		},
		methods: {
			changeTime(date) {
				return myFunc.dateTime(date);
			},
			getFriends() {
				this.friends = simData.friends()
			},
			openChatroom() {
				uni.navigateTo({
					url: '../chatroom/chatroom'
				});
			}
		}
	}
</script>

<style lang="scss">
	.main {
		padding-top: 16rpx;
	}
	.friend-list {
		height: 96rpx;
		padding: 16rpx $uni-spacing-col-base;
		&:active {
			background-color: $uni-bg-color-grey;
		}
		
		.friend-list-l {
			position: relative;
			float: left;
			image{
				width: 96rpx;
				height: 96rpx;
				border-radius: $uni-border-radius-base;
				background-color: $uni-color-primary;
			}
			.tip {
				position: absolute;
				z-index: 10;
				top: -6rpx;
				left: 68rpx;
				min-width: 36rpx;
				height: 36rpx;
				background: $uni-color-warning;
				border-radius: $uni-border-radius-circle;
				font-size: $uni-font-size-sm;
				color: $uni-text-color-inverse;
				line-height: 36rpx;
				text-align: center;
			}
		}
		.friend-list-r {
			padding-left: 128rpx;
			.top {
				height: 58rpx;
				.name {
					float: left;
					font-size: 36rpx;
					font-weight: 400;
					color: $uni-text-color;
					line-height: 50rpx;
				}
				.time {
					float: right;
					font-size: $uni-font-size-sm;
					color: $uni-text-color-disable;
					line-height: 50rpx;
				}
			}
			.msg-content {
				font-size: $uni-font-size-base;
				color: $uni-text-color-grey;
				line-height: 40rpx;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp:1;
				overflow: hidden;
			}
		}
	}
</style>
