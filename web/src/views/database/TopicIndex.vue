<template>
    <ContentField>
        <div class="card">
            <div class="card-header">
                编辑本题
            </div>
            <div class="card-body" v-if="topic[0]">
                <h5 class="card-title">
                    {{ topic[0].topicName }}
                    <button type="button" class="btn btn-outline-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        查看本题对应动画
                    </button>
                </h5>
                <!-- Button trigger modal -->


                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-fullscreen">
                        <div class="modal-content" v-if="topic[0].id==7 || topic[0].id==8 || topic[0].id==11">
                            <div class="modal-header">
                                
                                <h1 class="modal-title fs-5" id="exampleModalLabel">栈</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <Stack/>
                            </div>
                        </div>
                        <div class="modal-content" v-else-if="topic[0].id==9 || topic[0].id==10">
                            <div class="modal-header">
                                
                                <h1 class="modal-title fs-5" id="exampleModalLabel">队列</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <Queue/>
                            </div>
                        </div>
                        <div class="modal-content" v-else>
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">本题暂无对应动画！正在加紧开发</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                ...
                            </div>
                        </div>
                    </div>
                </div>










                <p class="card-text" v-if="topic[0]"><pre>{{ topic[0].topicContent }}</pre></p>
                <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
                <div class="col-9">
                    <div class="card" style="margin-top:20px;">
                        <div class="card-header">
                            <span style="font-size=1.3em;">我的代码（注意：代码通过后才会在本页下方显示他人代码）</span>
                            <button type="button" class="btn btn-outline-primary float-end" data-bs-toggle="modal" data-bs-target="#add-code">
                                编写新代码
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="add-code" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-fullscreen">
                                    <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">编写新代码</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- <div class="mb-3">
                                            <label for="add-code-topicId" class="form-label">代码对应题目</label>
                                            <select class="form-select" aria-label="Default select example" v-model="codeadd.topic_id">
                                                <option v-for="topic in topics" :key="topic.id" :value="topic.topicName">{{ topic.topicName }}</option>
                                            </select>
                                        </div> -->
                                        <div class="mb-3">
                                            <label for="add-code-title" class="form-label">标题</label>
                                            <input v-model="codeadd.title" type="text" class="form-control" id="add-code-title" placeholder="请输入代码标题">
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-code-description" class="form-label">代码描述</label>
                                            <textarea v-model="codeadd.description" class="form-control" id="add-code-description" rows="3" placeholder="请输入代码描述（建议加上使用的代码语言）"></textarea>
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-code-content" class="form-label">代码内容</label>
                                            <!-- <textarea v-model="codeadd.content" class="form-control" id="add-code-content" rows="7" placeholder="请输入代码内容"></textarea> -->
                                            <VAceEditor
                                                v-model:value="codeadd.content"
                                                @init="editorInit"
                                                lang="c_cpp"
                                                theme="textmate"
                                                style="height: 300px" />
                                        </div>

                                        <div class="mb-3">
                                            <div class="form-check form-switch">
                                                <input v-model="codeadd.is_passed" class="form-check-input" type="checkbox" role="switch" id="add-code-isPassed" checked>
                                                <label class="form-check-label" for="add-code-isPassed">代码已经通过（通过发布到代码区，未通过发布到debug区）</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <div class="error-message">{{ codeadd.error_message }}</div>
                                        <button type="button" class="btn btn-primary" @click="add_code">创建</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                    </div>
                                </div>
                            </div>
                            </div>
                        </div>
                        <div class="card-body">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <!-- <th>题目</th> -->
                                        <th>标题</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="code in codes" :key="code.id">
                                        <!-- <td>{{ code.topicId }}</td> -->
                                        <td>{{ code.title }}</td>
                                        <td>{{ code.createtime }}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-secondary" style="margin-right:10px;" data-bs-toggle="modal" :data-bs-target="'#update-code-modal-' + code.id">修改</button>
                                            <button type="button" class="btn btn-outline-danger" @click="remove_code(code)">删除</button>

                                            <!-- Modal -->
                                            <div class="modal fade" :id="'update-code-modal-' + code.id" tabindex="-1" aria-labelledby="exampleModalLabel2" aria-hidden="true">
                                                <div class="modal-dialog modal-fullscreen">
                                                    <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5" id="exampleModalLabel2">修改代码</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <!-- <div class="mb-3">
                                                            <label for="update-code-topicId" class="form-label">代码对应题目</label>
                                                            <select class="form-select" aria-label="Default select example" v-model="code.topic_id">
                                                                <option v-for="topic in topics" :key="topic.id" :value="topic.topicName">{{ topic.topicName }}</option>
                                                            </select>
                                                        </div> -->
                                                        <div class="mb-3">
                                                            <label for="update-code-title" class="form-label">标题</label>
                                                            <input v-model="code.title" type="text" class="form-control" id="update-code-title" placeholder="请输入代码标题">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="update-code-description" class="form-label">代码描述</label>
                                                            <textarea v-model="code.description" class="form-control" id="update-code-description" rows="3" placeholder="请输入代码描述（建议加上使用的代码语言）"></textarea>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="update-code-content" class="form-label">代码内容</label>
                                                            <!-- <textarea v-model="code.content" class="form-control" id="update-code-content" rows="7" placeholder="请输入代码内容"></textarea> -->
                                                            <VAceEditor
                                                                v-model:value="code.content"
                                                                @init="editorInit"
                                                                lang="c_cpp"
                                                                theme="textmate"
                                                                style="height: 300px" />
                                                        </div>

                                                        <div class="mb-3">
                                                            <div class="form-check form-switch">
                                                                <input v-model="code.is_passed" class="form-check-input" type="checkbox" role="switch" id="update-code-isPassed" checked>
                                                                <label class="form-check-label" for="update-code-isPassed">代码已经通过（通过发布到代码区，未通过发布到debug区）</label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="error-message">{{ code.error_message }}</div>
                                                        <button type="button" class="btn btn-primary" @click="update_code(code)">保存修改</button>
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="refresh_codes()">取消</button>
                                                    </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
            </div>
        </div>
        </div>

        <br><br>
        <div class="card">
            <div class="card-header">
                通过本题后可查看他人的AC代码
            </div>
            <div class="card-body">
                <div class="accordion accordion-flush" v-for="code in allcodes" :key=code.id>
                <!-- <div class="accordion" style="margin:0;" v-for="code in allcodes" :key=code.id> -->
                    <!-- <div class="accordion-item" v-if="code.topicId == this_topic && code.isPassed=='true'"> -->
                    <div class="accordion-item" v-if="code.isPassed=='true' && topic[0].topicName==code.topicId">
                        <h2 class="accordion-header" :id="'panelsStayOpen-heading'+code.id">
                        <button class="accordion-button" type="button" data-bs-toggle="collapse" :data-bs-target="'#panelsStayOpen-collapse'+code.id" aria-expanded="false" :aria-controls="'panelsStayOpen-collapse'+code.id">
                            <p>{{ code.userId }}{{  "   " + code.title }}</p>
                        </button>
                        </h2>
                        <div :id="'panelsStayOpen-collapse'+code.id" class="accordion-collapse collapse" :aria-labelledby="'panelsStayOpen-heading'+code.id">
                        <div class="accordion-body">
                            <p class="card-title">{{ code.description }}</p>
                            <p class="card-text"> <pre>{{ code.content }}</pre> </p>
                        </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </ContentField>
