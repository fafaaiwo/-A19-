<template>
	<view class="bg-white m-2" style="border-radius: 15rpx; box-shadow: 0 0 5rpx #ddd;">
		<!-- <divider></divider> -->
		<!-- 头部 -->
		<view class="d-flex a-center p-2 border-bottom border-light-secondary"  @click.stop="openDetail">
			<text class="text-light-muted font-md">{{ item.create_time }}</text>
			<text class="ml-auto font-md" style="color: #FDBF2E;">{{ item.status_text }}</text>
		</view>
		<!-- 身体 -->
		<view class="px-2">
			<block v-for="(order,orderIndex) in item.order_items" :key="orderIndex">
				<order-list-item :item="order" :index="orderIndex"></order-list-item>
			</block>
		</view>

		<!-- 底部 -->
		<view class="d-flex a-center p-3">
			<text class="text-dark font-md ml-auto">共{{ item.total_num }}件商品，合计押金：￥{{ item.total_price }}</text>
		</view>
		<view class="row j-sb pb-2">
			<view class="d-flex a-center px-3 pb-2">
				<uni-combox :candidates="candidates" v-on:select="select"></uni-combox>
			</view>
			<!-- <uni-combox  :candidates="candidates"></uni-combox> -->
			<!-- <view class="d-flex a-center px-3 pb-2"><view class="rounded border border-light-secondary py-1 px-2 text-secondary" hover-class="bg-light-secondary">更多</view></view> -->
			<view class="d-flex a-center px-3 pb-2">
				<view class="rounded border border-light-secondary py-1 px-2 text-secondary" hover-class="bg-light-secondary" @click.stop="openExpress">修改地址</view>
				<view class="ml-2 rounded border border-light-secondary py-1 px-3 text-secondary" hover-class="bg-light-secondary" @click.stop="openAfterSale">去付款</view>
			</view>
		</view>
	</view>
</template>

<script>
import uniCombox from "@/components/uni-ui/uni-combox/uni-combox.vue"
import orderListItem from './order-list-item.vue';
export default {
	components: {
		uniCombox,
		orderListItem
	},
	props: { 
		item: Object,
		index: Number
	},
	data() {
		return {
			candidates:['联系卖家','取消订单']
		}
	},
	methods: {
		select(index) {
			console.log(this.candidates[index])
		},
		openDetail(){
			uni.navigateTo({
				url: '../../pages/order-detail/order-detail',
			});
		},
		openExpress(){
			uni.navigateTo({
				url: '/pages/express-detail/express-detail',
			});
		},
		openAfterSale(){
			uni.navigateTo({
				url: '/pages/after-sale/after-sale',
			});
		}
	}
};
</script>

<style>
page {
	background-color: #f5f5f5;
}
</style>
