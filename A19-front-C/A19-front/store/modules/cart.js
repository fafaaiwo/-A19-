export default {
	state: {
		list: [
			{
				shopName:"珞珈自强信部店512号",
				checked: false,
				shopid:6,
				goodsList:[
					{
						checked: false,
						numBoxShow: false,
						id: 11,
						unit: "天",
						title: "商品标题111",
						cover: "/static/images/demo/list/1.jpg",
						// 选中商品属性
						attrs: [{
								title: '成色',
								selected: 0,
								list: [{
									name: '全新'
								}, {
									name: '九成新'
								}, {
									name: '八成新'
								}]
							},
							{
								title: '颜色',
								selected: 0,
								list: [{
									name: '红色'
								}, {
									name: '黑色'
								}]
							},
							{
								title: '租赁方式',
								selected: 0,
								list: [{
									name: '按月'
								}, {
									name: '按天'
								}, {
									name: '按小时'
								}]
							}
						],
						pprice: 10,
						deposit: 1000,
						num: 1,
						minnum: 1,
						maxnum: 10, // 该商品最大商品数，跟库存有关
						period: 10,
						minperiod: 10,
						maxperiod: 100 // 商品租期
					},
					{
						checked: false,
						numBoxShow: false,
						id: 12,
						unit: "天",
						title: "商品标题111",
						cover: "/static/images/demo/list/1.jpg",
						// 选中商品属性
						attrs: [{
								title: '成色',
								selected: 0,
								list: [{
									name: '全新'
								}, {
									name: '九成新'
								}, {
									name: '八成新'
								}]
							},
							{
								title: '颜色',
								selected: 0,
								list: [{
									name: '红色'
								}, {
									name: '黑色'
								}]
							},
							{
								title: '租赁方式',
								selected: 0,
								list: [{
									name: '按月'
								}, {
									name: '按天'
								}, {
									name: '按小时'
								}]
							}
						],
						pprice: 10,
						deposit: 1000,
						num: 1,
						minnum: 1,
						maxnum: 10, // 该商品最大商品数，跟库存有关
						period: 10,
						minperiod: 10,
						maxperiod: 100 // 商品租期
					}
				]
			},
			{
				shopName:"中百罗森网安店No.599",
				checked: false,
				shopid : 7,
				goodsList:[
					{
						checked: false,
						numBoxShow: false,
						id: 13,
						unit: "天",
						title: "商品标题111",
						cover: "/static/images/demo/list/1.jpg",
						// 选中商品属性
						attrs: [{
								title: '成色',
								selected: 0,
								list: [{
									name: '全新'
								}, {
									name: '九成新'
								}, {
									name: '八成新'
								}]
							},
							{
								title: '颜色',
								selected: 0,
								list: [{
									name: '红色'
								}, {
									name: '黑色'
								}]
							},
							{
								title: '租赁方式',
								selected: 0,
								list: [{
									name: '按月'
								}, {
									name: '按天'
								}, {
									name: '按小时'
								}]
							}
						],
						pprice: 10,
						deposit: 1000,
						num: 1,
						minnum: 1,
						maxnum: 10, // 该商品最大商品数，跟库存有关
						period: 10,
						minperiod: 10,
						maxperiod: 100, // 商品租期 
						desc: "磁动力滑盖全面屏 / 前后旗舰AI双摄 / 四曲面彩色陶瓷机身 / 高效10W无线充电",
						oprice: 10,
						sales: 100,
						shopid: 9,
						shopname: "珞珈自强超市信部店",
					}
				]
			},
		],
		// 选中列表（存放选中的id）
		selectedList: [],
		// popup显示
		popupShow: "none",
		popupIndex: -1,
		popupShop: -1
	},
	getters: {
		// 判断是否全选
		checkedAll: (state) => {
			var totalGoods = 0
			state.list.forEach(v => {
				totalGoods += v.goodsList.length
			})
			// console.log(totalGoods)
			return totalGoods === state.selectedList.length 
		},
		// 判断店铺是否全选
		checkedShop: function(state){
			return function (shopid) {
				var flag = true
			    state.list[shopid].goodsList.forEach(v => {
			    	if (state.selectedList.indexOf(v.id) < 0) {
			    		flag = false
			    	}
			    })
			    return flag
			}
		},
		
		// 合计
		totalPrice: (state) => {
			var total = 0
			for (var j = 0; j < state.list.length; j++) {
				state.list[j].goodsList.forEach(v => {
					if (state.selectedList.indexOf(v.id) > -1) {
						total += v.deposit * v.num
					}
				})
			}
			return total
		},
		// 禁用全选
		disableSelectAll: (state) => {
			return state.list.length === 0
		},
		// 拿到当前需要修改属性的商品
		popupData: (state) => {
			return state.popupIndex > -1 ? state.list[state.popupShop].goodsList[state.popupIndex] : {}
		}
	},
	mutations: {
		// 选中/取消选中某个商品
		selectItem(state, args) {
			let shopid = args.shop
			let goodsid = args.goods
			let id = state.list[shopid].goodsList[goodsid].id
			let i = state.selectedList.indexOf(id)
			// 之前是选中，取消选中 
			if (i > -1) {
				// 取消当前商品选中状态
				state.list[shopid].goodsList[goodsid].checked = false
				// 移除选中列表中的当前商品
				// console.log("取消勾选")
				return state.selectedList.splice(i, 1)
			}
			// 选中
			state.list[shopid].goodsList[goodsid].checked = true
			state.selectedList.push(id)
		},
		selectShop(state, shopid){
			// console.log(1,shopid)
			for (var j = 0; j < state.list[shopid].goodsList.length; j++) {
				var id = state.list[shopid].goodsList[j].id
				let i = state.selectedList.indexOf(id)
				if (i < 0) {
					state.list[shopid].goodsList[j].checked = true
					state.selectedList.push(id)
				}
			}
		},
		 
		unSelectShop(state, shopid) {
			for (var j = 0; j < state.list[shopid].goodsList.length; j++) {
				var id = state.list[shopid].goodsList[j].id
				state.list[shopid].goodsList[j].checked = false
				var i = state.selectedList.indexOf(id)
				state.selectedList.splice(i, 1)
			}
		},
		
		// 全选
		selectAll(state) {
			state.selectedList = []
			state.list.map(s => {
				 s.goodsList.map(v => {
					// 设置选中状态
					v.checked = true
					state.selectedList.push(v.id)
				})
			})
			// console.log(state.selectedList)
		},
		// 取消全选
		unSelectAll(state) {
			state.list.map(s => {
				 s.goodsList.map(v => {
					// 设置选中状态
					v.checked = false
				})
			})
			state.selectedList = []
		},
		// 删除选中
		delGoods(state) {
			state.list.map(s => {
				s.goodsList = s.goodsList.filter(v => {
					return state.selectedList.indexOf(v.id) === -1
				})
				
				if (!s.goodsList.length) {
					state.list.splice(state.list.indexOf(s), 1) 
				}
			})
			
		},
		
		// 初始化popupIndex
		initPopupIndex(state, args) {
			state.popupShop = args.shop
			state.popupIndex = args.goods
		},
		// 加入购物车
		addGoodsToCart(state,goods){
			var flag = false
			state.list.forEach(s => {
				if (goods.shopid === s.shopid) {
					s.goodsList.forEach(v => {
						if (v.id === goods.id) {
							v.num = Number(v.num) + Number(goods.num)
							v.period = Number(v.period) + Number(goods.period) 
							flag = true
							return
						}
					})
					if (!flag) {
						s.goodsList.unshift(goods)
						flag = true
					}
					return 
				}
			})
			if (!flag) {
				var tmpShop = {}
				tmpShop["checked"] = false
				tmpShop["shopName"] = goods.shopname
				tmpShop["shopid"] = goods.shopid
				tmpShop["goodsList"] = [goods]
				state.list.unshift(tmpShop)
				// console.log(tmpShop)
			}
			
			// console.log(goods)
			
		}
	},
	actions: {
		// 显示popup
		doShowPopup({
			state,
			commit
		}, args) {
			commit('initPopupIndex', args)
			state.popupShow = 'show'
		},
		// 隐藏popup
		doHidePopup({
			state,
			commit
		}) {
			state.popupShow = 'hide'
			setTimeout(() => {
				state.popupShow = 'none'
				commit('initPopupIndex', {shop:-1,goods:-1})
			}, 200)
		},
		doSelectAll({
			commit,
			getters
		}) {
			getters.checkedAll ? commit('unSelectAll') : commit('selectAll')
		},
		doSelectShop({
			commit,
			getters
		}, shopIndex) {
			getters.checkedShop(shopIndex) ? commit('unSelectShop',shopIndex) : commit('selectShop',shopIndex)
		}, 
		doDelGoods({
			commit
		}) {
			uni.showModal({
				content: '是否删除选中',
				success: (res) => {
					if (res.confirm) {
						commit('delGoods')
						uni.showToast({
							title: '删除成功'
						});
					}
				}
			});
		}
	}
}
