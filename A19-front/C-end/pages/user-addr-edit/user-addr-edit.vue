<template>
	<view>
		
		<view class="p-2 border-bottom d-flex a-center bg-white">
			<view class="font-md text-secondary mr-1 flex-shrink">
				收货人：</view>
			<input class="px-1 font-md flex-1" type="text" 
			v-model="form.name" />
		</view>
		<view class="p-2 d-flex a-center bg-white">
			<view class="font-md text-secondary mr-1 flex-shrink">
				手机号码：</view>
			<input class="px-1 font-md flex-1" type="text" 
			v-model="form.phone" />
		</view>
		
		<divider></divider>
		
		<view class="p-2 border-bottom d-flex a-center bg-white">
			<view class="font-md text-secondary mr-1 flex-shrink">
				所在地区：</view>
			<input class="px-1 font-md flex-1" type="text" disabled 
			@click="showMulLinkageThreePicker" placeholder="请选择所在地区"
			:value="form.addr"/>
			
			<mpvue-city-picker :themeColor="themeColor" ref="mpvueCityPicker" :pickerValueDefault="pickerValue" @onConfirm="onConfirm"></mpvue-city-picker>
			
		</view>
		<view class="p-2 d-flex a-center bg-white">
			<view class="font-md text-secondary mr-1 flex-shrink">
				详细地址：</view>
			<input class="px-1 font-md flex-1" type="text" 
			v-model="form.detailAddr" />
		</view>
		
		<divider></divider>
		
		<view class="p-2 d-flex a-center bg-white">
			<view class="font-md text-secondary mr-1 flex-shrink">
				设为默认地址：</view>
			<switch :checked="form.isDefault" class="ml-auto" color="#FD6801"
			@change="form.isDefault = $event.detail.value"/>
		</view>
		
		<view class="p-3">
			<view class="text-center w-100 main-bg-color text-white font-md rounded py-2" hover-class="main-bg-hover-color" @click="submit">
				保 存
			</view>
		</view>
		
	</view>
</template>

<script>
	import mpvueCityPicker from "@/components/uni-ui/mpvue-citypicker/mpvueCityPicker.vue"
	import {mapActions} from "vuex"
	export default {
		components: {
			mpvueCityPicker
		},
		data() {
			return {
				themeColor: '#007AFF',
				pickerValue: [0, 0, 1],
				index:-1,
				form:{
					addr:'',
					name:"",
					phone:"",
					detailAddr:"",
					isDefault:false
				}
			}
		},
		// 监听返回
		onBackPress() {
		  if (this.$refs.mpvueCityPicker.showPicker) {
		  	this.$refs.mpvueCityPicker.pickerCancel();
		    return true;
		  }
		},
		onLoad(e) {
			if (e.data) {
				let result = JSON.parse(e.data)
				this.form = result.item
				this.index = result.index
				this.isedit = true
				uni.setNavigationBarTitle({
					title:"修改收货地址"
				})
			}
		},
		// 页面卸载
		onUnload() {
			if (this.$refs.mpvueCityPicker.showPicker) {
				this.$refs.mpvueCityPicker.pickerCancel()
			}
		},
		methods: {
			...mapActions(['updateAddrAction','createAddrAction']),
			// 提交
			submit(){
				// 验证表单
				
				// 修改
				if (this.isedit) {
					this.updateAddrAction({
						index:this.index,
						item:this.form
					})
					uni.showToast({ title: '修改成功' });
					return uni.navigateBack({ delta: 1 })
				}
				
				// 创建
				this.createAddrAction(this.form)
				uni.showToast({
					title: '创建成功'
				});
				uni.navigateBack({
					delta: 1
				});
			},
			// 三级联动确定
			onConfirm(e) {
				this.form.addr = e.label
				this.pickerValue = e.value
			},
			// 显示三级联动选择
			showMulLinkageThreePicker() {
				this.$refs.mpvueCityPicker.show()
			},
		}
	}
</script>

<style>
page{
	background: #EEEEEE;
}
</style>
