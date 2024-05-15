<template>
    <ContentField>
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <div class="card" style="margin-top:20px;">
                        <div class="card-body">
                            <img :src="$store.state.user.photo" alt="" style="width:100%;">
                        </div>
                    </div>
                </div>
                <div class="col-9">
                    <div class="card" style="margin-top:20px;">
                        <div class="card-header">
                            <span style="font-size=1.3em;">我的代码</span>
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
                                        <div class="mb-3">
                                            <label for="add-code-topicId" class="form-label">代码对应题目</label>
                                            <select class="form-select" aria-label="Default select example" v-model="codeadd.topic_id">
                                                <option v-for="topic in topics" :key="topic.id" :value="topic.topicName">{{ topic.topicName }}</option>
                                            </select>
                                        </div>
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
                                        <th>题目</th>
                                        <th>标题</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="code in codes" :key="code.id">
                                        <td>{{ code.topicId }}</td>
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
                                                        <div class="mb-3">
                                                            <label for="update-code-topicId" class="form-label">代码对应题目</label>
                                                            <select class="form-select" aria-label="Default select example" v-model="code.topic_id">
                                                                <option v-for="topic in topics" :key="topic.id" :value="topic.topicName">{{ topic.topicName }}</option>
                                                            </select>
                                                        </div>
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


                    
                    <div class="card" style="margin-top:20px;">
                        <div class="card-header">
                            <span style="font-size=1.3em;">我的推荐</span>
                            <button type="button" class="btn btn-outline-primary float-end" data-bs-toggle="modal" data-bs-target="#add-lesson">
                                编写新推荐
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="add-lesson" tabindex="-1" aria-hidden="true">
                                <div class="modal-dialog modal-fullscreen">
                                    <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5">编写新推荐</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="mb-3">
                                            <label for="add-lesson-lessonName" class="form-label">推荐对应课程</label>
                                            <select class="form-select" aria-label="add-lesson-lessonName Default select example" v-model="lessonadd.lesson_name">
                                                <option value="C语言程序设计">C语言程序设计</option>
                                                <option value="C++语法基础">C++语法基础</option>
                                                <option value="数据结构">数据结构</option>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-lesson-title" class="form-label">推荐标题</label>
                                            <input v-model="lessonadd.title" type="text" class="form-control" id="add-lesson-title" placeholder="请输入推荐的标题">
                                        </div>
                                        <div class="mb-3">
                                            <label for="add-lesson-description" class="form-label">推荐描述</label>
                                            <textarea v-model="lessonadd.description" class="form-control" id="add-lesson-description" rows="3" placeholder="请输入推荐描述（建议加上推荐链接）"></textarea>
                                        </div>

                                    </div>
                                    <div class="modal-footer">
                                        <div class="error-message">{{ lessonadd.error_message }}</div>
                                        <button type="button" class="btn btn-primary" @click="add_lesson">创建</button>
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
                                        <th>课程</th>
                                        <th>推荐网课标题</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="lesson in lessons" :key="lesson.id">
                                        <td>{{ lesson.lessonName }}</td>
                                        <td>{{ lesson.title }}</td>
                                        <td>
                                            <button type="button" class="btn btn-outline-secondary" style="margin-right:10px;" data-bs-toggle="modal" :data-bs-target="'#update-lesson-modal-' + lesson.id">修改</button>
                                            <button type="button" class="btn btn-outline-danger" @click="remove_lesson(lesson)">删除</button>

                                            <!-- Modal -->
                                            <div class="modal fade" :id="'update-lesson-modal-' + lesson.id" tabindex="-1" aria-hidden="true">
                                                <div class="modal-dialog modal-fullscreen">
                                                    <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5">修改代码</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <div class="mb-3">
                                                            <label for="update-lesson-lessonName" class="form-label">推荐对应课程</label>
                                                            <select class="form-select" aria-label="update-lesson-lessonName Default select example" v-model="lesson.lesson_name">
                                                                <option value="C语言程序设计">C语言程序设计</option>
                                                                <option value="数据结构">数据结构</option>
                                                                <option value="课程3">课程3</option>
                                                            </select>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="update-lesson-title" class="form-label">标题</label>
                                                            <input v-model="lesson.title" type="text" class="form-control" id="update-lesson-title" placeholder="请输入推荐标题">
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="update-lesson-description" class="form-label">推荐描述</label>
                                                            <textarea v-model="lesson.description" class="form-control" id="update-lesson-description" rows="3" placeholder="请输入推荐描述（建议加上推荐的链接）"></textarea>
                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <div class="error-message">{{ lesson.error_message }}</div>
                                                        <button type="button" class="btn btn-primary" @click="update_lesson(lesson)">保存修改</button>
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="refresh_lessons()">取消</button>
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
    </ContentField>
