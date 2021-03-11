<template>
	<view>
		<!-- 排序|筛选 -->
		<view class="d-flex border-top border-bottom a-center" 
		style="height: 100upx;">
			<view class="flex-1 d-flex a-center j-center font-md"
			v-for="(item,index) in screen.list" :key="index"
			@tap="changeScreen(index)">
				<text :class="screen.currentIndex === index ? 'main-text-color' : 'text-muted'">{{item.name}}</text>
				<view>
					<view class="iconfont icon-shengxu line-h0"
					:class="item.status===1?'main-text-color':'text-light-muted'">
					</view>
					<view class="iconfont icon-jiangxu line-h0"
					:class="item.status===2?'main-text-color':'text-light-muted'">
					</view>
				</view>
			</view>
			
			
			<view class="flex-1 d-flex a-center j-center main-text-color"
			@click="showRigth = true">
				筛选
			</view>
		</view>
		
		<!-- 抽屉 -->
		<uni-drawer :visible="showRigth" mode="right" @close="showRigth = false">
			<card headTitle="服务" :headBorderBottom="false" :headTitleWeight="false">
				
				<!-- 单选按钮组 -->
				<zcm-radio-group :label="label" 
				:selected.sync='label.selected'></zcm-radio-group>
				
			</card>
			<!-- 按钮 -->
			<view class="d-flex position-fixed bottom-0 right-0 w-100 border-top border-light-secondary">
				<view class="flex-1 main-bg-color text-white font-md py-2 text-center" hover-class="main-bg-hover-color">确定</view>
				<view class="flex-1 font-md py-2 text-center"
				hover-class="bg-light-secondary">重置</view>
			</view>
		</uni-drawer>
		
		<!-- 列表 -->
		<block v-for="(item,index) in list" :key="index">
			<search-list :item="item" :index="index"></search-list>
		</block>
		
	</view>
</template>

<script>
	import uniDrawer from "@/components/uni-ui/uni-drawer/uni-drawer.vue"
	import card from "@/components/common/card.vue"
	import zcmRadioGroup from "@/components/common/radio-group.vue"
	import searchList from "@/components/search-list/search-list.vue"
	export default {
		components: {
			uniDrawer,
			card,
			zcmRadioGroup,
			searchList
		},
		data() {
			return {
				list:[
					{
						title:"真无限蓝牙耳机雅致简约分体式入耳收纳盒充电收纳盒充电收纳盒充电收纳盒充电",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 /",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机雅致简约分体式入耳收纳盒充电收纳盒充电收纳盒充电收纳盒充电",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作 / 雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 /",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					},
					{
						title:"真无限蓝牙耳机",
						titlepic:"/static/images/demo/demo6.jpg",
						desc:"雅致简约 / 分体式入耳 / 收纳盒充电 / 蓝牙5.0 / 触控操作",
						unit:'天',
						pprice:10,
						sales:1300,
						good_rate:"98%"
					}
				],
				showRigth: false,
				screen:{
					currentIndex:0,
					list:[
						{ name:"综合",status:1 },
						{ name:"销量",status:0 },
						{ name:"价格",status:0 },
					]
				},
				label:{
					selected:0,
					list:[
						{name:"选项一"},
						{name:"选项二"},
						{name:"选项三"},
					]
				}
			}
		},
		methods: {
			changeScreen(index){
				// 判断当前点击是否已经是激活状态
				let oldIndex = this.screen.currentIndex
				let oldItem = this.screen.list[oldIndex]
				if (oldIndex === index) {
					return oldItem.status = oldItem.status === 1 ? 2 : 1
				}
				let newIitem = this.screen.list[index] 
				// 移除旧激活状态
				oldItem.status = 0
				this.screen.currentIndex = index
				// 增加新激活状态
				newIitem.status = 1
			},
		}
	}
</script>

<style>
</style>
