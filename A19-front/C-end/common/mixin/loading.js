export default {
	data(){
		return {
			beforeReady:true,
		}
	},
	onReady() {
		this.$nextTick(()=>{
			this.beforeReady = false
		})
	},
}