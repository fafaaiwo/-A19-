<template>
	<!-- <scroll-view scroll-y="true" :style="'height:' + scrollH + 'px;'" @scrolltolower=""> -->
	<view class="">
		<swiper-image :resdata="swiperData" />

		<index-nav :resdata="navData" />
		<divider />

		<waterfall-list></waterfall-list>
		<!-- 上拉加载更多 -->
<!-- 		<divider />
		<view class="d-flex a-center j-center text-light-muted font-md py-3">{{ loadtext }}</view> -->
	</view>
	<!-- </scroll-view> -->
</template>

<script>
// 模拟后端数据

import swiperImage from '@/components/index/swiper-image.vue';
import indexNav from '@/components/index/index-nav.vue';
import threeAdv from '@/components/index/three-adv.vue';
import card from '@/components/common/card.vue';
import commonList from '@/components/common/common-list.vue';
import waterfallList from '@/components/common/waterfall-list.vue';

export default {
	components: {
		swiperImage,
		indexNav,
		threeAdv,
		card,
		commonList,
		waterfallList
	},
	data() {
		return {
			swiperData: [{ src: '../../static/images/demo/demo4.jpg' }, { src: '../../static/images/demo/demo4.jpg' }, { src: '../../static/images/demo/demo4.jpg' }],
			navData: [
				{ src: '/static/images/indexnav/1.png', text: '新品发布' },
				{ src: '/static/images/indexnav/2.gif', text: '小米众筹' },
				{ src: '/static/images/indexnav/3.gif', text: '以旧换新' },
				{ src: '/static/images/indexnav/4.gif', text: '一分换团' },
				{ src: '/static/images/indexnav/5.gif', text: '超值特卖' },
				{ src: '/static/images/indexnav/6.gif', text: '小米秒杀' },
				{ src: '/static/images/indexnav/7.gif', text: '真心想要' },
				{ src: '/static/images/indexnav/8.gif', text: '电视热卖' },
				{ src: '/static/images/indexnav/9.gif', text: '家电热卖' },
				{ src: '/static/images/indexnav/10.gif', text: '米粉卡' }
			],
			scrollH: 500,
			news_list: [],
			loadtext: '上拉加载更多'
		};
	},
	onLoad() {
		// 获取可视区域高度
		uni.getSystemInfo({
			success: res => {
				this.scrollH = res.windowHeight;
			}
		});
		// 初始化事件
		this.__init();
	},
	onNavigationBarSearchInputClicked() {
		uni.navigateTo({
			url: '../search/search'
		});
	},
	onReachBottom() {
		// console.log("触底")
		uni.$emit('onReachBottom');
	},
	methods: {
		// 初始化事件
		__init() {
			// this.news_list = demo1;
		},

		// 上拉加载更多
		loadmore() {
			let news = this.news_list;
			// 是否处于可加载状态
			if (this.loadtext !== '上拉加载更多') return;
			// 模拟加载
			this.loadtext = '加载中...';
			setTimeout(() => {
				// 加载数据
				this.news_list = [...news, ...demo2];
				// 恢复状态
				this.loadtext = '上拉加载更多';
			}, 2000);
		}
	}
};
</script>

<style></style>
