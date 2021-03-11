<template>
	<view style="">
		<view class="p-2">
			<view class="font-lg line-h-sm">{{ detail.title }}</view>
			<view class="font text-light-muted mt-1 mb-3 line-h-sm">{{ detail.desc }}</view>
			<view class="d-flex j-sb">
				<price :unit="detail.unit" :deposit="detail.deposit" priceSize="font-lg" unitSize="font" :showDeposit="true">{{ detail.pprice }}</price>
				<text class="mr-2 text-light-muted">月交易量 {{ detail.sales }}</text>
			</view>
		</view>
		<uni-list-item>
			<view class="d-flex">
				<text class="mr-2 font-weight" style="font-size: 33rpx;">{{ detail.shopname }}</text>
				<view style="width: 20rpx;"></view>
				<view style="align-self:center;">
					<view class="t-icon t-icon-level4"></view>
					<view class="t-icon t-icon-level3"></view>
					<view class="t-icon t-icon-level2"></view>
					<view class="t-icon t-icon-level1"></view>
				</view>
			</view>
		</uni-list-item>
	</view>
</template>

<script>
import price from '@/components/common/price.vue';
import uniListItem from '@/components/uni-ui/uni-list-item/uni-list-item.vue';

export default {
	components: {
		price,
		uniListItem
	},
	props: ['detail']
};
</script>

<style></style>
