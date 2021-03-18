<template>
	<view>
		<view class="submit" :style="{bottom: inputBottom+'px'}">
			<view class="submit-chat">
				<view class="bt-icon" @tap="emoji">
					<text class="iconfont icon-expressions"></text>
				</view>
				<textarea auto-height="true" class="chat-send btn" @input="inputs" v-model="msg"
				@focus="focusTextarea" @blur="blurTextarea" :adjust-position="false"></textarea>
				<view class="bt-icon" v-if="!msg.length">
					<text class="iconfont icon-tianjia"></text>
				</view>
				<view class="bt-icon" v-else @tap="send">
					<text class="iconfont icon-fasong"></text>
				</view>
			</view>
			<view class="emoji-board" :class="{displaynone:!isemoji}">
				表情
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isemoji:false,
				msg:'',
				inputBottom:0,
			}
		},
		methods: {
			//获取模块高度
			getElementHeight(h) {
				const query = uni.createSelectorQuery().in(this);
				query.select('.submit').boundingClientRect(data => {
					this.$emit('heights',data.height + h);
				}).exec();
			},
			
			//点击表情
			emoji() {
				this.isemoji = !this.isemoji;
				this.$nextTick(function(){
					this.getElementHeight(0);
				})
			},
			//文字输入与发送
			inputs(e) {
				var chatm = e.detail.value;
				var pos = chatm.indexOf('\n');
				if (pos != -1 && chatm.length>1) {
					this.$emit('inputs',this.msg);
					setTimeout(()=> {
						this.msg = '';
					}, 0);
				}
			},
			send() {
				this.$emit('inputs',this.msg);
				setTimeout(()=> {
					this.msg = '';
				}, 0);
			},
			focusTextarea(e) {
			    this.inputBottom = e.detail.height;
				this.$nextTick(function(){
					this.getElementHeight(this.inputBottom);
				})
			},
			blurTextarea(e) {
			    this.inputBottom = 0;
				this.$nextTick(function(){
					this.getElementHeight(this.inputBottom);
				})
			},
		}
	}
</script>

<style lang="scss">
	.submit {
		background: rgba(244, 244, 244, 0.96);
		border-top: 1px solid $uni-border-color;
		width: 100%;
		position: fixed;
		bottom: 0;
		z-index: 100;
		padding-bottom: 15rpx;
	}
	.displaynone {
		display: none;
	}
	.submit-chat {
		width: 100%;
		display: flex;
		align-items: center;
		box-sizing: border-box;
		padding: 14rpx;
		text {
			font-size: 56rpx;
			margin: 0 10rpx;
			flex: auto;
		}
		.btn {
			flex: auto;
			background-color: #fff;
			border-radius: 10rpx;
			padding: 20rpx;
			max-height: 150rpx;
			margin: 0 10rpx;
		}
	}
	.emoji-board {
		width:100%;
		height:460rpx;
		background:rgba(236,237,238,1);
		box-shadow:0rpx -1rpx 0rpx 0rpx rgba(0, 0, 0, 0.1);
	}
</style>
