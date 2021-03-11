<template>
	<view>
		<view class="d-flex flex-column a-center j-center py-5 my-3">
			<text class="text-light-muted font-md">支付金额</text>
			<view class="font-lg main-text-color">￥38.00</view>
		</view>
		<view class="px-5">
			<label>
				<uni-list-item leftIcon="icon-zhifubao text-primary" 
				title="支付宝支付" showExtraIcon leftIconStyle="font-size:60rpx;"
				note="推荐使用支付宝支付">
					<radio slot="right" value="1" color="#FD6801"/>
				</uni-list-item>
			</label>
			<label>
				<uni-list-item leftIcon="icon-weixinzhifu text-success" 
				title="微信支付" showExtraIcon leftIconStyle="font-size:60rpx;"
				>
					<radio slot="right" value="2" color="#FD6801"/>
				</uni-list-item>
			</label>
			
			<view class="rounded main-bg-color text-white font-md w-100 py-2 mt-3 text-center" hover-class="main-bg-hover-color" @click="submit">
				确认支付
			</view>
			
		</view>
	</view>
</template>

<script>
	import price from "@/components/common/price.vue"
	import uniListItem from "@/components/uni-ui/uni-list-item/uni-list-item.vue"
	export default {
		components: {
			price,
			uniListItem
		},
		data() {
			return {
				
			}
		},
		methods: {
			submit(){
				uni.navigateTo({
					url: '../pay-result/pay-result',
				});
			}
		}
	}
</script>

<style>

</style>
