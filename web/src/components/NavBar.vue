<template>
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: purple;">
    <!-- <nav class="navbar navbar-expand-lg navbar-light " style="background-color: #b8f1ed;"> -->
        <div class="container">
            <RouterLink class="navbar-brand" :to="{name: 'home'}">codeshare</RouterLink>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <RouterLink :class="route_name=='database'?'nav-link active':'nav-link'"  aria-current="page" :to="{name: 'database'}">题库</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink :class="route_name=='debug'?'nav-link active':'nav-link'" :to="{name: 'debug'}">debug</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink :class="route_name=='lesson'?'nav-link active':'nav-link'" :to="{name: 'lesson'}">网课推荐</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink :class="route_name=='ranklist'?'nav-link active':'nav-link'" :to="{name: 'ranklist'}">排行榜</RouterLink>
                    </li>
                </ul>

                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            {{ $store.state.user.username }}
                        </a>
                        <ul class="dropdown-menu">
                            <li><RouterLink class="dropdown-item" :to="{name:'user_code'}">个人中心</RouterLink></li>
                            <li><a class="dropdown-item" href="#" @click="logout">
                                退出
                            </a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
                    <li class="nav-item">
                        <RouterLink class="nav-link" :to="{name: 'user_account'}" role="button">
                            登录
                        </RouterLink>
                    </li>
                    <!-- <li class="nav-item">
                        <RouterLink class="nav-link" :to="{name: 'user_account'}" role="button">
                            注册
                        </RouterLink>
                    </li> -->
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
import { useRoute } from 'vue-router'
import { computed } from 'vue';
import { useStore } from 'vuex';

export default {
    setup() {
        const store = useStore();
        const route = useRoute();
        let route_name = computed(() => route.name);

        const logout = () => {
            store.dispatch("logout");
        }


        return {
            route_name,
            logout,
        }
    }
}
</script>

<style scoped>

</style>