</template>

<script>
import ContentField from '../../components/ContentField.vue'
import { useRoute } from 'vue-router';
import $ from 'jquery'
import { useStore } from 'vuex'
import { ref, reactive } from 'vue'
import { Modal } from 'bootstrap/dist/js/bootstrap'
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';
import Queue from '../../components/Queue.vue'
import Stack from '../../components/Stack.vue'

const getInitialItems = () => []
let id = getInitialItems().length + 1

export default {

    components: {
        ContentField,
        VAceEditor,
        Queue,
        Stack,
    },
    setup() {

        ace.config.set(
        "basePath", 
        "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

        const store = useStore();
        const route = useRoute();
        // const topicId = parseInt(route.params.topicId);
        const topicId = route.params.topicId;
        // const topicName = route.params.topicName;
        // console.log(topicId);
        // console.log(topicName);

        let codes = ref([]);
        let allcodes = ref([]);
        let this_topic = ref("");
        let pass = ref("false");
        const codeadd = reactive({
            title: "",
            descriptiosn: "",
            content: "",
            error_message: "",
            is_passed: "",
        });
        let topic = ref([]);

        // const refresh_this_topic = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/database/topic/getbyid/",
                type: "get",
                data: {
                    topic_id: topicId,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    topic.value = resp;
                    if(topic.value[0]) {
                        this_topic.value = topic.value[0].topicName;
                        refresh_codes();
                    }
                },
            });
        // }

        // refresh_this_topic();

        const refresh_allcodes = () => {
            if(this_topic.value) {
                $.ajax({
                    url: "http://127.0.0.1:3001/api/debug/code/getlistall/",
                    type: "get",
                    headers: {
                        Authorization: "Bearer " + store.state.user.token,
                    },
                    success(resp) {
                        allcodes.value = resp;
                        // console.log(resp);
                        // console.log("通过题目，获得所有代码");
                        // console.log(allcodes.value);
                    },
                });
            }
            // else console.log("未获取到题目名字");

        }

        const refresh_codes = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/database/topic/mycode/",
                type: "get",
                data: {
                    topic_name: this_topic.value,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    codes.value = resp;
                    // console.log(codes.value);
                    pass.value = "false";
                    for(var i in codes.value){
                        if(codes.value[i].isPassed == "true"){
                            pass.value = "true";
                            // console.log(pass.value);
                            // console.log("通过本题");
                            refresh_allcodes();
                            break;
                        }
                        // else{
                        //     console.log(codes.value[i].id);
                        //     console.log(codes.value[i]);
                        //     console.log("该代码未通过！");
                        // }
                    }
                },
            });
        }

        refresh_codes();

        const add_code = () => {
            codeadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3001/api/user/code/add/",
                type: "post",
                data: {
                    title: codeadd.title,
                    description: codeadd.description,
                    content: codeadd.content,
                    is_passed: codeadd.is_passed == "" ? "false" : codeadd.is_passed,
                    topic_id: this_topic.value,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        codeadd.title = "";
                        codeadd.description = "";
                        codeadd.content = "";
                        codeadd.is_passed = "true";
                        codeadd.error_message = "";
                        $.ajax({
                            url: "http://127.0.0.1:3001/api/user/account/update/",
                            type: "post",
                            data: {
                                rating_change: "10",
                            },
                            headers: {
                                Authorization: "Bearer " + store.state.user.token,
                            },
                            // success(resp) {
                            //     console.log(resp);
                            // }
                        });
                        Modal.getInstance("#add-code").hide();
                        refresh_codes();
                    }
                    else {
                        codeadd.error_message = resp.error_message;
                    }
                },
            });
        }

        const remove_code = (code) => {

            $.ajax({
                url: "http://127.0.0.1:3001/api/user/account/update/",
                type: "post",
                data: {
                    rating_change: "-10",
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
            });

            $.ajax({
                url: "http://127.0.0.1:3001/api/user/code/remove/",
                type: "post",
                data: {
                    code_id: code.id,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        refresh_codes();
                    }
                },
            });
        }

        const update_code = (code) => {
            codeadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3001/api/user/code/update/",
                type: "post",
                data: {
                    code_id: code.id,
                    title: code.title,
                    description: code.descriptioon,
                    content: code.content,
                    is_passed: code.is_passed,
                    topic_id: this_topic.value,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        Modal.getInstance('#update-code-modal-' + code.id).hide();
                        refresh_codes();
                    }
                    else {
                        code.error_message = resp.error_message;
                    }
                },
            });
        }

        return {
            topic,
            codes,
            codeadd,
            add_code,
            remove_code,
            update_code,
            refresh_codes,
            pass,
            allcodes,
        }
    }
}

</script>

<style scoped>
.error_message {
    color: red;
}
</style>