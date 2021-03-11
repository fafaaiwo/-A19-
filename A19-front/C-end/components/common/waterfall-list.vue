<template>
	<div class="page">
		<!-- <scroll-view style="height: 300px;" scroll-y="true" class="scroll-Y" @scrolltolower="loadMoreImages"> -->
		<view class="">
			<view class="main">
				<div class="view Left">
					<div class="content" v-for="(item, index) in firstList" :key="index" @click="openDetail">
						<div class="img-content"><image v-if="item.cover" :src="item.cover" class="image" mode="widthFix" :data-index="index" data-type="1" /></div>
						<view class="px-2 py-1 pt-1">
							<view class="br-line-1">{{item.title}}</view>
							<!-- <text class="d-block font text-light-muted">{{item.desc}}</text> -->
							<view class="d-flex my-1">
								<price :unit="item.unit"><text class="font-lg">{{item.pprice}}</text></price>
								<view class="font-sm text-light-muted line-through ml-1 a-self-end line-h">￥{{item.oprice}} /天</view>
							</view>
						</view>
					</div>
				</div>
				<div class="view Right">
					<div class="content" v-for="(item, index) in secondList" :key="index" @click="openDetail">
						<div class="img-content"><image v-if="item.cover" :src="item.cover" class="image" mode="widthFix" :data-index="index" data-type="2" /></div>
						<view class="px-2 py-1 pt-1">
							<view class="br-line-1">{{item.title}}</view>
							<!-- <text class="d-block font text-light-muted">{{item.desc}}</text> -->
							<view class="d-flex my-1">
								<price :unit="item.unit"><text class="font-lg">{{item.pprice}}</text></price>
								<view class="font-sm text-light-muted line-through ml-1 a-self-end line-h">￥ {{item.oprice}} /天</view>
							</view>
						</view>
					</div>
				</div>
			</view>
		</view>
		<!-- </scroll-view> -->
	</div>
</template>

<script>
import price from '@/components/common/price.vue';
export default {
	components: {
		price
	},
	props: {
		params: Object, //forumLists 接口的其它参数
		type: String, //好物种草我的嗮图传 type 嗮图 userForumLists，赞过 userFabulousLists
		url: String // 判断是哪个社区模块调用的，imgCommunity 好物种草
	},

	data() {
		return {
			dataList: [], // 数据源
			firstList: [], // 第一列数组
			secondList: [], // 第二列数组
			last: false,
			height1: 0,
			height2: 0
		};
	},
	created() {
		this.init();
		this.getGoodthingList();
	},
	mounted() {
		var that = this;
		uni.$on('onReachBottom', function(data) {
			// console.log('onReachBottom');
			that.getGoodthingList();
		});
	},
	methods: {
		init() {
			this.dataList = [];
			this.firstList = [];
			this.secondList = [];
			this.last = false;
			this.height1 = 0;
			this.height2 = 0;
			// debugger
		},
		openDetail(){
			uni.navigateTo({
				url: '/pages/goods-detail/goods-detail'
			});
		},

		// 获取数据
		getGoodthingList() {
			this.page++;
			this.dataList = [
				{
					cover: '/static/images/demo/list/1.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo1.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo2.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo3.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo1.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo2.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo3.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo2.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo1.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/demo3.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				}
			];
			this.setDataList(this.dataList, 'cover');
		},
		/*
				函数说明：瀑布流获取图片高度计算方法，会把原数组拆分成两个数组返回
				参数说明：
				必填：
					arr：数组，里面有对象，对比对象里面的图片高度，返回两个数组
					attributeName：要根据数组里对象指定属性名称（图片的 Key）
				非必填：
					otherHeight：瀑布流的盒子其它组成部分的高度，例如点赞那列，因为瀑布流不单单是图片组成
					minHeight: 图片最小高度，如果没有图片，也会使用最小高度，相当于默认高度
					maxHeight:图片最大高度，高于就按照 maxHeight 算
			*/
		async setDataList(arr = [], attributeName, otherHeight = 100, minHeight = 10, maxHeight = 1000) {
			// var getSystemInfo = await wx.getSystemInfo()
			var height1 = this.height1;
			var height2 = this.height2;
			for (let i = 0; i < arr.length; i++) {
				try {
					let heightData = await uni.getImageInfo({
						src: arr[i][attributeName]
					});
					let ImgHeight = heightData.length == 1 ? minHeight : heightData[1].height; //如果长度等于1，就是图片有问题，这时候设置一个默认高度，如果长度等于2，下标1就有图片高度
					let imgWidth = heightData.length == 1 ? minHeight : heightData[1].width; //如果长度等于1，就是图片有问题，

					let height = ImgHeight;
					if (height < minHeight) height = minHeight;
					if (height > maxHeight) height = maxHeight;

					if (height1 <= height2) {
						height1 += height;
						// this.height1 = height1
						this.firstList.push(arr[i]);
					} else {
						height2 += height;
						// this.height2 = height2
						this.secondList.push(arr[i]);
					}
					this.height1 = height1 - height2;
					this.height2 = 0;
					// console.log(this.height1, this.height2);
				} catch (err) {
					console.log('getImageInfo-err', err);
				}
			}
		},
		// 加载更多图片
		loadMoreImages: function() {
			if (!this.last) {
				uni.showLoading({
					title: '加载中...'
				});
				this.getGoodthingList();
			}
			uni.hideLoading();
		}
	}
};
</script>

<style scoped lang="less">
.page {
	width: 100%;
}

.main {
	width: 100%;
	background: #f5f5f5;

	.view {
		width: 50%;
		display: inline-block !important;
		position: relative;
		vertical-align: top;
		padding: 10rpx;
		box-sizing: border-box;

		.content {
			box-shadow: 0 0 3px #ddd;
			border-radius: 4px;
			margin-bottom: 20rpx;
			box-sizing: border-box;
			background: white;

			.img-content {
				position: relative;
				.image {
					width: 100%;
					// height: 100%;
					border-top-right-radius: 4px;
					border-top-left-radius: 4px;
					box-sizing: border-box;
				}
			}
		}
	}
}
</style>
