<template>
	<view class="row p-2 border-bottom border-light-secondary animated fadeIn faster">
		<view class="span-6"><image :src="item.titlepic" mode="widthFix" class="w-100"></image></view>
		<view class="span-14 pl-3 d-flex flex-column">
			<view class="font-md font-weight br-line-2 line-h-md">{{ item.title }}</view>
			<view class="font text-light-muted line-h-sm mb-auto desc-break">{{ item.desc }}</view>
			<price :unit="item.unit">{{ item.pprice }}</price>
			<view class="font-sm text-light-muted">月销 {{ item.sales }} 好评率 {{ item.good_rate }}</view>
		</view>
	</view>
</template>

<script>
import price from '@/components/common/price.vue';
export default {
	components: {
		price
	},
	props: {
		item: Object,
		index: Number
	}
};
</script>

<style>
.desc-break {
	max-height: 2.4em;
	overflow: hidden;
	display: -webkit-box;
	word-break: break-all;
	text-overflow: ellipsis;
	-webkit-box-orient: vertical;
	-webkit-line-clamp: 2;
}
</style>
