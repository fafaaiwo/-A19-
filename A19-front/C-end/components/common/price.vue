<template>
	<view class="row" >
		<view class="d-flex line-h" :class="priceSize+' '+color">
			<text class="a-self-start" :class="unitSize">￥</text>
			<slot />
			<view style="width: 10rpx;"></view>
			<text class="pr-1 a-self-end" :class="unitSize">/{{unit}}</text>
		</view>
		<view class="ml-2 tag-blue" v-if="showDeposit">押金{{deposit}}元</view>
	</view>
	
</template>

<script>
	export default {
		props:{
			unit:{
				type:String,
				default:""
			},
			deposit:{
				type:Number,
				default:0
			},
			priceSize:{
				type:String,
				default:"font-md"
			},
			unitSize:{
				type:String,
				default:"font-sm"
			},
			showDeposit:{
				type:Boolean,
				default:false
			},
			color:{
				type:String,
				default:"main-text-color"
			}
		}
	}
</script>

<style>
</style>
