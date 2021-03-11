<template>
	<view style="background: #F5F5F5;">
		<loading-plus v-if="beforeReady"></loading-plus>
		<uni-nav-bar :right-text="isedit ? '完成' : '编辑'" title="购物车" statusBar :shadow="false" @clickRight="isedit = !isedit" :fixed="true"></uni-nav-bar>

		<!-- 购物车为空 -->
		<view class="py-5 d-flex a-center j-center bg-white" v-if="disableSelectAll">
			<view class="iconfont icon-gouwuche text-light-muted" style="font-size: 50upx;"></view>
			<text class="text-light-muted mx-2">购物车还是为空</text>
			<view class="px-2 py-1 border border-light-secondary rounded" hover-class="bg-light-secondary">去逛逛</view>
		</view>

		<!-- 购物车商品列表 -->
		<view class="px-2" v-else>
			<!-- 列表 -->
			<view class="flex-wrap border-bottom bg-white border-light-secondary px-1 mx-1 my-2 cart-item-list" v-for="(shopItem, shopIndex) in list" :key="shopIndex">
				<view class="d-flex a-center pt-3">
					<label class="radio d-flex a-center j-center flex-shrink" style="width: 90upx;height: 50upx;" @click="doSelectShop(shopIndex)">
						<radio :value="shopItem.shopid" :checked="checkedShop(shopIndex)" color="#FD6801" />
					</label>
					<view class="font-md font-weight">{{shopItem.shopName}}</view>
					<view class="iconfont icon-gengduo text-light-muted ml-1 font-md"></view>
				</view>
				<view v-for="(item, index) in shopItem.goodsList" :key="index">
					<view class="d-flex a-center pt-1">
						<label class="radio d-flex a-center j-center flex-shrink" style="width: 90upx;height: 90upx;" @click="selectItem({shop:shopIndex, goods:index})">
							<radio :value="item.id" :checked="item.checked" color="#FD6801" />
						</label>

						<image :src="item.cover" mode="widthFix" style="height: 180rpx;width: 180rpx;" class="border-light-secondary rounded flex-shrink"></image>

						<view class="flex-1 d-flex flex-column pl-2">
							<view class="text-dark br-line-1" style="font-size: 35upx;">{{ item.title }}</view>
							<!-- 规格属性 -->
							<view class="d-flex text-light-muted mb-1" :class="isedit ? ' p-1 bg-light mb-2' : ''" @tap.stop="doShowPopup({shop:shopIndex, goods:index})">
								<text class="mr-1" v-for="(attr, attrIndex) in item.attrs" :key="attrIndex">{{ attr.list[attr.selected].name }}</text>

								<view class="iconfont icon-xiala font ml-auto" v-if="isedit"></view>
							</view>
							<view class="row">
								<view>租价</view>
								<text class="ml-1 main-text-color">￥ {{ item.pprice }} / {{ item.unit }}</text>
							</view>
							<view class="row">
								<view>押金</view>
								<text class="ml-1 main-text-color">￥ {{ item.deposit }}</text>
							</view>
						</view>
					</view>
					<view class="d-flex j-end a-center text-light-muted pb-2" @tap="showNumBox(item, index)">
						<view class="mx-1">数量 x {{ item.num }}</view>
						<view class="mx-1">租期 x {{ item.period }}</view>
						<view :class="item.numBoxShow ? 'iconfont icon-shangla' : 'iconfont icon-xiala'" class="mx-2 font-md"></view>
					</view>
					<view v-if="item.numBoxShow">
						<view class="d-flex j-sb a-center pl-4 pr-1">
							<text class="ml-5">租赁数量（个）</text> 
							<uni-number-box :min="item.minnum" :value="item.num" :max="item.maxnum" @change="changeNum($event, item, index)"></uni-number-box>
						</view>
						<view class="d-flex j-sb a-center pt-2 pb-4 pl-4 pr-1">
							<text class="ml-5">预计租期（{{ item.unit }}）</text>
							<uni-number-box :min="item.minperiod" :value="item.period" :max="item.maxperiod" @change="changePeriod($event, item, index)"></uni-number-box>
						</view>
					</view>
				</view>
			</view>
		</view>
		
		
		 <view class="text-center main-text-color font-md font-weight mt-5">
			 为你推荐
		 </view>
		 <view class="position-relative d-flex a-center j-center text-secondary mb-4 pt-3">
			<view style="background: #F5F5F5;z-index: 2;" class="px-2 position-absolute">
				买的人还买了</view>
			<view class="position-absolute" style="height: 1upx;left: 0;right: 0;background-color: #DDDDDD;"></view>
		 </view>
		<waterfall-list></waterfall-list>
		
		
		
		<view style="height: 100upx;"></view>

		<!-- 合计 -->
		<view class="d-flex a-center bg-white position-fixed left-0 right-0 bottom-0 border-top border-light-secondary a-stretch" style="height: 100upx;z-index: 100;">
			<label class="radio d-flex a-center j-center flex-shrink" style="width: 120upx;" @click="doSelectAll">
				<radio color="#FD6801" :checked="checkedAll" :disabled="disableSelectAll" />
			</label>
			<template v-if="!isedit">
				<view class="flex-1 d-flex a-center j-center font-md">
					合计押金
					<view class="main-text-color ml-2">￥ {{ totalPrice }}</view>
				</view>
				<view class="flex-1 d-flex a-center j-center main-bg-color text-white font-md" hover-class="main-bg-hover-color" @tap="orderConfirm">结算</view>
			</template>
			<template v-else>
				<view class="flex-1 d-flex a-center j-center font-md main-bg-color text-white">移入收藏</view>
				<view class="flex-1 d-flex a-center j-center bg-danger text-white font-md" hover-class="main-bg-hover-color" @tap="doDelGoods">删除</view>
			</template>
		</view>

		<!-- 属性筛选框 -->
		<common-popup :popupClass="popupShow" @hide="doHidePopup">
			<view class="d-flex a-center" style="height: 275rpx;">
				<image src="../../static/images/demo/list/1.jpg" mode="widthFix" style="height: 180rpx;width: 180rpx;" class="border rounded"></image>
				<view class="pl-2">
					<!-- <price priceSize="font-lg" unitSize="font">2365</price> -->
					<price unit="天" :deposit="2366" priceSize="font-lg" unitSize="font" :showDeposit="true">2366</price>
					<view class="d-block mt-2">
						<text class="mr-1" v-for="(attr, attrIndex) in popupData.attrs" :key="attrIndex">{{ attr.list[attr.selected].name }}</text>
					</view>
				</view>
				<view class="iconfont icon-guanbi ml-auto a-self-start" @tap="doHidePopup"></view>
			</view>
			<!-- <scroll-view scroll-y class="w-100" style="height: 900rpx;">
				<card :headTitle="item.title" :headTitleWeight="false" :headBorderBottom="false" :key="index" v-for="(item, index) in popupData.attrs">
					<zcm-radio-group :label="item" :selected.sync="item.selected"></zcm-radio-group>
				</card>
				<view class="d-flex j-sb a-center p-2 border-top border-light-secondary">
					<text>购买数量</text>
					<uni-number-box :min="1" :value="popupData.num" @change="popupData.num = $event"></uni-number-box>
				</view>
			</scroll-view> -->

			<scroll-view scroll-y class="w-100" style="height: 900rpx;">
				<card :headTitle="item.title" :headTitleWeight="false" :headBorderBottom="false" :key="index" v-for="(item, index) in popupData.attrs">
					<zcm-radio-group :label="item" :selected.sync="item.selected"></zcm-radio-group>
				</card>
				<view class="d-flex j-sb a-center mt-4 p-4 border-top border-light-secondary">
					<text>租赁时间（单位：天）</text>
					<uni-number-box :min="popupData.minperiod" :value="popupData.period" :max="popupData.maxperiod" @change="popupData.period = $event"></uni-number-box>
				</view>
				<view class="d-flex j-sb a-center p-4">
					<text>租赁数量（库存：100）</text>
					<uni-number-box :min="popupData.minnum" :value="popupData.num" :max="popupData.maxnum" @change="popupData.num = $event"></uni-number-box>
				</view>
			</scroll-view>

			<view
				class="main-bg-color text-white font-md d-flex a-center j-center"
				hover-class="main-bg-hover-color"
				style="height: 100rpx;margin-left: -30rpx;margin-right: -30rpx;"
				@tap.stop="doHidePopup"
			>
				确定
			</view>
		</common-popup>
	</view>
