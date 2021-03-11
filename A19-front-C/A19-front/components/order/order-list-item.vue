<template>
	<view class="border-bottom d-flex a-center py-2 border-light-secondary" @click="open">
		<image :src="item.cover" mode="widthFix" style="width: 150rpx;height: 150rpx;" class="rounded mx-2 flex-shrink"></image>
		<view class="flex-1">
			<view class="d-flex a-center">
				<text class="font-md text-dark">{{ item.title }}</text>
				<text class="font-md text-light-muted ml-auto">押金 ￥{{ item.deposit }}</text>
			</view>
			<view class="d-flex a-center">
				<text class="font text-light-muted">{{ item.attrs }}</text>
				<text class="font text-light-muted ml-auto">x{{ item.num }}</text>
			</view>
			<view class="d-flex a-center">
				<text class="font text-light-muted">租金 ￥{{ item.pprice }} / {{ item.unit }}</text>
				<text class="font text-light-muted ml-auto">x{{ item.period }}</text>
			</view>
			<view class="d-flex a-center">
				<text class="font text" style="color: #FDBF2E;">付款后{{ item.sendtime }}内发货</text>
			</view>
		</view>
	</view>
	
<!-- 	<view class="border-bottom d-flex a-center py-2 border-light-secondary"
	@click="open">
		<image :src="item.cover" mode="widthFix"
		style="width: 150rpx;height: 150rpx;" class="rounded mx-2 flex-shrink"
		></image>
		<view class="flex-1">
			<view class="d-flex a-center">
				<text class="font-md text-dark">{{item.title}}</text>
				<text class="font-md text-light-muted ml-auto"
				>￥{{item.pprice}}</text>
			</view>
			<view class="d-flex a-center">
				<text class="font text-light-muted">{{item.attrs}}</text>
				<text class="font text-light-muted ml-auto"
				>x{{item.num}}</text>
			</view>
		</view>
	</view> -->
</template>

<script>
	export default {
		props: {
			item: Object,
			index:Number
		},
		methods: {
			open() {
				uni.navigateTo({
					url: '/pages/goods-detail/goods-detail',
				});
			}
		},
	}
</script>

<style>
</style>
