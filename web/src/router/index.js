import { createRouter, createWebHistory } from 'vue-router'
import Database from '../views/database/DatabaseIndex'
import Debug from '../views/debug/DebugIndex'
import NotFound from '../views/error/NotFound'
import Lesson from '../views/lesson/LessonIndex'
import Ranklist from '../views/ranklist/RanklistIndex'
import UserCode from '../views/user/code/UserCodeIndex'
import UserAccount from '../views/user/account/UserAccount'
import store from '../store/index'
import LessonId from '../views/lesson/LessonIdIndex'
import Topic from '../views/database/TopicIndex'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/database/",
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/database/",
    name: "database",
    component: Database,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/database/:topicId/",
    name: "topic",
    component: Topic,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/debug/",
    name: "debug",
    component: Debug,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/404/",
    name: "notfound",
    component: NotFound,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/lesson/",
    name: "lesson",
    component: Lesson,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/lesson/:lessonId/",
    name: "lessonId",
    component: LessonId,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/ranklist/",
    name: "ranklist",
    component: Ranklist,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/code/",
    name: "user_code",
    component: UserCode,
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/user/account/",
    name: "user_account",
    component: UserAccount,
    meta: {
      requestAuth: false,
    }
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/",
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// router.beforeEach((to,from,next)=>{
//   const jwt_token = localStorage.getItem('jwt_token');
//   if(jwt_token){
//     store.commit('updateToken',jwt_token);
//     store.dispatch('getInfo',{
//       success(){
//         next();
//       },
//       error(){
//         // console.log(store.state.user);
//         store.dispatch('logout');
//         alert("token无效，请重新登录！");
//         next({name:"user_account"});
//       }
//     })
//   }else{
//     if(to.meta.requestAuth && !store.state.user.is_login){
//       next({name:"user_account"});
//     }else{
//       next();
//     }
//   }
// })


router.beforeEach((to, from, next) => {
  if(to.meta.requestAuth && !store.state.user.is_login) {//组件中使用$
    next({name: 'user_account'});
  }
  else {
    next();
  }
}) 

// router.beforeEach((to, from, next) => {

//   let flag = 1;
//   const jwt_token = localStorage.getItem("jwt_token");

//   if (jwt_token) {
//     store.commit("updateToken", jwt_token);
//     store.dispatch("getInfo", {
//       error() {
//         localStorage.removeItem('jwt_token');
//         alert("token无效，请重新登录！");
//         router.push({ name: 'user_account' });
//       },
//       success() {
//         store.commit("updatePullingInfo", false);
//       },

//     })
//   } else {
//     flag = 2;
//   }

//   if (to.meta.requestAuth && !store.state.user.is_login) {
//     if (flag === 1) {
//       store.commit("updatePullingInfo", false);
//       next();
//     } else {
//       // alert("请先进行登录！");
//       next({name: "user_account"});
//     }
//   } else {
//     next();
//   }

// })

export default router
