<template>
	<view>
		<uni-swipe-action>
			<uni-swipe-action-item v-for="(item, index) in list" :key="index" :right-options="options" :threshold="0" @click="bindClick($event, index)">
				<view class="w-100">
					<uni-list-item :hideHover="!isChoose" @click="choose(item)">
						<view class="text-secondary">
							<view class="d-flex a-center">
								<text class="font-weight text-dark mr-2" style="font-size: 35rpx;">{{ item.name }}</text>
								{{ item.phone }}
								<text v-if="item.isDefault" class="main-text-color ml-1">[默认]</text>
							</view>
							<view>{{ item.addr }}</view>
							<view>{{ item.detailAddr }}</view>
						</view>
					</uni-list-item>
				</view>
			</uni-swipe-action-item>
		</uni-swipe-action>
	</view>
</template>

<script>
import uniListItem from '@/components/uni-ui/uni-list-item/uni-list-item.vue';
import uniSwipeAction from '@/components/uni-ui/uni-swipe-action/uni-swipe-action.vue';
import uniSwipeActionItem from '@/components/uni-ui/uni-swipe-action-item/uni-swipe-action-item.vue';
import { mapState, mapMutations } from 'vuex';

export default {
	components: {
		uniListItem,
		uniSwipeAction,
		uniSwipeActionItem
	},
	data() {
		return {
			options: [
				{
					text: '修改',
					style: {
						backgroundColor: '#fe9c01'
					}
				},
				{
					text: '删除',
					style: {
						backgroundColor: '#dd524d'
					}
				}
			]
		};
	},
	computed: {
		...mapState({
			list: state => state.addr.list
		})
	},
	// 监听导航栏按钮点击
	onNavigationBarButtonTap(e) {
		if (e.index === 0) {
			uni.navigateTo({
				url: '../user-addr-edit/user-addr-edit'
			});
		}
	},
	onLoad(e) {
		if (e.type === 'choose') {
			this.isChoose = true
		}
	},
	methods: {
		...mapMutations(['delAddr']),
		bindClick(value, i) {
			switch (value.index) {
				case 0: // 修改
					let obj = JSON.stringify({
						index:i,
						item:this.list[i]
					})
					setTimeout(()=> {
						uni.navigateTo({
							url: '../user-addr-edit/user-addr-edit?data='+obj,
						});
					},50);
						break;
				case 1: // 删除
					uni.showModal({
						content: '要删除该收货地址吗？',
						success: res => {
							if (res.confirm) {
								this.delAddr(i);
								uni.showToast({
									title: '删除成功'
								});
							}
						}
					});
					break;
			}
		},
		// 选择收货地址
		choose(item){
			if (this.isChoose) {
				// 通知订单提交页修改收货地址
				uni.$emit('chooseAddr',item)
				// 关闭当前页面
				uni.navigateBack({
					delta: 1
				});
			}
		}
	}
};
</script>

<style></style>