</template>

<script>
import ContentField from '../../../components/ContentField.vue'
import $ from 'jquery'
import { useStore } from 'vuex'
import { ref, reactive } from 'vue'
import { Modal } from 'bootstrap/dist/js/bootstrap'
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';

export default {
    components: {
        ContentField,
        VAceEditor,
    },
    setup() {
        ace.config.set(
        "basePath", 
        "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

        const store = useStore();
        let codes = ref([]);
        let lessons = ref([]);
        let topics = ref([]);

        const codeadd = reactive({
            title: "",
            description: "",
            content: "",
            error_message: "",
            is_passed: "",
            topic_id: "",
        });

        const lessonadd = reactive({
            title: "",
            description: "",
            error_message: "",
            lesson_name: "",
        });

        // const refresh_topics = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/topic/getlistall/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    // console.log(resp);
                    topics.value = resp;
                },
            });
        // }

        // refresh_topics();

        const refresh_codes = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/user/code/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    codes.value = resp;
                },
            });
        }

        refresh_codes();

        const refresh_lessons = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/lesson/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    lessons.value = resp;
                },
            });
        }

        refresh_lessons();

        const add_code = () => {
            codeadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3001/api/user/code/add/",
                type: "post",
                data: {
                    title: codeadd.title,
                    description: codeadd.descriptioon,
                    content: codeadd.content,
                    is_passed: codeadd.is_passed,
                    topic_id: codeadd.topic_id,
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
                        codeadd.topic_id = "";
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

        const add_lesson = () => {
            lessonadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3001/api/lesson/add/",
                type: "post",
                data: {
                    title: lessonadd.title,
                    description: lessonadd.description,
                    lesson_name: lessonadd.lesson_name,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        lessonadd.title = "";
                        lessonadd.description = "";
                        lessonadd.error_message = "";
                        lessonadd.lesson_name = "";
                        $.ajax({
                            url: "http://127.0.0.1:3001/api/user/account/update/",
                            type: "post",
                            data: {
                                rating_change: "10",
                            },
                            headers: {
                                Authorization: "Bearer " + store.state.user.token,
                            },
                        });
                        $.ajax({
                            url: "http://127.0.0.1:3001/api/user/account/update/",
                            type: "post",
                            data: {
                                rating_change: "10",
                            },
                            headers: {
                                Authorization: "Bearer " + store.state.user.token,
                            },
                        });
                        Modal.getInstance("#add-lesson").hide();
                        refresh_lessons();
                    }
                    else {
                        lessonadd.error_message = resp.error_message;
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

        const remove_lesson = (lesson) => {
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
                url: "http://127.0.0.1:3001/api/lesson/remove/",
                type: "post",
                data: {
                    lesson_id: lesson.id,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        refresh_lessons();
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
                    topic_id: code.topic_id,
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

        const update_lesson = (lesson) => {
            lessonadd.error_message = "";
            $.ajax({
                url: "http://127.0.0.1:3001/api/lesson/update/",
                type: "post",
                data: {
                    lesson_id: lesson.id,
                    title: lesson.title,
                    description: lesson.descriptioon,
                    lesson_name: lesson.lesson_name,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        Modal.getInstance('#update-lesson-modal-' + lesson.id).hide();
                        refresh_lessons();
                    }
                    else {
                        lesson.error_message = resp.error_message;
                    }
                },
            });
        }

        return {
            codes,
            codeadd,
            add_code,
            remove_code,
            update_code,
            lessons,
            lessonadd,
            add_lesson,
            remove_lesson,
            update_lesson,
            topics,
            refresh_codes,
            refresh_lessons,
        }

    },
}

</script>

<style scoped>
.error-message {
    color: red;
}
</style>