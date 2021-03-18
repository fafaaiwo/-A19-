<template>
	<view>
		<!-- 商品详情大图 -->
		<swiper-image :resdata="banners" height="750" preview></swiper-image>
		<!-- 基础详情 -->
		<base-info :detail="detail"></base-info>

		<!-- 属性选择 -->
		<view class="p-2">
			<view class="choose-property">
				<uni-list-item @click="show('attr')">
					<view class="d-flex">
						<text class="mr-2 text-muted">已选</text>
						<text>九成新 红色 按天借</text>
					</view>
				</uni-list-item>
				<uni-list-item @click="show('express')">
					<view class="d-flex">
						<text class="mr-2 text-muted">配送</text>
						<text class="mr-2">湖北 武汉 洪山区</text>
						<text class="main-text-color">有现货</text>
					</view>
				</uni-list-item>
				<uni-list-item extraWidth="15%" @click="show('service')">
					<view class="d-flex a-center">
						<view class="text-muted font d-flex a-center mr-2">
							<view class="iconfont icon-finish main-text-color"></view>
							只租不卖
						</view>
						<view class="text-muted font d-flex a-center mr-2">
							<view class="iconfont icon-finish main-text-color"></view>
							到店自提
						</view>
						<view class="text-muted font d-flex a-center mr-2">
							<view class="iconfont icon-finish main-text-color"></view>
							七天以上包邮
						</view>
					</view>
				</uni-list-item>
			</view>
		</view>

		<!-- 横向滚动评论 
			外层 w620*h380
			头像 w70*h70
			图片 h115
		-->
		<scroll-comments :comments="comments"></scroll-comments>

		<!-- 商品详情 -->
		<view class="py-4"><u-parse className="uparse" :content="context" @preview="preview" @navigate="navigate"></u-parse></view>
		<!-- 热门推荐 -->
		<card headTitle="热门推荐" :headBorderBottom="false">
			<view class="row j-sb"><common-list v-for="(item, index) in hotList" :key="index" :item="item" :index="index"></common-list></view>
		</card>

		<!-- 底部操作条 -->
		<bottom-btn @show="show('attr')"></bottom-btn>

		<!-- 属性筛选框 -->
		<common-popup :popupClass="popup.attr" @hide="hide('attr')">
			<!-- 商品信息(275rpx) 图片 180*180 -->
			<view class="d-flex a-center" style="height: 275rpx;">
				<image src="../../static/images/demo/list/1.jpg" mode="widthFix" style="height: 180rpx;width: 180rpx;" class="border rounded"></image>
				<view class="pl-2">
					<price :unit="detail.unit" :deposit="detail.deposit" priceSize="font-lg" unitSize="font" :showDeposit="true">{{detail.pprice}}</price>
					<text class="d-block">九成新 红色 按天借</text>
				</view>
				<view class="iconfont icon-guanbi ml-auto a-self-start" @tap="hide('attr')"></view>
			</view>
			<!-- 表单部分(900rpx) -->
			<scroll-view scroll-y class="w-100" style="height: 900rpx;">
				<card :headTitle="item.title" :headTitleWeight="false" :headBorderBottom="false" :key="index" v-for="(item, index) in selects">
					<zcm-radio-group :label="item" :selected.sync="item.selected"></zcm-radio-group>
				</card>
				<view class="d-flex j-sb a-center mt-4 p-4 border-top border-light-secondary">
					<text>租赁时间（单位：天）</text>
					<uni-number-box :min="detail.minperiod" :value="detail.period" :max="detail.maxperiod" @change="detail.period = $event"></uni-number-box>
				</view>
				<view class="d-flex j-sb a-center p-4">
					<text>租赁数量（库存：100）</text>
					<uni-number-box :min="detail.minnum" :value="detail.num" :max="detail.maxnum" @change="detail.num = $event"></uni-number-box>
				</view>
			</scroll-view>
			<!-- 按钮(100rpx) -->
			<view
				class="main-bg-color text-white font-md d-flex a-center j-center"
				hover-class="main-bg-hover-color"
				style="height: 100rpx;margin-left: -35rpx;margin-right: -35rpx;"
				@tap.stop="addCart">
				立即租赁
			</view>
		</common-popup>
		
		<!-- 收货地址 -->
		<common-popup :popupClass="popup.express" @hide="hide('express')">
			<view class="d-flex j-center font-md border-bottom border-light-secondary" style="height: 100rpx;">
				<view class="font-md" style="margin-left: 285rpx;align-self:center;">收货地址</view>
				<view class="iconfont icon-guanbi a-self-start ml-auto" @tap="hide('express')"></view>
			</view>
			<scroll-view scroll-y class="w-100" style="height: 1075rpx;">
				<uni-list-item v-for="(item,index) in addrList" :key="index">
					<view class="iconfont icon-dingwei font-weight font-md"
					>{{item.name}}</view>
					<view class="font text-light-muted">
						{{item.addr}} {{item.detailAddr}}
					</view>
				</uni-list-item>
			</scroll-view>
			<!-- 
			 按钮(100rpx)
			 -->
			 <view class="main-bg-color text-white font-md d-flex a-center j-center" hover-class="main-bg-hover-color" style="height: 100rpx;margin-left: -30rpx;margin-right: -30rpx;" @tap.stop="openCreatePath">
			 	使用新地址
			 </view>
		</common-popup>
		
		<!-- 服务说明 -->
		<common-popup :popupClass="popup.service" @hide="hide('service')">
			<view class="d-flex j-center font-md border-bottom border-light-secondary" style="height: 100rpx;">
				<view class="font-md" style="margin-left: 285rpx;align-self:center;">服务说明</view>
				<view class="iconfont icon-guanbi a-self-start ml-auto" @tap="hide('service')"></view>
			</view>
			<scroll-view scroll-y class="w-100" style="height: 1075rpx;">
				<view class="py-1">
					<view class="d-flex a-center">
						<view class="iconfont icon-finish main-text-color mr-1"></view>
						小米自营
					</view>
					<text class="text-light-muted font pl-4">
						不管满多少，就是不包邮
					</text>
				</view>
			</scroll-view>
			<!-- 
			 按钮(100rpx)
			 -->
			 <view class="main-bg-color text-white font-md d-flex a-center j-center" hover-class="main-bg-hover-color" style="height: 100rpx;margin-left: -30rpx;margin-right: -30rpx;" @tap.stop="hide('service')">
			 	确定
			 </view>
		</common-popup>
		
	</view>
