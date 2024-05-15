<template>
    <!-- <ContentField>

    </ContentField> -->
    <!-- <ContentField> -->
<!-- <div class="bigBox"> -->
<!-- <div class="bigBox" v-if="show"> -->
<div class="bigBox" v-if="!store.state.user.pulling_info">
    <div class="box">
        <!-- 滑动盒子 -->
        <div class="pre-box" ref="preRef">
            <h1>{{register_message}}</h1>
            <p>{{register_message2}}</p>
            <div class="img-box">
                <img :src="flag==true?imgList[0]:imgList[1]" alt="">
            </div>
        </div>
        <!-- 注册盒子 -->
        <form @submit.prevent="signup">
            <div class="register-form">
                <!-- 标题盒子 -->
                <div class="title-box">
                    <h1>注册</h1>
                </div>
                <!-- 输入框盒子 -->
                <div class="input-box">
                    <input v-model="username2" type="text" placeholder="用户名">
                    <input v-model="password2" type="password" placeholder="密码">
                    <input v-model="confirmedPassword2" type="password" placeholder="确认密码">
                </div>
                <div class="error-message">
                {{error_message2}}
                </div>
                <!-- 按钮盒子 -->
                <div class="btn-box">
                    <button type="submit">注册</button>
                    <!-- 绑定点击事件 -->
                    <p @click="mySwitch">已有账号?去登录</p>
                </div>
            </div>
        </form> 
        <form @submit.prevent="login">
        <!-- 登录盒子 -->
        <div class="login-form">
            <!-- 标题盒子 -->
            <div class="title-box">
                <h1>登录</h1>
            </div>
            <!-- 输入框盒子 -->
            <div class="input-box">
                <input v-model="username" type="text" placeholder="用户名">
                <input v-model="password" type="password" placeholder="密码">
            </div>
            <div class="error-message">
                {{error_message}}
            </div>
            <!-- 按钮盒子 -->
            <div class="btn-box">
                <button type="submit">登录</button>
                <!-- 绑定点击事件 -->
                <p @click="mySwitch">没有账号?去注册</p>
            </div>
        </div>
        </form>
       </div>
    </div>
<!-- </ContentField> -->
</template>

<!-- <script>
import ContentField from '../../../components/ContentField.vue'


export default {
    components: {
        ContentField,
    }
}

</script> -->

<script setup>
// import { ref } from '@vue/reactivity';
import { useStore } from 'vuex';
import { ref } from 'vue';
import router from '../../../router/index'
import $ from 'jquery';


const preRef = ref('');
const imgList = ref([require('../../../assets/bg-blue3.jpg'), require('../../../assets/bg-blue2.jpg')])

let flag = ref(true);

const mySwitch=()=>{
    if(flag.value){
        preRef.value.style.background = '#3498db'
        preRef.value.style.transform = 'translateX(100%)'
    }
    else{
        preRef.value.style.background = '#8e44ad'
        preRef.value.style.transform = "translateX(0%)"
    }
    flag.value = !flag.value;
}

    const store = useStore();
    let username = ref('');
    let password = ref('');
    let error_message = ref('');
    // let show = ref('false');

    const jwt_token = localStorage.getItem("jwt_token");
    if(jwt_token) {
        store.commit("updateToken", jwt_token);
        store.dispatch("getinfo", {
            success() {
                // console.log(router.name);
                // router.push({name: router.name});
                router.push({name: "home"});
                // router.push();
                // router.push(router.name);
                store.commit("updatePullingInfo", false);
            },
            error() {
                // show.value=true;
                store.commit("updatePullingInfo", false);
            },
        })
    }
    else{
        // show.value=true;
        store.commit("updatePullingInfo", false);
    }

    const login = () => {
        error_message.value="";
        store.dispatch("login", {
            username: username.value,
            password: password.value,
            success() {
                store.dispatch("getinfo",{
                    success(resp){
                        router.push({name: 'home'});
                        // console.log(resp);
                    }
                });
                // console.log(store.state.user);
            },
            error() {
                error_message.value="用户名或密码错误";
            },
        });
    }

    let username2 = ref('');
    let password2 = ref('');
    let confirmedPassword2 = ref('');
    let error_message2 = ref('');
    let register_message = ref('WELCOME');
    let register_message2 = ref('JOIN US!');

    const signup = () => { //注册不会修改state的值，直接在前端写即可
        error_message.value="";
        $.ajax({
            url: "http://127.0.0.1:3001/api/user/account/register/",
            type: "post",
            data: {
                username: username2.value,
                password: password2.value,
                confirmedPassword: confirmedPassword2.value,
            },
            success(resp) {
                if(resp.error_message === "success") {
                    username2.value = "";
                    password2.value = "";
                    confirmedPassword2.value = "";
                    register_message.value = "注册成功";
                    register_message2.value = "WELCOME!";
                    mySwitch();
                }
                else {
                    error_message2.value = resp.error_message;
                }
            },
            // error() {
            //     console.log("error!!!");
            // },
        });
    }



</script>

