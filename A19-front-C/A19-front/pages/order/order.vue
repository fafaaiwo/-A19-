<template>
	<view>
		<!-- 顶部选项卡 -->
		<scroll-view scroll-x class="border-bottom scroll-row" style="height: 80rpx;" :scroll-into-view="scrollinto" :scroll-with-animation="true">
			<view
				class="scroll-row-item px-3"
				@click="changeTab(index)"
				style="height: 80rpx;line-height: 80rpx;"
				v-for="(item, index) in tabBars"
				:key="index"
				:class="tabIndex === index ? 'main-text-color' : ''"
				:id="'tab' + index"
			>
				<text class="font-md">{{ item.name }}</text>
			</view>
		</scroll-view>
		<swiper style="background-color: #f5f5f5;" :duration="150" :current="tabIndex" :style="'height:' + scrollH + 'px;'" @change="onChangeTab">
			<swiper-item v-for="(order, orderindex) in orderitems" :key="orderindex">
				<scroll-view scroll-y="true" :style="'height:' + scrollH + 'px;'" @scrolltolower="loadmore(orderindex)">
					<!-- 订单列表 -->

					<view class="position-relative" style="min-height: 440rpx;">
						<template v-if="order.list.length > 0">
							<!-- 订单列表 -->
							<block v-for="(item, index) in order.list" :key="index"><to-pay :item="item" :index="index"></to-pay></block>
						</template>
						<!-- 默认无 -->
						<template v-else>
							<no-thing :icon="order.no_thing" :msg="order.msg"></no-thing>
						</template>
					</view>
				</scroll-view>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
// 模拟后端数据
let demoTabBars = [
	{
		name: '全部'
	},
	{
		name: '待付款'
	},
	{
		name: '待发货'
	},
	{
		name: '待收货'
	},
	{
		name: '使用中'
	},
	{
		name: '已退还'
	},
	{
		name: '已购买'
	},
	{
		name: '待评价'
	},
	{
		name: '退款/售后'
	}
];

let demo1 = {
	name: '全部',
	no_thing: 'no_pay',
	msg: '你还没有订单',
	list: [
		{
			create_time: '2019-09-10 10:20',
			state:0,
			status_text: '等待买家付款',
			order_items: [
				{
					cover: '/static/images/demo/demo6.jpg',
					title: '小米8',
					deposit: 1999.0,
					pprice: 20.00,
					attrs: '金色 标配',
					sendtime: '3天',
					unit:'天',
					num: 1,
					period:10
				},
				{
					cover: '/static/images/demo/demo6.jpg',
					title: '小米8',
					deposit: 1999.0,
					pprice: 20.00,
					attrs: '金色 标配',
					sendtime: '3天',
					unit:'天',
					num: 1,
					period:10
				},
				{
					cover: '/static/images/demo/demo6.jpg',
					title: '小米8',
					deposit: 1999.0,
					pprice: 20.00,
					attrs: '金色 标配',
					sendtime: '3天',
					unit:'天',
					num: 1,
					period:10
				}
			],
			total_num: 3,
			total_price: 299.0
		},
		{
			create_time: '2019-09-10 10:20',
			status_text: '已发货',
			state:1,
			order_items: [
				{
					cover: '/static/images/demo/demo6.jpg',
					title: '小米8',
					deposit: 1999.0,
					pprice: 20.00,
					attrs: '金色 标配',
					sendtime: '3天',
					unit:'天',
					num: 1,
					period:10
				}
			],
			total_num: 3,
			total_price: 299.0
		}
	]
};

let demo2 = [];

import swiperImage from '@/components/index/swiper-image.vue';
import indexNav from '@/components/index/index-nav.vue';
import threeAdv from '@/components/index/three-adv.vue';
import card from '@/components/common/card.vue';
import commonList from '@/components/common/common-list.vue';
import noThing from "@/components/common/no-thing.vue"
import toPay from "@/components/order/to-pay.vue"
export default {
	components: {
		swiperImage,
		indexNav,
		threeAdv,
		card,
		commonList,
		noThing,
		toPay
	},
	data() {
		return {
			scrollinto: '',
			scrollH: 500,
			tabIndex: 0,
			tabBars: [],
			orderitems: []
		};
	},
	onLoad() {
		// 获取可视区域高度
		uni.getSystemInfo({
			success: res => {
				this.scrollH = res.windowHeight - uni.upx2px(82);
			}
		});
		// 初始化事件
		this.__init();
	},
	methods: {
		// 初始化事件
		__init() {
			// 获取顶部选项卡
			this.tabBars = demoTabBars;
			// 根据顶部选项卡生成页面
			let arr = [];
			for (var i = 0; i < this.tabBars.length; i++) {
				let obj = {
					list: [],
					// 1.上拉加载更多 2.加载中... 3.没有更多了
					loadtext: '上拉加载更多'
				};
				// 获取首屏数据
				if (i === 0) {
					obj.list = demo1.list;
				}

				arr.push(obj);
			}
			this.orderitems = arr;
		},
		// 切换选项卡
		changeTab(index) {
			if (this.tabIndex === index) {
				return;
			}
			this.tabIndex = index;
			this.scrollinto = 'tab' + index;
			this.addData();
		},
		// 监听滑动列表
		onChangeTab(e) {
			this.changeTab(e.detail.current);
		},
		// 加载数据
		addData() {
			// 拿到当前索引
			let index = this.tabIndex;
			// 请求数据库
			this.orderitems[index] = demo1;
		},
		// 上拉加载更多
		loadmore(index) {
			let item = this.orderitems[index];
			// 是否处于可加载状态
			// if (item.loadtext !== '上拉加载更多') return;
			// // 模拟加载
			// item.loadtext = '加载中...';
			// setTimeout(() => {
			// 	// 加载数据
			// 	item.list = [...item.list, ...demo2];
			// 	// 恢复状态
			// 	item.loadtext = '上拉加载更多';
			// }, 2000);
		}
	}
};
</script>

<style></style>