</template>

<script>
import swiperImage from '@/components/index/swiper-image.vue';
import baseInfo from '@/components/goods-detail/base-info.vue';
import uniListItem from '@/components/uni-ui/uni-list-item/uni-list-item.vue';
import scrollComments from '@/components/goods-detail/scroll-comments.vue';
import uParse from '@/components/uni-ui/uParse/src/wxParse.vue';
import card from '@/components/common/card.vue';
import commonList from '@/components/common/common-list.vue';
import bottomBtn from '@/components/goods-detail/bottom-btn.vue';
import commonPopup from '@/components/common/common-popup.vue';
import price from '@/components/common/price.vue';
import zcmRadioGroup from '@/components/common/radio-group.vue';
import uniNumberBox from '@/components/uni-ui/uni-number-box/uni-number-box.vue';
import {mapState, mapMutations} from "vuex"

var htmlString = `
	<p>
		<img src="https://i8.mifile.cn/v1/a1/9c3e29dc-151f-75cb-b0a5-c423a5d13926.webp">
		<img src="https://i8.mifile.cn/v1/a1/f442b971-379f-5030-68aa-3b0accb8c2b9.webp">
		<img src="https://i8.mifile.cn/v1/a1/63b700b6-643e-ec18-fdf3-da66b4b4173f.webp">
		<img src="https://i8.mifile.cn/v1/a1/e9dbf276-193e-11c4-99a6-3097fde82350.webp">
		<img src="https://i8.mifile.cn/v1/a1/1249d3ee-2990-a2b4-28d9-f719c2417b1f.webp">
		<img src="https://i8.mifile.cn/v1/a1/97c79915-64b2-808c-53b4-4345652a179f.webp">
		<img src="https://i8.mifile.cn/v1/a1/cd0fbe1e-a1b3-a87a-f4a6-6fb08ec54931.webp">
	</p>
	`;

