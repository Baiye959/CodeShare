<template>
  <ContentField>
    <!-- <div style="text-align:center;">
            题库
            <div style="background-color:rgba(0,0,0,0.1); border-color: rgba(0,0,0,0);" class="accordion accordion-item" id="accordionExample">
                <ul class="list-group" >
                    <li style="border-color: rgba(0,0,0,0); background-color:rgba(0,0,0,0.1);color:black;" class="list-group-item active" aria-current="true">
                    数据结构
                    </li>
                </ul>
                
                <div class="accordion-item" >
                    <h2 class="accordion-header" id="headingOne">
                        <button class="accordion-button" style="background-color:black;" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            实验一 线性表
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul class="list-group list-group-flush">
                                <li v-for="topic in topics[0]" :key="topic.id" class="list-group-item">
                                    <router-link
                                    :to="{name: 'topic', params: {topicId: topic.id}}"
                                    >
                                        {{ topic.topicName }}
                                    </router-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingTwo">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                          实验二 栈和队列
                        </button>
                    </h2>
                    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul class="list-group list-group-flush">
                                <li v-for="topic in topics[1]" :key="topic.id" class="list-group-item">
                                    <router-link
                                    :to="{name: 'topic', params: {topicId: topic.id}}"
                                    >
                                        {{ topic.topicName }}
                                    </router-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="accordion-item">
                    <h2 class="accordion-header" id="headingThree">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                          实验三 稀疏矩阵、三元表和串
                        </button>
                    </h2>
                    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <ul class="list-group list-group-flush">
                                <li v-for="topic in topics[2]" :key="topic.id" class="list-group-item">
                                    <router-link
                                    :to="{name: 'topic', params: {topicId: topic.id}}"
                                    >
                                        {{ topic.topicName }}
                                    </router-link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div> -->
        <div class="h5 pb-2 mb-4 text-light border-bottom border-light">
            题库
        </div>

        <table class="table table-striped table-hover" style="text-align: center;">
            <thead>
                <tr>
                    <th>实验</th>
                    <th>题目</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="topic in topics2" :key="topic.id">
                    <td>
                       <div style="float: left; margin-left: 40%;">
                        {{ topic.testName }}
                       </div> 
                        
                    </td>
                    <td>
                        <router-link
                        :to="{name: 'topic', params: {topicId: topic.id}}"
                        style="color:black;text-decoration:none; float: left; margin-left: 40%;" >
                            {{ topic.topicName }}
                        </router-link>
                    </td>
                </tr>
            </tbody>
        </table>
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

    let topics2 = ref([]);

    const refresh_topics2 = () => {
        $.ajax({
            url: "http://127.0.0.1:3001/api/topic/getlistall/",
            type: "get",
            headers: {
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp) {
                topics2.value = resp;
            },
        });
    }

    refresh_topics2();


    return {
        topics2,
    }
  }

}

</script>

<style scoped>
.accordion {
    margin-top: 10px;
}
</style>