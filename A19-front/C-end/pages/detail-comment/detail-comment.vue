<template>
	<view>
		
		<view class="p-2">
			<view class="d-flex a-center py-2 border-bottom border-light-secondary">
				<text class="text-muted">用户评价(123)</text>
				<text class="main-text-color ml-auto mr-1">98.5%</text>
				<text class="text-muted">满意</text>
			</view>
			<view class="d-flex flex-wrap pt-3" style="margin-right: -20rpx;">
				<view class="px-2 py border rounded mr-2 mb-2 cate-item"
				v-for="(item,index) in cateList" :key="index"
				:class="cateIndex === index ? 'active' : ''"
				@tap="cateChange(index)">
					{{item.name}}
				</view>
			</view>
		</view>
		<divider />
		
		<view class="p-2 d-flex a-start border-bottom border-light-secondary">
			<image src="../../static/images/demo/demo6.jpg" mode="widthFix"
			style="width: 90rpx;height: 90rpx;" class="rounded flex-shrink"></image>
			<view class="pl-2 flex-1">
				<view class="d-flex a-center">
					<text class="font-md text-primary font-weight">热***友</text>
					<view class="iconfont icon-manyi main-text-color ml-auto">
						<text class="pl-1 font">非常满意</text>
					</view>
				</view>
				<view class="line-h-md font-md">
					真的是全新的，这波也太值了！！
				</view>
				<view class="row">
					<view class="span24-8 px pb">
						<image src="../../static/images/demo/cate_08.png" mode="widthFix"
						style="height: 100rpx;"></image>
					</view>
				</view>
				<view class="d-flex a-center">
					<text class="text-light-muted">2019-08-20</text>
					<view class="d-flex text-light-muted ml-auto a-center mr-2">
						0 <text class="iconfont icon-dianzan text-light-muted ml"></text>
					</view>
					<view class="d-flex text-light-muted a-center">
						10 <text class="iconfont icon-pinglun text-light-muted ml"></text>
					</view>
				</view>
				<!-- 客服回复 -->
				<view class="bg-light-secondary rounded p-2 d-flex flex-wrap a-center mt-1">
					<text class="main-text-color">官方回复：</text>
					感谢您的支持！您的满意是小二最大的追求！欢迎下次再来~有借有还再借不难！
<!-- 					<view class="iconfont icon-dianzan text-light-muted line-h-md font ml-2">
						<text class="text-muted pl-1">赞客服 120</text>
					</view> -->
				</view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				cateIndex:0,
				cateList:[
					{ name:"全部" },
					{ name:"有图" },
					{ name:"非常喜欢" },
					{ name:"拍照好" },
					{ name:"手感很棒" },
					{ name:"效果好" },
					{ name:"性能很棒" },
				],
			}
		},
		methods: {
			cateChange(index){
				this.cateIndex = index
			}
		}
	}
</script>



<style>
.cate-item{
	background: #FFEBEC;
	color: #7B6D6C;
	border-color:#F4E0E1;
}
.cate-item.active{
	background: #FF6B01!important;
	color: #FFFFFF!important;
	border-color: #DE7232!important;
}
</style>
