<template>
    <ContentField>
        
        <div class="card" style="margin-top: 20px;">
            <div class="card-header">
                {{ lesson[0].title }}
            </div>
            <div class="card-body">
                <pre>{{ lesson[0].description }}</pre>
            </div>
        </div>
    </ContentField>
</template>

<script>
import ContentField from '../../components/ContentField.vue'
import { useRoute } from 'vue-router';
import $ from 'jquery'
import { useStore } from 'vuex'
import { ref } from 'vue'

export default {
    components: {
        ContentField,
    },
    setup() {
        const store = useStore();
        const route = useRoute();
        let lesson = ref([]);

        const lessonId = route.params.lessonId;

        $.ajax({
                url: "http://127.0.0.1:3001/api/lesson/getbyid/",
                type: "get",
                data: {
                    lesson_id: lessonId,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp) {
                    lesson.value = resp;
                },
            });

        return {
            lesson,
        }
    }
}

</script>

<style scoped>

</style>