<style scoped>
.error-message{
    color: red;
    margin-left: 30%;
    margin-bottom: 10px;
}
* {
    /* 去除浏览器默认内外边距 */
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* 去除input的轮廓 */
input {
    outline: none;
}

/* html, */
/* body {
    height: 100%;
} */

.bigBox {
    min-height: 100vh;
    /* 溢出隐藏 */
    overflow-x: hidden;
    display: flex;
    /* 渐变方向从左到右 */
    background-image: linear-gradient(120deg,#3498db,#8e44ad);
    /* background: linear-gradient(to right, rgb(247, 209, 215), rgb(191, 227, 241)); */
}

span {
    position: absolute;
    z-index: 0;
    bottom: 0;
    border-radius: 50%;
    /* 径向渐变 */
    background: radial-gradient(circle at 72% 28%, #fff 3px, #3498db 8%, #5b5b5b, #8e44ad 100%);
    /* 泡泡内阴影 */
    box-shadow: inset 0 0 6px #8e44ad,
        inset 3px 0 6px #8e44ad,
        inset 2px -2px 10px #8e44ad,
        inset 0 0 60px #8e44ad,
        0 0 20px #8e44ad;
    /* 动画 */
    animation: myMove 4s linear infinite;
}


@keyframes myMove {
    0% {
        transform: translateY(0%);
        opacity: 1;
    }

    50% {
        transform: translate(10%, -1000%);
    }

    75% {
        transform: translate(-20%, -1200%);
    }

    99% {
        opacity: .9;
    }

    100% {
        transform: translateY(-1800%) scale(1.5);
        opacity: 0;
    }
}

/* 最外层的大盒子 */
.box {
    width: 1050px;
    height: 600px;
    display: flex;
    /* 相对定位 */
    position: relative;
    z-index: 2;
    margin: auto;
    /* 设置圆角 */
    border-radius: 15px;
    /* 设置边框 */
    border: 1px solid rgba(255, 255, 255, .6);
    /* 设置盒子阴影 */
    box-shadow: 2px 1px 19px rgba(0, 0, 0, .1);
}

/* 滑动的盒子 */
.pre-box {
    /* 宽度为大盒子的一半 */
    width: 50%;
    /* width: var(--width); */
    height: 100%;
    /* 绝对定位 */
    position: absolute;
    /* 距离大盒子左侧为0 */
    left: 0;
    /* 距离大盒子顶部为0 */
    top: 0;
    z-index: 99;
    border-radius: 15px;
    background-color: #8e44ad;
    box-shadow: 2px 1px 19px rgba(0, 0, 0, .1);
    /* 动画过渡，先加速再减速 */
    transition: 0.5s ease-in-out;
}

/* 滑动盒子的标题 */
.pre-box h1 {
    margin-top: 150px;
    text-align: center;
    /* 文字间距 */
    letter-spacing: 5px;
    color: white;
    /* 禁止选中 */
    user-select: none;
    /* 文字阴影 */
    text-shadow: 4px 4px 3px rgba(0, 0, 0, .1);
}

/* 滑动盒子的文字 */
.pre-box p {
    height: 30px;
    line-height: 30px;
    text-align: center;
    margin: 20px 0;
    /* 禁止选中 */
    user-select: none;
    font-weight: bold;
    color: white;
    text-shadow: 4px 4px 3px rgba(0, 0, 0, .1);
}

/* 图片盒子 */
.img-box {
    width: 200px;
    height: 200px;
    margin: 20px auto;
    /* 设置为圆形 */
    border-radius: 50%;
    /* 设置用户禁止选中 */
    user-select: none;
    overflow: hidden;
    box-shadow: 4px 4px 3px rgba(0, 0, 0, .1);
}

/* 图片 */
.img-box img {
    width: 100%;
    transition: 0.5s;
}

form{
    width: 100%;
}

/* 登录和注册盒子 */
.login-form{
    /* float: right; */
    height: 100%;
    /* width: 100%; */
}
.register-form {
    flex: left;
    /* width: 100%; */
    height: 100%;
}

/* 标题盒子 */
.title-box {
    height: 30%;
    /* line-height: 500px; */
    margin-top: 20%;
}

/* 标题 */
.title-box h1 {
    text-align: center;
    color: white;
    /* 禁止选中 */
    user-select: none;
    letter-spacing: 5px;
    text-shadow: 4px 4px 3px rgba(0, 0, 0, .1);

}

/* 输入框盒子 */
.input-box {
    display: flex;
    /* 纵向布局 */
    flex-direction: column;
    /* 水平居中 */
    align-items: center;
}

/* 输入框 */
input {
    width: 60%;
    height: 40px;
    margin-bottom: 20px;
    text-indent: 10px;
    border: 1px solid #fff;
    background-color: rgba(255, 255, 255, 0.3);
    border-radius: 120px;
    /* 增加磨砂质感 */
    backdrop-filter: blur(10px);
}

input:focus {
    /* 光标颜色 */
    color: #b0cfe9;

}

/* 聚焦时隐藏文字 */
input:focus::placeholder {
    opacity: 0;
}

/* 按钮盒子 */
.btn-box {
    display: flex;
    justify-content: center;
}

/* 按钮 */
button {
    width: 100px;
    height: 30px;
    margin: 0 7px;
    line-height: 30px;
    border: none;
    border-radius: 4px;
    background-color: #69b3f0;
    color: white;
}

/* 按钮悬停时 */
button:hover {
    /* 鼠标小手 */
    cursor: pointer;
    /* 透明度 */
    opacity: .8;
}

/* 按钮文字 */
.btn-box p {
    height: 30px;
    line-height: 30px;
    /* 禁止选中 */
    user-select: none;
    font-size: 14px;
    color: white;

}

.btn-box p:hover {
    cursor: pointer;
    border-bottom: 1px solid white;
}

</style>