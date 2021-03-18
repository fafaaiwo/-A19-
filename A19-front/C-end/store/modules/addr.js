export default {
	state:{
		list:[
			{
				name:"张三",
				phone:"158****531",
				addr:"广东省 广州市 白云区",
				detailAddr:"XXXXX街道",
				isDefault:true
			},
			{
				name:"李四",
				phone:"158****531",
				addr:"湖北省 武汉市 洪山区",
				detailAddr:"XXXXX街道",
				isDefault:false
			}
		],
	},
	getters:{
		// 获取默认地址
		defaultAddr:(state)=>{
			return state.list.filter(v=> v.isDefault)
		}
	},
	mutations:{
		// 创建收货地址
		createAddr(state,item){
			state.list.unshift(item)
		},
		// 删除收货地址
		delAddr(state,index){
			state.list.splice(index,1)
		},
		// 修改收货地址
		updateAddr(state,{index,item}){
			for (let key in item) {
				state.list[index][key] = item[key]
			}
		},
		// 取消默认地址
		removeDefault(state){
			state.list.forEach((v)=>{
				if (v.isDefault) {
					v.isDefault = false
				}
			})
		}
	},
	actions:{
		// 修改地址
		updateAddrAction({commit},obj){
			if (obj.item.isDefault) {
				commit('removeDefault');
			}
			commit('updateAddr',obj)
		},
		// 增加地址
		createAddrAction({commit},item){
			if (item.isDefault) {
				commit('removeDefault');
			}
			commit('createAddr',item)
		}
	}
}