export default {
	components: {
		swiperImage,
		baseInfo,
		uniListItem,
		scrollComments,
		uParse,
		card,
		commonList,
		bottomBtn,
		commonPopup,
		price,
		zcmRadioGroup,
		uniNumberBox
	},
	data() {
		return {
			selects: [
				{
					title: '成色',
					selected: 0,
					list: [{ name: '全新' }, { name: '九成新' }, { name: '八成新' }]
				},
				{
					title: '颜色',
					selected: 0,
					list: [{ name: '红色' }, { name: '黑色' }]
				},
				{
					title: '租赁方式',
					selected: 0,
					list: [{ name: '按月' }, { name: '按天' }, { name: '按小时' }]
				}
			],
			popup:{
				attr:"none",
				express:"none",
				service:"none"
			},
			comments: [
				{
					userpic: '/static/images/demo/demo6.jpg',
					username: '用户A',
					state: 0,
					create_time: '2021-01-01',
					goods_num: 666,
					context: '图片仅供参考，以实物为准！谨慎下单懂的都懂。',
					imglist: ['/static/images/demo/demo6.jpg', '/static/images/demo/demo6.jpg', '/static/images/demo/demo6.jpg']
				},
				{
					userpic: '/static/images/demo/demo6.jpg',
					username: '用户B',
					state: 1,
					create_time: '2021-01-01',
					goods_num: 123,
					context: '我觉得还不错！',
					imglist: ['/static/images/demo/demo6.jpg', '/static/images/demo/demo6.jpg']
				},
				{
					userpic: '/static/images/demo/demo6.jpg',
					username: '用户C',
					state: 0,
					create_time: '2021-01-01',
					goods_num: 345,
					context: '图片仅供参考，以实物为准！！',
					imglist: ['/static/images/demo/demo6.jpg']
				}
			],
			banners: [
				{ src: 'https://i8.mifile.cn/v1/a1/6d13e060-d5c5-e610-88d0-80816fa2b0ce.webp' },
				{ src: 'https://i8.mifile.cn/v1/a1/aff0b715-e63a-68f3-91b0-46a22543555e.webp' }
			],
			

			detail: {
				id: 20,
				title: '小米MIX3 6GB+128GB 磁动力滑盖全面屏 / 前后旗舰AI双摄 / 四曲面彩色陶瓷机身 / 高效10W无线充电',
				desc: '磁动力滑盖全面屏 / 前后旗舰AI双摄 / 四曲面彩色陶瓷机身 / 高效10W无线充电',
				cover:"/static/images/demo/list/1.jpg",
				unit: '天',
				pprice: 10,
				oprice: 10,
				deposit: 3299,
				sales: 100,
				num: 1,
				minnum: 1,
				maxnum: 100,
				period: 10,
				minperiod: 10,
				maxperiod: 100, // 商品租期
				shopid: 9, 
				shopname: '珞珈自强超市信部店'
			},
			context: htmlString,
			hotList: [
				{
					cover: '/static/images/demo/list/1.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提线下退还',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/list/1.jpg',
					title: '米家空调最新1.5匹可租可借包邮支持自提',
					unit: '月',
					oprice: 600,
					pprice: 90
				},
				{
					cover: '/static/images/demo/list/1.jpg',
					title: '米家空调最新1.5匹可租可借包邮',
					unit: '时',
					oprice: 1,
					pprice: 0.01
				},
				{
					cover: '/static/images/demo/list/1.jpg',
					title: '米家空调最新1.5匹可租可借',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/list/1.jpg',
					title: '米家空调最新1.5匹',
					unit: '天',
					oprice: 20,
					pprice: 3
				},
				{
					cover: '/static/images/demo/list/1.jpg',
					title: '米家空调最新',
					unit: '天',
					oprice: 20,
					pprice: 3
				}
			]
		};
	},
	// 监听页面返回事件
	onBackPress() {
	// 关闭模态框
		for (let key in this.popup) {
			if (this.popup[key] !== 'none') {
				this.hide(key)
				return true
			}
		}
	},
	computed: {
		...mapState({
			addrList:state=>state.addr.list
		})
	},
	methods: {
		...mapMutations([
				'addGoodsToCart'
			]),
		// 加入购物车
		addCart(){
			// 组织数据
			let goods = this.detail
			goods['checked'] = false
			goods['numBoxShow'] = false
			goods['attrs'] = this.selects
			// 加入购物车
			this.addGoodsToCart(goods)
			// 隐藏筛选框
			this.hide('attr')
			// 成功提示
			uni.showToast({
				title: '加入成功'
			});
		},
		openCreatePath(){
			uni.navigateTo({
				url: '../user-addr-edit/user-addr-edit',
			});
			this.hide('express')
		},
		hide(key){
			this.popup[key] = 'hide'
			setTimeout(()=>{
				this.popup[key] = 'none'
			}, 200);
		},
		show(key){
			this.popup[key] = 'show'
		},
		preview(src, e) {
			// do something
			console.log('src: ' + src);
		},
		navigate(href, e) {
			// 如允许点击超链接跳转，则应该打开一个新页面，并传入href，由新页面内嵌webview组件负责显示该链接内容
			console.log('href: ' + href);
		}
	}
};
</script>

<style>
.choose-property {
	border-radius: 10rpx;
	-moz-box-shadow: 2px 2px 10px #eeeeee;
	-webkit-box-shadow: 2px 2px 10px #eeeeee;
	box-shadow: 2px 2px 10px #eeeeee;
}
.uparse .p {
	padding: 0 !important;
}
.uparse view,
.uparse uni-view {
	line-height: 0 !important;
}
</style>
