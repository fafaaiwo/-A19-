<template>
	<view>
		<!-- 头部 -->
		<view class="position-relative d-flex a-center" style="height: 450rpx;">
			<loading-plus v-if="beforeReady"></loading-plus>
			<view class="iconfont icon-shezhi position-absolute text-white" 
			style="font-size: 50rpx;top: 75rpx;right: 20rpx;z-index: 100;"
			@click="navigate('user-set')"></view>
			<view class="iconfont icon-pingtaikefu position-absolute text-white"
			style="font-size: 50rpx;top: 75rpx;right: 100rpx;z-index: 100;"></view>
			<image src="../../static/images/bg.jpg" style="height: 450rpx;width: 100%;"></image>
			
			<view class="d-flex a-center position-absolute left-0 right-0" style="bottom: 180rpx;">
				<image src="../../static/images/demo/demo6.jpg" style="height: 145rpx;width: 145rpx;border: 5rpx solid;" class="rounded-circle border-light ml-4"></image>
				<view class="">
					<navigator url="../login/login">
						<view class="ml-2 text-white font-md">飞翔的企鹅</view>
					</navigator>
					<view class="ml-2 text-white font-md">ID: feixiangdeqie</view>
				</view>
				<view class="d-flex a-center j-center a-self-end ml-auto px-2" style="height: 70rpx;background: #FFD43F;color: #CC4A00;border-top-left-radius: 40rpx;border-bottom-left-radius: 40rpx;">
					<!-- <view class="line-h iconfont icon-level1 mr-1"></view> -->
					信用积分 100
				</view>
			</view>
			<view class="d-flex a-center position-absolute left-0 right-0 text-white" style="bottom: 20rpx;">
				<view class="flex-1 d-flex flex-column a-center j-center">
					<view class="font-lg">1</view>
					<view class="font">收藏夹</view>
				</view>
				<view class="flex-1 d-flex flex-column a-center j-center">
					<view class="font-lg">3</view>
					<view class="font">关注店铺</view>
				</view>
				<view class="flex-1 d-flex flex-column a-center j-center">
					<view class="font-lg">60</view>
					<view class="font">足迹</view>
				</view>
				<view class="flex-1 d-flex flex-column a-center j-center">
					<view class="font-lg">0</view>
					<view class="font">优惠券</view>
				</view>
			</view>
		</view>
		
		<!-- 图标分类 -->
		<card>
			<view slot="title" class="d-flex a-center j-sb">
				<text class="font-md font-weight">我的订单</text>
				<view class="text-secondary font" @click="navigate('order')">
					全部订单 <text class="iconfont icon-gengduo font"></text>
				</view>
			</view>
			<view class="row j-center p-2" style="border-bottom: #f5f5f5 2rpx solid;">
				<block v-for="(orderItem,orderIndex) in orderCate" :key="orderIndex">
					<view class="span-5 d-flex flex-column a-center j-center py-2"
					hover-class="bg-light-secondary">
						<view class="line-h" :class="orderItem.icon" style="font-size: 50rpx;"></view>
						<view>{{orderItem.name}}</view>
					</view>
				</block>
			</view>
		</card>
		
		<!-- 我的钱包 -->
		<view class="m-2" style="border: #f5f5f5 1px solid; border-radius: 15rpx; overflow: hidden;">
			<view class="d-flex a-center j-sb">
				<!-- <view class="iconfont icon-yue" style="color:#FDBF2E;"></view> -->
				<view class="ml-2 p-2" style="font-size: 35rpx;">钱包余额</view>
				<view class="d-flex a-center">
					<view v-if="remainderShow" class="mr-3" style="font-size: 35rpx; color: #FDBF2E;">￥ 100.00</view>
					<view v-else class="mr-3" style="font-size: 35rpx; color: #FDBF2E;">￥ *****</view>
					<view :class="remainderShow?'iconfont icon-yincang':'iconfont icon-xianshi'" class="mr-3 text-light-muted"
					@click="showRemainder"></view>
				</view>
			</view>
			<uni-list-item showExtraIcon leftIconStyle="color:#FDBF2E;"
			leftIcon="icon-liushui" title="查看账户流水"></uni-list-item>
			<uni-list-item showExtraIcon leftIconStyle="color:#FDBF2E;"
			leftIcon="icon-tixian" title="去提现"></uni-list-item>
		</view>
		
	</view>
</template>

<script>
	import card from "@/components/common/card.vue"
	import uniListItem from "@/components/uni-ui/uni-list-item/uni-list-item.vue"
	import loading from "@/common/mixin/loading.js"
	export default {
		mixins:[loading],
		components: {
			card,
			uniListItem
		},
		data() {
			return {
				remainderShow:false,
				orderCate:[
					{
						id:0,
						name:"待付款",
						icon:"iconfont icon-daifukuan",
						num:0
					},
					{
						id:1,
						name:"待发货",
						icon:"iconfont icon-daifahuo",
						num:1
					},
					{
						id:2,
						name:"待收货",
						icon:"iconfont icon-daishouhuo",
						num:2
					},
					{
						id:3,
						name:"使用中",
						icon:"iconfont icon-shiyongzhong",
						num:3
					},
					{
						id:4,
						name:"已退还",
						icon:"iconfont icon-yituihuan",
						num:4
					},
					{
						id:5,
						name:"已购买",
						icon:"iconfont icon-yigoumai",
						num:5
					},
					{
						id:6,
						name:"待评价",
						icon:"iconfont icon-daipingjia",
						num:6
					},
					{
						id:7,
						name:"退款/售后",
						icon:"iconfont icon-tuikuan",
						num:7
					}
				]
			}
		},
		methods: {
			navigate(path){
				if (!path) return;
				uni.navigateTo({
					url: `/pages/${path}/${path}`
				});
			},
			showRemainder(){
				this.remainderShow = !this.remainderShow;
			}
		}
	}
</script>

<style>

</style>
