<script>
const getInitialItems = () => []
let id = getInitialItems().length + 1

export default {
  data() {
    return {
      items: getInitialItems(),
	  output:[],
    }
  },
  methods: {
    push() {
		if(id<=10)    //入队元素最多到10
			this.items.push(id++)
    },
    reset() {
      this.items = getInitialItems()
	  id=getInitialItems().length+1
	  this.output=[]
    },
    pop() {
		this.output.push(this.items[0])
		this.items.shift()
    }
  }
}
</script>

<template>
	<div class="contain">
		<div class="output">
			输出：
			<span v-for="item in output" class="item badge" :key="item">{{ item }}</span>
		</div>
		<div class="queue">
			<div class="row justify-content-around">
				<button type="button" class="btn btn-light col-3" @click="push">入队</button>
				<button type="button" class="btn btn-light col-3" @click="pop">出队</button>
				<button type="button" class="btn btn-warning col-3" @click="reset">重置</button>
			</div>
			<div class="transition">
				<TransitionGroup name="fade" >
					<div v-for="item in items" class="item" :key="item">
						<span class="badge">{{item}}</span>
					</div>
				</TransitionGroup>
			</div>
		</div>
	</div>
</template>

<style>
.contain {
	width: 360px;
	margin: auto;
	padding: 30px;
}

.queue {
	position: relative;
	width: 360px;
	height: 400px;
}

.transition {
	position: absolute;
	left: 0;
	right: 0;
	width: 40px;
	margin: 30px auto;
	/* border-bottom: 3px solid #666; */
}

.item {
  width: 100%;
  height: 30px;
  background-color: #fff;
  box-sizing: border-box;
}

.badge {
	background-color: #666;
	height: 20px;
	width: 40px;
	margin: 3px;
}

.output {
	height: 60px;
	width: 360px;
}

/*过渡效果 */
.fade-move,
.fade-enter-active,
.fade-leave-active {
  transition: all 0.5s cubic-bezier(0.55, 0, 0.1, 1);
}

/*进入和离开的状态 */
.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: scaleY(0.01) translate(30px, 0);
}

/*确保离开的项目被移除出了布局流以便正确地计算移动时的动画效果。 */
.fade-leave-active {
  position: absolute;
}
</style>