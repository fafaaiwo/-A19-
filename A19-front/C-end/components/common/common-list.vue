<template>
	<view class="goods-item" @click="openDetail">
		<image  :src="item.cover" mode="widthFix" lazy-load></image>
		<view class="px-2 py-1 pt-1">
			<view class="goods-name">{{item.title}}</view>
			<!-- <text class="d-block font text-light-muted">{{item.desc}}</text> -->
			<view class="d-flex my-1">
				<price :unit="item.unit"><text class="font-lg">{{item.pprice}}</text></price>
				<view class="font-sm text-light-muted line-through ml-1 a-self-end line-h" >￥{{item.oprice}}/天</view>
			</view>
		</view>
	</view>
</template>

<script>
	import price from "@/components/common/price.vue";
	export default {
		components:{
			price
		},
		props:{
			item:Object,
			index:Number
		},
		methods:{
			openDetail(){
				uni.navigateTo({
					url: '/pages/goods-detail/goods-detail'
				});
			}
		}
	}
</script>

<style>
	.goods-item{
		margin: 5upx;
		border: #eeeeee solid 2upx;
		width: 361upx; 
		border-radius: 15upx; 
		overflow: hidden;
	}
	.goods-name{
		font-size: 30upx;
		color: #3e4b53;
		height: 3.2em;
		overflow: hidden;
		line-height: 1.6em;
	}
</style>
