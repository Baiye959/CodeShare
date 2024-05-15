<template>
    <ContentField>
        <!-- <div style="text-align:center;text-shadow: 2px 2px 1px rgba(#333745, 0.6);">
            网课推荐
            <div class="accordion accordion-item" id="accordionExample">
                <ul class="list-group">
                    <li class="list-group-item active" aria-current="true">
                    算法路线
                    </li>
                </ul>
                
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingOne">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            C语言程序设计
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul class="list-group list-group-flush">
                                <li v-for="lesson in lessons[0]" :key="lesson.id" class="list-group-item">
                                    <router-link
                                    :to="{name: 'lessonId', params: {lessonId: lesson.id}}"
                                    >
                                        {{ lesson.title }}
                                    </router-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            数据结构
                        </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul class="list-group list-group-flush">
                                <li v-for="lesson in lessons[1]" :key="lesson.id" class="list-group-item">
                                    <router-link
                                    :to="{name: 'lessonId', params: {lessonId: lesson.id}}"
                                    >
                                        {{ lesson.title }}
                                    </router-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            课程三
                        </button>
                    </h2>
                    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul class="list-group list-group-flush">
                                <li v-for="lesson in lessons[2]" :key="lesson.id" class="list-group-item">
                                    <router-link
                                    :to="{name: 'lessonId', params: {lessonId: lesson.id}}"
                                    >
                                        {{ lesson.title }}
                                    </router-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <div class="h5 pb-2 mb-4 text-light border-bottom border-light">
            网课推荐
        </div>

        <div class="card" style="background-color:rgba(0,0,0,0);">
            <div class="card-header" >
                算法路线
            </div>
            <div class="card-body">
                <table class="table table-striped table-hover" style="text-align: center;">
            <thead>
                <tr>
                    <th>课程</th>
                    <th>推荐</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="lesson in lessons2" :key="lesson.id">
                    <td>
                        <div style="float: left; margin-left: 40%;">
                            {{ lesson.lessonName }}
                        </div>
                        
                    </td>
                    <td>
                        <router-link
                        :to="{name: 'lessonId', params: {lessonId: lesson.id}}"
                        style="color:black;text-decoration:none;float: left; margin-left: 40%;" >
                            {{ lesson.title }}
                        </router-link>
                    </td>
                </tr>
            </tbody>
        </table>
            </div>
        </div>
        <!-- <table class="table table-striped table-hover" style="text-align: center;">
            <thead>
                <tr>
                    <th>课程</th>
                    <th>推荐</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="lesson in lessons2" :key="lesson.id">
                    <td>
                        {{ lesson.lessonName }}
                    </td>
                    <td>
                        <router-link
                        :to="{name: 'lessonId', params: {lessonId: lesson.id}}"
                        style="color:black;text-decoration:none;" >
                            {{ lesson.title }}
                        </router-link>
                    </td>
                </tr>
            </tbody>
        </table> -->
    </ContentField>
</template>

<script>
import ContentField from '../../components/ContentField.vue'
import $ from 'jquery'
import { useStore } from 'vuex'
import { ref } from 'vue'

export default {
    components: {
        ContentField,
    },
    setup() {
        const store = useStore();
        let lessons2 = ref([]);

        const refresh_lessons2 = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/lesson/getlistall/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    // console.log(resp);
                    lessons2.value = resp;
                    // console.log(topics.value[0]);
                },
            });
        }

        refresh_lessons2();

        return {
            // lessons,
            lessons2,
        }
    }
}

</script>

<style scoped>
.accordion {
    margin-top: 10px;
}
</style>