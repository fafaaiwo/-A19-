<template>
	<view class="uni-combox">
		<view class="uni-combox__input-box">
			<view class=""><view class="rounded border border-light-secondary py-1 px-2 ml-1 text-secondary" hover-class="bg-light-secondary" @click="toggleSelector">更多</view></view>
			<view class="uni-combox__selector" v-if="showSelector">
				<scroll-view scroll-y="true" class="uni-combox__selector-scroll">
					<view class="uni-combox__selector-empty" v-if="candidatesLength === 0">
						<text>{{emptyTips}}</text>
					</view>
					<view class="uni-combox__selector-item" v-for="(item,index) in candidates" :key="index" @click="onSelectorClick(index)">
						<text>{{item}}</text>
					</view>
				</scroll-view>
			</view>
		</view>
	</view>
</template>

<script>
	/**
	 * Combox 组合输入框
	 * @description 组合输入框一般用于既可以输入也可以选择的场景
	 * @tutorial https://ext.dcloud.net.cn/plugin?id=1261
	 * @property {String} label 左侧文字
	 * @property {String} labelWidth 左侧内容宽度
	 * @property {String} placeholder 输入框占位符
	 * @property {Array} candidates 候选项列表
	 * @property {String} emptyTips 筛选结果为空时显示的文字
	 * @property {String} value 组合框的值
	 */
	export default {
		name: 'uniCombox',
		props: {
			candidates: {
				type: Array,
				default () {
					return []
				}
			},
			emptyTips: {
				type: String,
				default: '无匹配项'
			}
		},
		data() {
			return {
				showSelector: false
			}
		},
		computed: {
			candidatesLength() {
				return this.candidates.length
			}
		},
		methods: {
			toggleSelector() {
				this.showSelector = !this.showSelector
			},
			onSelectorClick(index) {
				this.showSelector = false
				this.$emit('select', index)
			}
		}
	}
</script>

<style scoped>
	.uni-combox {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		height: 40px;
		flex-direction: row;
		align-items: center;
	}

	.uni-combox__label {
		font-size: 16px;
		line-height: 22px;
		padding-right: 10px;
		color: #999999;
	}

	.uni-combox__input-box {
		position: relative;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex: 1;
		flex-direction: row;
		align-items: center;
	}

	.uni-combox__input {
		flex: 1;
		font-size: 16px;
		height: 22px;
		line-height: 22px;
	}

	.uni-combox__input-arrow {
		padding: 10px;
	}

	.uni-combox__selector {
		box-sizing: border-box;
		position: absolute;
		top: 42px;
		left: -20%;
		width: 160%;
		background-color: #FFFFFF;
		border-radius: 6px;
		box-shadow: #DDDDDD 4px 4px 8px, #DDDDDD -4px -4px 8px;
		z-index: 2;
	}

	.uni-combox__selector-scroll {
		max-height: 200px;
		box-sizing: border-box;
	}

	.uni-combox__selector::before {
		content: '';
		position: absolute;
		width: 0;
		height: 0;
		border-bottom: solid 6px #FFFFFF;
		border-right: solid 6px transparent;
		border-left: solid 6px transparent;
		left: 50%;
		top: -6px;
		margin-left: -6px;
	}

	.uni-combox__selector-empty,
	.uni-combox__selector-item {
		/* #ifdef APP-NVUE */
		display: flex;
		/* #endif */
		line-height: 36px;
		font-size: 14px;
		text-align: center;
		border-bottom: solid 1px #DDDDDD;
		margin: 0px 10px;
	}

	.uni-combox__selector-empty:last-child,
	.uni-combox__selector-item:last-child {
		border-bottom: none;
	}
</style>