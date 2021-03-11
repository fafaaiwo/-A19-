<template>
	<view style="background: #F5F5F5;min-height: 100%;height: auto;">
		<!-- 地址选择 -->
		<view class="main-bg-color text-white d-flex a-center px-3" 
		style="height: 250rpx;" hover-class="main-bg-hover-color"
		 @click="openAddrList">
			<view>
				<template v-if="addr">
					<view class="font-weight font-md d-flex a-center font-lg">
						{{addr.name}} {{addr.phone}}
						<view class="border border-white rounded px-1 font ml-2"
						v-if="addr.isDefault">默认</view>
					</view>
					<view class="font">
						{{addr.addr}} {{addr.detailAddr}}
					</view>
				</template>
				<template v-else>
					<view
					class="font-weight font-md d-flex a-center font-lg">
						请选择收货地址
					</view>
				</template>
			</view>
			<view class="iconfont icon-gengduo ml-auto"></view>
		</view>
		
		<view style="border-top-left-radius: 25rpx;border-top-right-radius: 25rpx;margin-top: -25rpx;overflow: hidden;">
			<view class="bg-white">
				<uni-list-item showArrow>
					<view class="d-flex a-center">
						<image src="/static/images/demo/demo6.jpg"
						style="width: 100rpx;height: 100rpx;" class="rounded mr-2"
						></image>
						<image src="/static/images/demo/demo6.jpg"
						style="width: 100rpx;height: 100rpx;" class="rounded mr-2"
						></image>
						<image src="/static/images/demo/demo6.jpg"
						style="width: 100rpx;height: 100rpx;" class="rounded mr-2"
						></image>
					</view>
					<view slot="rightContent">共3件</view>
				</uni-list-item>
				<uni-list-item title="总押金" :showArrowIcon="false">
					<view slot="rightContent">
						<view class="text-dark">￥ 20.00</view>
					</view>
				</uni-list-item>
				<uni-list-item title="运费" :showArrowIcon="false">
					<view slot="rightContent">包邮</view>
				</uni-list-item>
				<navigator url="../order-coupon/order-coupon">
					<uni-list-item title="优惠券">
						<view slot="rightContent" class="text-light-muted">无可用</view>
					</uni-list-item>
				</navigator>
				<uni-list-item :showArrowIcon="false">
					<text class="main-text-color" style="font-size: 32rpx;">小计</text>
					<view slot="rightContent">
						<view class="main-text-color font-md">￥ 20.00</view>
					</view>
				</uni-list-item>
				<divider></divider>
				<uni-list-item title="发票" extraWidth="40%" @click="openOrderInvoice">
					<view slot="rightContent">电子发票-个人</view>
				</uni-list-item>
				
			</view>
		</view>
		
		<view class="position-fixed d-flex a-center j-end border bottom-0 left-0 right-0 bg-white p-2 font-md">
			合计:
			<view class="ml-2">
				<view class="main-text-color">￥ 20.00</view>
			</view>
			<view class="ml-2 px-2 py-1 main-bg-color text-white font-md"
			hover-class="main-bg-hover-color" style="border-radius: 80rpx;"
			@click="openPayMethods">
				去支付
			</view>
		</view>
		
	</view>
</template>

<script>
	import uniListItem from "@/components/uni-ui/uni-list-item/uni-list-item.vue"
	import price from "@/components/common/price.vue"
	
	import {mapGetters} from "vuex"
	export default {
		components: {
			uniListItem,
			price
		},
		data() {
			return {
				addr:false
			}
		},
		computed: {
			...mapGetters(['defaultAddr'])
		},
		onLoad() {
			if (this.defaultAddr.length) {
				this.addr = this.defaultAddr[0]
			}
			// 开启监听选择收货地址事件
			uni.$on('chooseAddr',(res)=>{
				this.addr = res
			})
		},
		onUnload() {
			// 关闭监听选择收货地址事件
			uni.$off('chooseAddr',(e)=>{
				console.log('关闭监听选择收货地址');
			})
		},
		methods: {
			openAddrList(){
				uni.navigateTo({
					url: '../user-addr-list/user-addr-list?type=choose'
				});
			},
			openOrderInvoice(){
				uni.navigateTo({
					url: '../order-invoice/order-invoice'
				});
			},
			openPayMethods(){
				uni.navigateTo({
					url: '../pay-methods/pay-methods',
				});
			}
		}
	}
</script>

<style>

</style>
