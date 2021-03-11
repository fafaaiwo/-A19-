<template>
	<view class="">
		<scroll-view scroll-x class="scroll-row">
			
			<view class="scroll-row-item rounded bg-white m-2 p-2 comment-style" v-for="(item,index) in comments" :key="index"
			style="width: 620rpx;height: 380rpx;">
				
				<view class="d-flex a-center mb-1">
					<image :src="item.userpic" 
					mode="widthFix" style="width: 70rpx;height: 70rpx;"
					class="rounded-circle"></image>
					<view class="ml-2">
						<text class="font-md line-h">{{item.username}}</text>
						<text v-if="item.state === 0" class="ml-2 text-warning">未成功交易</text>
						<text v-else-if="item.state === 1" class="ml-2 text-success">成功交易</text>
						<text class="d-block font text-light-muted line-h">{{item.create_time}}</text>
					</view>
					<view class="iconfont icon-dianzan text-light-muted font ml-auto">{{item.goods_num}}</view>
				</view>
				
				<view class="d-block font-md mb-2 br-line-1">
					{{item.context}}
				</view>
				
				<view class="row">
					<view class="span24-8 px-2"
					v-for="(img,i) in item.imglist"
					:key="i">
						<image :src="img" 
						mode="widthFix" style="height: 115rpx;"
						class="rounded"></image>
					</view>
				</view>
				
			</view>
		</scroll-view>

		<navigator url="/pages/detail-comment/detail-comment">
		<view class="d-flex a-center j-center py-2 mt-2 text-primary"
		hover-class="bg-light-secondary">
			更多评论 <view class="iconfont icon-gengduo"></view>
		</view>
		</navigator>	
	</view>
</template>

<script>
	export default {
		props: ['comments'],
	}
</script>

<style>
.comment-style{
	border-radius: 10rpx;
	-moz-box-shadow: 2px 2px 10px #ddd;
	-webkit-box-shadow: 2px 2px 10px #ddd; 
	box-shadow: 2px 2px 10px #ddd;
}

</style>
