<template>
    <ContentField>
        <div class="h5 pb-2 mb-4 text-light border-bottom border-light">
            debug区（代码显示作者id，评论显示作者用户名）
        </div>

        <div  v-for="code in codes" :key="code.id">
            <div class="row" v-if="code.isPassed=='false'" style="margin-top:10px; margin-bottom: 30px; background-color: rgba(250,250,250,0.5);padding: 10px 5px; border-radius: 10px;">
                <div class="col-6" style=" overflow: auto; max-height:100vh;">
                <!-- <div class="col-6"> -->
                    <div class="card" style="background-color:rgba(0,0,0,0);">
                        <div class="card-header">
                            <p style="float:left;">{{ code.userId }}</p>
                            <p style="float:right;">{{ code.createtime }}</p> 
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">{{ code.topic_id }}</h5>
                            <h5 class="card-title" style="margin-top:10px;">{{ code.title }}</h5>
                            <p class="card-title">{{ code.description }}</p>
                            <p class="card-text"> <pre>{{ code.content }}</pre> </p>
                        </div>
                        
                    </div>



                </div>
                <div class="col-6" style=" overflow: auto; max-height:100vh;" >
                    
                    <div v-for="comment in comments" :key="comment.id">
                        <div class="card" v-if="comment.codeId == code.id" style="margin-bottom:10px;background-color:rgba(0,0,0,0);">
                            <div class="card-header">
                                <!-- {{ comment.createtime }} -->
                                <p style="float:left;">{{ comment.userName }}</p>
                                <p style="float:right;">{{ comment.createtime }}</p> 
                            </div>
                            <div class="card-body">
                                <!-- <h5 class="card-title">评论内容</h5> -->
                                <p class="card-text"> <pre>{{ comment.content }}</pre> </p>
                                <button type="button" class="btn btn-outline-danger" @click="remove_comment(comment)">删除</button>
                            </div>
                        </div>
                    </div>

                </div>

                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" style="background-color:rgba(128,0,128,0.7);margin-top: 10px;">
                            编辑评论
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="exampleModalLabel">编辑评论</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="mb-3">
                                            <label for="add-code-content" class="form-label">评论内容</label>
                                            <!-- <textarea v-model="codeadd.content" class="form-control" id="add-code-content" rows="7" placeholder="请输入代码内容"></textarea> -->
                                            <!-- v-model:value="codeadd.content" -->
                                            <VAceEditor
                                                v-model:value="commentadd.content"
                                                @init="editorInit"
                                                lang="c_cpp"
                                                theme="textmate"
                                                style="height: 300px" />
                                        </div>
                                </div>
                                <div class="modal-footer">
                                    <div class="error-message">{{ commentadd.error_message }}</div>
                                        <button type="button" class="btn btn-primary" @click="add_comment(code)">发送</button>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
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
    setup(){
        ace.config.set(
        "basePath", 
        "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

        const store = useStore();

        let codes = ref([]);
        let comments = ref([[]]);
        
        const commentadd = reactive({
            content: "",
            error_message: "",
        });

        const refresh_codes = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/debug/code/getlistall/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    codes.value = resp;
                }
            })
        }

        refresh_codes();

        const add_comment = (code) => {

            $.ajax({
                url: "http://127.0.0.1:3001/api/debug/comment/add/",
                type: "post",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                data: {
                    code_id: code.id,
                    content: commentadd.content,
                },
                success(resp) {
                    if(resp.error_message === "success"){
                        commentadd.content = "";
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
                        commentadd.error_message = "";
                        Modal.getInstance("#exampleModal").hide();
                        refresh_comments();
                    }
                    else {
                        commentadd.error_message = resp.error_message;
                    }
                }
            })

        }

        const refresh_comments = () => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/debug/comment/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    comments.value = resp;
                }
            })
        }

        refresh_comments();

        const remove_comment = (comment) => {
            $.ajax({
                url: "http://127.0.0.1:3001/api/debug/comment/remove/",
                type: "post",
                data: {
                    comment_id: comment.id,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    if(resp.error_message === "success"){
                        refresh_comments();
                    }
                    else {
                        alert(resp.error_message);
                    }
                },
            });
        }



        return {
            add_comment,
            remove_comment,
            comments,
            codes,
            commentadd,
        }
    }
}

</script>

<style scoped>
</style>