</template>

<script>
import uniNavBar from '@/components/uni-ui/uni-nav-bar/uni-nav-bar.vue';
import price from '@/components/common/price.vue';
import uniNumberBox from '@/components/uni-ui/uni-number-box/uni-number-box.vue';
import card from '@/components/common/card.vue';
import zcmRadioGroup from '@/components/common/radio-group.vue';
import commonPopup from '@/components/common/common-popup.vue';
import commonList from '@/components/common/common-list.vue';
import waterfallList from '@/components/common/waterfall-list.vue';
import { mapState, mapGetters, mapActions, mapMutations } from 'vuex';
import loading from "@/common/mixin/loading.js"
export default {
	mixins:[loading],
	components: {
		uniNavBar,
		price,
		uniNumberBox,
		card,
		zcmRadioGroup,
		commonPopup,
		commonList,
		waterfallList
	},
	data() {
		return {
			isedit: false
		};
	},
	computed: {
		...mapState({
			list: state => state.cart.list,
			popupShow: state => state.cart.popupShow
		}),
		...mapGetters(['checkedAll', 'checkedShop', 'totalPrice', 'disableSelectAll', 'popupData'])
	},
	onReachBottom() {
		// console.log("触底")
		uni.$emit('onReachBottom');
	},
	methods: {
		...mapActions(['doSelectAll','doSelectShop', 'doDelGoods', 'doShowPopup', 'doHidePopup']),
		...mapMutations(['selectItem']),
		changeNum(e, item, index) {
			item.num = e;
		},
		changePeriod(e, item, index) {
			item.period = e;
		},
		showNumBox(item, index) {
			item.numBoxShow = !item.numBoxShow;
			this.$forceUpdate();
		},
		orderConfirm(){
			uni.navigateTo({
				url: '../order-confirm/order-confirm'
			});
		}
	}
};
</script>

<style>
.cart-item-list {
	border-radius: 20rpx;
}
</style>
